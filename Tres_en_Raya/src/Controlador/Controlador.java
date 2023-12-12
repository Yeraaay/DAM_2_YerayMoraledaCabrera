package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Vista.Vista;

public class Controlador implements ActionListener {

	static Vista vista;

	public Controlador(Vista vista_interfaz) {
		Vista.vista = vista_interfaz;
	}

	public void escucharEventos() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
//				Vista.botones.addActionListener(this);
			}
		}

	}
	
	// Método para comprobar si alguien ha ganado
    public static void comprobarVictoria() {
        int puntuacion = evaluarTablero(Vista.board);
        
//        switch (puntuacion) {
//		case 10:
//            JOptionPane.showMessageDialog(null, "¡La IA (O) ha ganado!");
//            Vista.isGameFinished = true;
//			break;
//		case -10:
//            JOptionPane.showMessageDialog(null, "¡Has ganado! (X)");
//            Vista.isGameFinished = true;
//			break;
//		case !movimientosDisponibles(Vista.board):
//            JOptionPane.showMessageDialog(null, "¡Empate!");
//            Vista.isGameFinished = true;
//		default:
//			break;
//		}

        if (puntuacion == 10) {
            JOptionPane.showMessageDialog(null, "¡La IA (O) ha ganado!");
            Vista.isGameFinished = true;
        } else if (puntuacion == -10) {
            JOptionPane.showMessageDialog(null, "¡Has ganado! (X)");
            Vista.isGameFinished = true;
        } else if (!movimientosDisponibles(Vista.board)) {
            JOptionPane.showMessageDialog(null, "¡Empate!");
            Vista.isGameFinished = true;
        }
    }
    
    // Método encargado de evaluar el estado actual del tablero y determinar si hay algún ganador
    public static int evaluarTablero(char[][] board) {
        // Verifica filas y columnas
        for (int row = 0; row < 3; row++) {
            if (board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                if (board[row][0] == 'O')
                    return 10;
                else if (board[row][0] == 'X')
                    return -10;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                if (board[0][col] == 'O')
                    return 10;
                else if (board[0][col] == 'X')
                    return -10;
            }
        }

        // Verifica diagonales
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'O')
                return 10;
            else if (board[0][0] == 'X')
                return -10;
        }

        if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'O')
                return 10;
            else if (board[0][2] == 'X')
                return -10;
        }

        return 0; // Retorna 0 si no hay un ganador
    }
    
    // Método para comprobar si existen movimientos disponibles
    public static boolean movimientosDisponibles(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-')
                    return true; // Hay movimientos disponibles
            }
        }
        return false; // No quedan movimientos disponibles
    }

	public void actionPerformed(ActionEvent e) {
		if (Vista.isGameFinished)
			return;

		JButton clickedButton = (JButton) e.getSource();
		int row = -1, col = -1;

		outerloop:
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (Vista.botones[i][j] == clickedButton) {
						row = i;
						col = j;
						break outerloop;
					}
				}
			}

		if (row != -1 && col != -1 && Vista.board[row][col] == '-') {
			Vista.board[row][col] = Vista.currentPlayer;
			clickedButton.setText(Character.toString(Vista.currentPlayer));
			comprobarVictoria();
			if (!Vista.isGameFinished) {
				// IA move
				makeAIMove();
				comprobarVictoria();
			}
		}
	}

}