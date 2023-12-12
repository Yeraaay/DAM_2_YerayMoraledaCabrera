package Controlador;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TresEnRaya extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton[][] buttons;
    private char[][] board;
    private char currentPlayer;
    private boolean isGameFinished;

    public TresEnRaya() {
        setTitle("Tres en Raya Inteligente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);

        JPanel panel = new JPanel(new GridLayout(3, 3));
        buttons = new JButton[3][3];
        board = new char[3][3];
        currentPlayer = 'X';
        isGameFinished = false;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
                board[i][j] = '-';
            }
        }

        add(panel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isGameFinished)
            return;

        JButton clickedButton = (JButton) e.getSource();
        int row = -1, col = -1;

        outerloop:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == clickedButton) {
                    row = i;
                    col = j;
                    break outerloop;
                }
            }
        }

        if (row != -1 && col != -1 && board[row][col] == '-') {
            board[row][col] = currentPlayer;
            clickedButton.setText(Character.toString(currentPlayer));
            checkWinner();
            if (!isGameFinished) {
                // IA move
                makeAIMove();
                checkWinner();
            }
        }
    }
    
    private void checkWinner() {
        int score = evaluate(board);

        if (score == 10) {
            JOptionPane.showMessageDialog(this, "¡La IA (O) ha ganado!");
            isGameFinished = true;
        } else if (score == -10) {
            JOptionPane.showMessageDialog(this, "¡Has ganado! (X)");
            isGameFinished = true;
        } else if (!isMovesLeft(board)) {
            JOptionPane.showMessageDialog(this, "¡Empate!");
            isGameFinished = true;
        }
    }


    private void makeAIMove() {
        int[] bestMove = findBestMove();
        int row = bestMove[0];
        int col = bestMove[1];

        if (row != -1 && col != -1) {
            board[row][col] = 'O';
            buttons[row][col].setText("O");
            currentPlayer = 'X';
        }
    }

    private int[] findBestMove() {
        int[] move = {-1, -1};
        int bestScore = Integer.MIN_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = 'O';
                    int score = minimax(board, 0, false);
                    board[i][j] = '-';

                    if (score > bestScore) {
                        bestScore = score;
                        move[0] = i;
                        move[1] = j;
                    }
                }
            }
        }

        return move;
    }

    private int minimax(char[][] board, int depth, boolean isMaximizing) {
        int score = evaluate(board);

        if (score == 10 || score == -10)
            return score;

        if (!isMovesLeft(board))
            return 0;

        if (isMaximizing) {
            int best = Integer.MIN_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'O';
                        best = Math.max(best, minimax(board, depth + 1, !isMaximizing));
                        board[i][j] = '-';
                    }
                }
            }

            return best;
        } else {
            int best = Integer.MAX_VALUE;

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'X';
                        best = Math.min(best, minimax(board, depth + 1, !isMaximizing));
                        board[i][j] = '-';
                    }
                }
            }

            return best;
        }
    }

    private int evaluate(char[][] board) {
        // Verificar filas y columnas
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

        // Verificar diagonales
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

    private boolean isMovesLeft(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-')
                    return true; // Hay movimientos disponibles
            }
        }
        return false; // No quedan movimientos disponibles
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TresEnRaya());
    }
}
