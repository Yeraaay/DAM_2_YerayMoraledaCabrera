package Calculadora;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Iterator;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

public class calculadora {

	private JFrame frame;
	private JTextField textField;
	private JButton[][] buttons;
	private JButton botonDividir;
	private JButton botonSumar;
	private JButton botonRestar;
	private JButton botonMulti;
	private JButton botonPunto;
	private JButton botonCero;
	private JButton botonIgual;
	private String operacion;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calculadora window = new calculadora();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public calculadora() {
		Calcualdora();
	}


	private void Calcualdora() {

		frame = new JFrame();
		frame.setBounds(100, 100, 425, 555);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(28, 34, 346, 69);
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);

		//El contenido del TextField se escribirá a la derecha
		textField.setHorizontalAlignment(JTextField.RIGHT);

		/*
		 * Modificamos la fuente del TextField para que se vea el contenido
		 * a un tamaño adecuado
		 */
		Font fuenteTextField = new Font("Sans-Serif", Font.BOLD, 35);
		textField.setFont(fuenteTextField);

		/*
		 * Añadimos un "keyListener" para controlar que solo se ingresan valores
		 * numéricos dentro del TextField
		 */
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if (!Character.isDigit(tecla)) e.consume();
			}
		});

		buttons = new JButton[3][3];
		Font fuente = new Font("Arial", Font.PLAIN, 40);

		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++ ) {
				final String numero = String.valueOf(i*3 + j + 1);
				buttons[i][j] = new JButton(numero);
				buttons[i][j].setBounds(30 + (j*90), 150 + (i*80), 70, 50);
				buttons[i][j].setFont(fuente);

				final String numeroPulsado = buttons[i][j].getText();
				buttons[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						textField.setText(textField.getText() + (numeroPulsado));
						textField.requestFocusInWindow();

					}
				});

				frame.getContentPane().add(buttons[i][j]);
			}
		}

		/*
		 * Intercambiamos el contenido de la primera fila por la tercera fila obteniendo el contenido de una fila
		 * con "getText()" y agregando ese contenido a la otra.
		 */
		for (int j=0; j<3; j++) {
			String contenidoFila1 = buttons[0][j].getText();
			buttons[0][j].setText(buttons[2][j].getText());
			buttons[2][j].setText(contenidoFila1);
		}

		//Actualizamos la ventana
		frame.revalidate();


		//Boton DIVIDIR
		botonDividir = new JButton("÷");
		botonDividir.setBounds(300, 150, 70, 50);
		botonDividir.setFont(fuente);
		botonDividir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacion = "÷";
				textField.setText(textField.getText() + "÷");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonDividir);

		//Boton MULTIPLICAR
		botonMulti = new JButton("*");
		botonMulti.setBounds(300, 230, 70, 50);
		botonMulti.setFont(fuente);
		botonMulti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacion = "*";
				textField.setText(textField.getText() + "*");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonMulti);

		//Boton RESTAR
		botonRestar = new JButton("-");
		botonRestar.setBounds(300, 310, 70, 50);
		botonRestar.setFont(fuente);
		botonRestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacion = "-";
				textField.setText(textField.getText() + "-");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonRestar);

		//Boton SUMAR
		botonSumar = new JButton("+");
		botonSumar.setBounds(300, 390, 70, 50);
		botonSumar.setFont(fuente);
		botonSumar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				operacion = "+";
				textField.setText(textField.getText() + "+");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonSumar);

		//Boton PUNTO
		botonPunto = new JButton(".");
		botonPunto.setBounds(120, 390, 70, 50);
		botonPunto.setFont(fuente);
		botonPunto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + ".");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonPunto);

		//Boton CERO
		botonCero = new JButton("0");
		botonCero.setBounds(30, 390, 70, 50);
		botonCero.setFont(fuente);
		botonCero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(textField.getText() + "0");
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonCero);

		//Boton IGUAL
		botonIgual = new JButton("=");
		botonIgual.setBounds(210, 390, 70, 50);
		botonIgual.setFont(fuente);
		botonIgual.setBackground(Color.GRAY);
		botonIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String contenido = textField.getText();
				if( operacion != null ) {
					String[] numerosCalcular = contenido.split(Pattern.quote(operacion));
					if (numerosCalcular.length == 2 ) {
						double numero1 = Double.parseDouble(numerosCalcular[0]);
						double numero2 = Double.parseDouble(numerosCalcular[1]);
						double resultado = 0.0;
						if (operacion.equals("+")) resultado = numero1 + numero2;
						else if (operacion.equals("-")) resultado = numero1 - numero2;
						else if (operacion.equals("÷")) {
							if (numero2 != 0) resultado = numero1 / numero2;
						}
						else if (operacion.equals("*")) resultado = numero1 * numero2;
						
						textField.setText(String.valueOf(resultado));
					}
				}
				textField.requestFocusInWindow();
			}
		});
		frame.getContentPane().add(botonIgual);

	}
}
