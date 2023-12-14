package Vista;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import Controlador.Controlador;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static JButton[][] botones;
    public static char[][] board;
    public static char currentPlayer;
    public static boolean isGameFinished;
	public static Controlador controlador;
	public static Vista vista;

	public Vista() {
		setTitle("Tres en Raya Inteligente");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 300);

		botones = new JButton[3][3];
		board = new char[3][3];
		currentPlayer = 'X';
		isGameFinished = false;
		
		add(contentPane);
		configurarBotones();
		setVisible(true);
	}
	private void configurarBotones() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				botones[i][j] = new JButton();
				botones[i][j].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 50));
				contentPane.add(botones[i][j]);
				board[i][j] = '-';
			}
		}
	}
	

}
