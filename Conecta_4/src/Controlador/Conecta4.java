package Controlador;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.html.HTMLEditorKit;

import com.jtattoo.plaf.texture.TextureLookAndFeel;

public class Conecta4 extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	/**
	 * Variables para la creación de botones que posteriormente formarán el tablero del juego
	 */
	private JButton[][] buttons;
	private char[][] board;
	private char currentPlayer;

	/**
	 * Variables para indicar el tamaño del tablero de juego y las fichas que hay que conectar para ganar
	 */
	private final int ROWS = 6;
	private final int COLUMNS = 7;
	private final int FICHAS_A_CONECTAR = 4;

	/**
	 * Variables para controlar el modo de juego (vsAI), llevar un seguimiento de los turnos (turnoJugador1),
	 * y calcular si la partida ha finalizado
	 */
	private boolean isGameFinished;
	private boolean vsAI; // Variable para controlar el modo de juego
	private boolean turnoJugador1; // Variable para llevar el seguimiento de los turnos

	public Conecta4() {
		setTitle("Conecta 4 Inteligente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(650, 500);

		JPanel panel = new JPanel(new GridLayout(ROWS, COLUMNS));
		panel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		buttons = new JButton[ROWS][COLUMNS];
		board = new char[ROWS][COLUMNS];
		currentPlayer = 'X';
		isGameFinished = false;

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 40));
				buttons[i][j].addActionListener(this);
				panel.add(buttons[i][j]);
				board[i][j] = '-';
			}

			/**
			 * Cambia el color de fondo para las filas no jugables
			 */
			if (i != 0) {
				for (int j = 0; j < COLUMNS; j++) {
					buttons[i][j].setEnabled(false);
					buttons[i][j].setBackground(Color.LIGHT_GRAY);
				}
			}
		}

		/**
		 *  Menú de inicio para seleccionar el modo de juego
		 */
		int option = JOptionPane.showOptionDialog(this, "¿Contra la máquina o 2 jugadores?",
				"Selecciona el modo de juego", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, new String[]{"Contra la máquina", "2 jugadores"}, "Contra la máquina");

		/**
		 * Si option es YES_OPTION, vsAI será true
		 */
		vsAI = (option == JOptionPane.YES_OPTION);

		/**
		 * Botones de guardado y cargado del tablero de juego
		 */
		JButton saveButton = new JButton("Guardar");
		saveButton.addActionListener(e -> guardarPartida());

		JButton loadButton = new JButton("Cargar");
		loadButton.addActionListener(e -> cargarPartida());

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(loadButton);

		JMenuBar menuBar = new JMenuBar();

		JMenuItem blankSpace1 = new JMenuItem("");
		blankSpace1.setEnabled(false);
		menuBar.add(blankSpace1);

		// Elemento centrado (un poco raro pero no encontraba otra forma factible)
		JMenuItem helpMenuItem = new JMenuItem("           Ayuda");
		helpMenuItem.setFont(new Font("Arial", Font.BOLD, 21));
		helpMenuItem.setForeground(Color.WHITE);
		helpMenuItem.setBackground(Color.BLUE);
		helpMenuItem.addActionListener(e -> showHelp());
		menuBar.add(helpMenuItem);

		JMenuItem blankSpace2 = new JMenuItem("");
		blankSpace2.setEnabled(false);
		menuBar.add(blankSpace2);

		setJMenuBar(menuBar);

		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		/**
		 * Se establece un estilo concreto para el juego
		 */
		try {
			//Se indica el Look and Feel de JTattoo (TextureLookAndFeel en este caso)
			UIManager.setLookAndFeel(new TextureLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		add(panel);
		setVisible(true);

		// Iniciar el juego
		startGame();
	}

	/**
	 * Método que implementa un panel de ayuda para los usuarios, incluyendo su funcionalidas
	 * y algunas preguntas sobre la jugabilidad
	 */
	private void showHelp() {
		String helpText = "Guía de Ayuda:\n" +
				"Este es el juego Conecta 4. El objetivo es hacer una fila de 4 fichas del mismo color, en este caso, conectando '0' o 'X', ya sea horizontal, vertical o diagonalmente.\n" +
				"Para jugar, haz clic en una columna para dejar caer una ficha.\n" +
				"Cuando alguien gana una partida, puede reiniciar el juego o salir del mismo.\n" +
				"Puedes guardar el juego y cargarlo en cualquier momento usando los botones correspondientes.\n\n" +
				"Preguntas Frecuentes (FAQs):\n" +
				"1. ¿Cómo gano el juego?\n" +
				"   - Ganas haciendo una fila de 4 fichas iguales, ya sea '0' o 'X' en línea recta (horizontal, vertical o diagonal).\n" +
				"2. ¿Cómo se juega contra la IA?\n" +
				"   - Selecciona 'Contra la máquina' al inicio. La IA jugará como 'O'.\n" +
				"3. ¿Cómo cargar una partida guardada?\n" +
				"   - Asegúrate de haber guardado una partida antes. Luego, selecciona 'Cargar partida' en el menú principal.\n";

		JOptionPane.showMessageDialog(this, helpText, "Ayuda y Preguntas frecuentes", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Método estructurado para la inicialización del juego
	 */
	private void startGame() {
		if (vsAI) {
			currentPlayer = 'X'; // El jugador humano comienza con 'X'
			turnoJugador1 = true; // El jugador 1 comienza primero
			realizarMovimiento(); // Hacer el primer movimiento
		} else {
			currentPlayer = 'X'; // Empieza el jugador 'X'
			turnoJugador1 = true; // El jugador 1 comienza primero
		}
	}

	/**
	 * Método encargado de la activación y funcionalidad de los otros métodos
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (isGameFinished) {
			return;
		}

		JButton botonPulsado = (JButton) e.getSource();
		int col = -1;

		for (int j = 0; j < COLUMNS; j++) {
			if (buttons[0][j] == botonPulsado) {
				col = j;
				break;
			}
		}

		if (col != -1 && isColumnNotFull(col)) {
			int row = buscarEspacioTablero(col);
			board[row][col] = currentPlayer;
			buttons[row][col].setText(Character.toString(currentPlayer));

			char winner = checkWinner(row, col);
			if (winner == 'X') {
				announceWinner('X');
			} else if (winner == 'O') {
				announceWinner('O');
			} else if (tableroCompleto()) {
				announceWinner('-');
			} else {
				currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Cambiar jugador
				if (vsAI) {
					movimientoIA(col); // Movimiento de la IA después del jugador humano
				}
			}
		}
	}

	/**
	 * Método que comprueba si el tablero está lleno
	 * @return
	 */
	private boolean tableroCompleto() {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLUMNS; col++) {
				if (board[row][col] == '-') {
					return false; // Todavía hay espacios vacíos
				}
			}
		}
		return true; // El tablero está completamente lleno
	}

	/**
	 * Método que permite la ejecución de movimientos
	 */
	private void realizarMovimiento() {
		currentPlayer = turnoJugador1 ? 'X' : 'O'; // Determinar el jugador actual

		if (vsAI && currentPlayer == 'O') {
			movimientoIA(1); // Si está jugando contra la IA y es el turno de la IA
		}
	}

	/**
	 * Método encargado de mostrar el usuario ganador
	 * @param winner
	 */
	private void announceWinner(char winner) {
		String message;
		if (winner == 'O' && vsAI) {
			message = "¡Ha ganado la IA!";
		} else if (winner == 'X' && vsAI) {
			message = "¡Has ganado!";
		} else if (winner == 'O' && !vsAI) {
			message = "¡Ha ganado el Jugador 2!";
		} else if (winner == 'X' && !vsAI) {
			message = "¡Ha ganado el Jugador 1!";
		} else {
			message = "¡Empate!";
		}

		int option = JOptionPane.showOptionDialog(
				this,
				message + "\n¿Qué deseas hacer?",
				"Fin del Juego",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				new String[]{"Reiniciar", "Salir"},
				"Reiniciar"
				);

		if (option == JOptionPane.YES_OPTION) {
			resetGame(); // Método para reiniciar el juego
		} else {
			System.exit(0); // Salir del juego
		}
		// Resto de tu código...
	}

	/**
	 * Método para resetear la partida actual
	 */
	private void resetGame() {
		// Reiniciar todas las variables y el tablero para comenzar un nuevo juego
		currentPlayer = 'X';
		isGameFinished = false;

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				buttons[i][j].setText("");
				board[i][j] = '-';
			}
		}
	}

	/**
	 * Método encargado de comprobar si una columna está completa
	 * @param col
	 * @return
	 */
	private boolean isColumnNotFull(int col) {
		return board[0][col] == '-';
	}

	/**
	 * Método cuya funcionalidad es recorrer el tablero y buscar donde es el punto más bajo donde colocar una ficha
	 * @param col
	 * @return
	 */
	private int buscarEspacioTablero(int col) {
		for (int i = ROWS - 1; i >= 0; i--) {
			if (board[i][col] == '-') {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Método encargado de comprobar si algún usuario ha ganado, conectando 4 fichas iguales vertical,
	 * horizontal o diagonalmente
	 * @param row
	 * @param col
	 * @return
	 */
	private char checkWinner(int row, int col) {
		char token = board[row][col];

		// Verificar horizontalmente
		for (int c = 0; c <= COLUMNS - FICHAS_A_CONECTAR; c++) {
			for (int r = 0; r < ROWS; r++) {
				if (board[r][c] == token &&
						board[r][c + 1] == token &&
						board[r][c + 2] == token &&
						board[r][c + 3] == token) {
					return token;
				}
			}
		}

		// Verificar verticalmente
		for (int r = 0; r <= ROWS - FICHAS_A_CONECTAR; r++) {
			for (int c = 0; c < COLUMNS; c++) {
				if (board[r][c] == token &&
						board[r + 1][c] == token &&
						board[r + 2][c] == token &&
						board[r + 3][c] == token) {
					return token;
				}
			}
		}

		// Verificar diagonal ascendente
		for (int r = FICHAS_A_CONECTAR - 1; r < ROWS; r++) {
			for (int c = 0; c <= COLUMNS - FICHAS_A_CONECTAR; c++) {
				if (board[r][c] == token &&
						board[r - 1][c + 1] == token &&
						board[r - 2][c + 2] == token &&
						board[r - 3][c + 3] == token) {
					return token;
				}
			}
		}

		// Verificar diagonal descendente
		for (int r = 0; r <= ROWS - FICHAS_A_CONECTAR; r++) {
			for (int c = 0; c <= COLUMNS - FICHAS_A_CONECTAR; c++) {
				if (board[r][c] == token &&
						board[r + 1][c + 1] == token &&
						board[r + 2][c + 2] == token &&
						board[r + 3][c + 3] == token) {
					return token;
				}
			}
		}

		// Si no hay ganador
		return '-';
	}

	/**
	 * Método cuya funcionalidad es proporcionarle movimiento a la máquina
	 * @param opponentColumn
	 */
	private void movimientoIA(int opponentColumn) {
		if (isGameFinished)
			return;

		int aiColumn = findBestMoveForAI(opponentColumn);

		try {
			if (aiColumn == -1) {
				throw new ArrayIndexOutOfBoundsException("Columna llena, no se puede realizar la jugada");
			}

			int row = buscarEspacioTablero(aiColumn);
			board[row][aiColumn] = 'O';
			buttons[row][aiColumn].setText("O");

			char ganador = checkWinner(row, aiColumn);
			if (ganador == 'O') {
				announceWinner(ganador);
			} else if (tableroCompleto()) {
				announceWinner('-');
			} else {
				currentPlayer = 'X'; // Cambiar al jugador humano
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			JOptionPane.showMessageDialog(this, "Columna llena, no se puede realizar la jugada");
		}
	}

	/**
	 * Método que busca cuál es el mejor movimiento o colocación para la máquina
	 * @param opponentColumn
	 * @return
	 */
	private int findBestMoveForAI(int opponentColumn) {
		// Evaluar las posibles jugadas del oponente en la columna recibida
		int opponentRow = buscarEspacioTablero(opponentColumn);
		if (opponentRow == -1) {
			// La columna está llena, manejar este caso (puede ser un mensaje de error o una acción apropiada)
			// Por ejemplo:
			System.out.println("Columna llena, no se puede realizar la jugada");
			return -1; // Otra indicación apropiada
		}

		board[opponentRow][opponentColumn] = 'X';

		// Buscar la mejor columna para la IA después del movimiento del oponente
		int bestMove = findBestMove();

		// Deshacer el movimiento simulado del oponente
		board[opponentRow][opponentColumn] = '-';

		return bestMove;
	}

	/**
	 * Método que, dentro de los movimientos posibles por la máquina, busca el mejor de ellos,
	 * anulando al otro la posibilidad de ganar
	 * @return
	 */
	private int findBestMove() {
		int bestMove = -1;
		int bestScore = Integer.MIN_VALUE;

		for (int j = 0; j < COLUMNS; j++) {
			if (isColumnNotFull(j)) {
				int row = buscarEspacioTablero(j);
				board[row][j] = 'O';
				int score = minimax(board, 0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
				board[row][j] = '-';

				if (score > bestScore) {
					bestScore = score;
					bestMove = j;
				}
			}
		}

		return bestMove;
	}

	/**
	 * Método de búsqueda que administra y determina la mejor jugada posible, evaluando todas las posibles jugadas en el tablero,
	 * utilizando una estrategia de recursividad que maximiza o minimiza el puntaje en función del jugador actual
	 * @param board
	 * @param depth
	 * @param isMaximizingPlayer
	 * @param alpha
	 * @param beta
	 * @return
	 */
	private int minimax(char[][] board, int depth, boolean isMaximizingPlayer, int alpha, int beta) {
		int score = evaluate(board);

		if (score == 100 || score == -100 || depth == 6) {
			return score;
		}

		if (isMaximizingPlayer) {
			int best = Integer.MIN_VALUE;

			for (int j = 0; j < COLUMNS; j++) {
				if (isColumnNotFull(j)) {
					int row = buscarEspacioTablero(j);
					board[row][j] = 'O';
					best = Math.max(best, minimax(board, depth + 1, false, alpha, beta));
					alpha = Math.max(alpha, best);
					board[row][j] = '-';

					if (beta <= alpha) {
						break;
					}
				}
			}

			return best;
		} else {
			int best = Integer.MAX_VALUE;

			for (int j = 0; j < COLUMNS; j++) {
				if (isColumnNotFull(j)) {
					int row = buscarEspacioTablero(j);
					board[row][j] = 'X';
					best = Math.min(best, minimax(board, depth + 1, true, alpha, beta));
					beta = Math.min(beta, best);
					board[row][j] = '-';

					if (beta <= alpha) {
						break;
					}
				}
			}

			return best;
		}
	}

	/**
	 * Método cuya funcionalidad es evaluar una posible jugada por parte de la máquina
	 * @param board
	 * @return
	 */
	private int evaluate(char[][] board) {
		int score = 0;

		// Evaluar horizontalmente
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col <= COLUMNS - FICHAS_A_CONECTAR; col++) {
				score += evaluateLine(board[row][col], board[row][col + 1], board[row][col + 2], board[row][col + 3]);
			}
		}

		// Evaluar verticalmente
		for (int col = 0; col < COLUMNS; col++) {
			for (int row = 0; row <= ROWS - FICHAS_A_CONECTAR; row++) {
				score += evaluateLine(board[row][col], board[row + 1][col], board[row + 2][col], board[row + 3][col]);
			}
		}

		// Evaluar diagonales ascendentes
		for (int row = FICHAS_A_CONECTAR - 1; row < ROWS; row++) {
			for (int col = 0; col <= COLUMNS - FICHAS_A_CONECTAR; col++) {
				score += evaluateLine(board[row][col], board[row - 1][col + 1], board[row - 2][col + 2], board[row - 3][col + 3]);
			}
		}

		// Evaluar diagonales descendentes
		for (int row = 0; row <= ROWS - FICHAS_A_CONECTAR; row++) {
			for (int col = 0; col <= COLUMNS - FICHAS_A_CONECTAR; col++) {
				score += evaluateLine(board[row][col], board[row + 1][col + 1], board[row + 2][col + 2], board[row + 3][col + 3]);
			}
		}

		return score;
	}

	/**
	 * Método igual que el anterior, pero asignando un puntaje en función de la configuración de las fichas de esa línea
	 * @param cell1
	 * @param cell2
	 * @param cell3
	 * @param cell4
	 * @return
	 */
	private int evaluateLine(char cell1, char cell2, char cell3, char cell4) {
		int score = 0;

		// Evaluar una línea de cuatro células
		char[] cells = { cell1, cell2, cell3, cell4 };

		// Contar fichas del jugador y la IA
		int playerTokens = 0;
		int opponentTokens = 0;

		for (char cell : cells) {
			if (cell == 'O') {
				playerTokens++;
			} else if (cell == 'X') {
				opponentTokens++;
			}
		}

		if (playerTokens == 4) {
			score += 100;
		} else if (playerTokens == 3 && opponentTokens == 0) {
			score += 5;
		} else if (playerTokens == 2 && opponentTokens == 0) {
			score += 2;
		}

		if (opponentTokens == 4) {
			score -= 100;
		} else if (opponentTokens == 3 && playerTokens == 0) {
			score -= 5;
		} else if (opponentTokens == 2 && playerTokens == 0) {
			score -= 2;
		}

		return score;
	}

	/**
	 * Método para guardar la partida actual, almacenando la posición de las fichas en un archivo '.txt'.
	 */
	private void guardarPartida() {
		try (PrintWriter writer = new PrintWriter("conecta4_save.txt")) {
			// Escribir los datos relevantes en el archivo
			writer.println(currentPlayer);
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLUMNS; j++) {
					writer.print(board[i][j]);
				}
				writer.println();
			}
			writer.println(turnoJugador1);
			writer.println(isGameFinished);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método que carga y actualiza el tablero por las fichas guardadas en el archivo '.txt'
	 */
	private void cargarPartida() {
		try (BufferedReader reader = new BufferedReader(new FileReader("conecta4_save.txt"))) {
			currentPlayer = reader.readLine().charAt(0);
			for (int i = 0; i < ROWS; i++) {
				String line = reader.readLine();
				for (int j = 0; j < COLUMNS; j++) {
					board[i][j] = line.charAt(j);
					if (board[i][j] != '-') {
						buttons[i][j].setText(Character.toString(board[i][j]));
					}
				}
			}
			turnoJugador1 = Boolean.parseBoolean(reader.readLine());
			isGameFinished = Boolean.parseBoolean(reader.readLine());
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "No hay ninguna partida guardada todavía");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Método para mostrar por pantalla el juego con todas sus funcionalidades
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new Conecta4());
	}
}