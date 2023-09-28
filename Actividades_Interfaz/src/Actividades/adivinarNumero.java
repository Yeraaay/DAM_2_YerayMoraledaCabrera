package Actividades;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class adivinarNumero {

	private JFrame frame;
	private JTextField textField;
	private JLabel label;
	private int intentos = 4;
	private int numero_random;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					adivinarNumero window = new adivinarNumero();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void adivinarNumeros() {

		frame = new JFrame();
		frame.setBounds(100, 100, 400, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JButton boton = new JButton("Adivinar");
		boton.setBounds(105, 150, 180, 35);
		frame.getContentPane().add(boton);

		textField = new JTextField();
		textField.setBounds(240, 35, 105, 35);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		label = new JLabel("Adivine el número (1-100): ");
		label.setBounds(43, 35, 173, 35);
		frame.getContentPane().add(label);

		JLabel labelFinal = new JLabel("");
		labelFinal.setBounds(60,101, 260, 35);
		frame.getContentPane().add(labelFinal);



		textField.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				int tecla = e.getKeyCode();
				if (tecla == 32) {
					boton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							numero_random = (int)(Math.random()*101);
							try {
								int numero = Integer.parseInt(textField.getText());
								if (intentos >= 0) {
									if (numero == numero_random) {
										System.out.println(numero_random);
										labelFinal.setText("Correcto! El número es " + numero_random);
										boton.setEnabled(false);
									} else if (numero < numero_random) {
										labelFinal.setText("El numero es mayor. Quedan: " + intentos + " intentos.");
										intentos--;
									} else {
										labelFinal.setText("El numero es menor. Quedan: " + intentos + " intentos.");
										intentos--;
									}
								} else {
									labelFinal.setForeground(Color.GREEN);
									Font fuente = new Font("Arial", Font.PLAIN, 35);
									labelFinal.setFont(fuente);
									labelFinal.setText("No quedan intentas, el número era: " + numero_random);
								}

							} catch (NumberFormatException e2) {
								System.out.println(e2.getMessage().toString());
							}
							textField.setText("");
						}
					});
				}
			}
			public void keyReleased(KeyEvent e) {
			}
			public void keyPressed(KeyEvent e) {
			}
		});


	}

	public adivinarNumero() {
		adivinarNumeros();
	}
}
