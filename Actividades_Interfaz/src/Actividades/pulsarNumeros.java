package Actividades;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class pulsarNumeros {

	private JFrame ventana;
	private JTextField texto;
	private JButton[] botones;
	private JButton boton_limpiar;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pulsarNumeros window = new pulsarNumeros();
					window.ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void ventana() {

		//Creamos una ventana y le proporcionamos un tamaño
		ventana = new JFrame("Pulsar Numeros");
		ventana.setSize(350, 500);

		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.getContentPane().setLayout(null);


		//Creamos un JTextField
		texto = new JTextField();
		texto.setBounds(30, 10, 260, 75);
		texto.setColumns(10);
		texto.setBackground(Color.BLACK);
		texto.setForeground(Color.GREEN);
		Font fuente = new Font("Arial", Font.PLAIN, 25);
		texto.setFont(fuente);
		texto.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char tecla = e.getKeyChar();
				if(tecla != '1' && tecla != '2' && tecla != '3') {
					e.consume();
					/**
					 * Si la tecla pulsada es distinta que "1", "2", o "3", la tecla pulsada se "consumirá" y no se 
					 * mostrará en pantalla, en este caso, en el TextField
					 */
				}
			}
		});

		ventana.getContentPane().add(texto);

		/**
		 * Existen varias formas de hacerlo, en mi caso, en primer lugar, creé boton por boton pero al darme cuenta
		 * que compartían propiedades, decidí meterlos en un bucle "for" para acotar y automaizar el código.
		 * Creamos boton por boton y añadimos sus respectivas caracteristicas
		 */


		/*
		boton1 = new JButton("PULSA 1");
		boton1.setBounds(50, 110, 225, 60);
		boton1.setBackground(Color.GRAY);
		boton1.setForeground(Color.white);
		Font colorBoton1 = new Font("Arial", Font.PLAIN, 25);
		texto.setFont(colorBoton1);
		ventana.getContentPane().add(boton1);

		boton2 = new JButton("PULSA 2");
		boton2.setBounds(50, 180, 225, 60);
		boton2.setBackground(Color.GRAY);
		boton2.setForeground(Color.white);
		Font colorBoton2 = new Font("Arial", Font.PLAIN, 25);
		texto.setFont(colorBoton2);
		ventana.getContentPane().add(boton2);

		boton3 = new JButton("PULSA 3");
		boton3.setBounds(50, 260, 225, 60);
		boton3.setBackground(Color.GRAY);
		boton3.setForeground(Color.white);
		Font colorBoton3 = new Font("Arial", Font.PLAIN, 25);
		texto.setFont(colorBoton3);
		ventana.getContentPane().add(boton3);

		boton_limpiar = new JButton("LIMPIAR");
		boton_limpiar.setBounds(100, 375, 125, 40);
		boton_limpiar.setBackground(Color.DARK_GRAY);
		boton_limpiar.setForeground(Color.white);
		Font color_limpiar = new Font("Arial", Font.PLAIN, 25);
		texto.setFont(color_limpiar);
		ventana.getContentPane().add(boton_limpiar);
		 */


		//Creamos los botones, que al compartir caracteristicas, usamos un bucle "for" para agregarlos

		String[] nombreBotones = {"PULSA 1", "PULSA 2", "PULSA 3"};
		botones = new JButton[nombreBotones.length];
		Font fuente_botones = new Font("Arial", Font.PLAIN, 45);
		for (int i = 0; i<nombreBotones.length; i++) {
			final int indice = i;
			botones[i] = new JButton(nombreBotones[i]);
			botones[i].setBounds(50, 110 + (i*80), 225, 60);
			botones[i].setBackground(Color.GRAY);
			botones[i].setForeground(Color.WHITE);
			botones[i].setFont(fuente_botones);
			botones[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					texto.setText(texto.getText() + (indice+1));
					texto.requestFocusInWindow();
				}
			});
			
			/**
			 * Añadimos los botones creados a nuestra ventana
			 */
			ventana.getContentPane().add(botones[i]);
		}

		boton_limpiar = new JButton("LIMPIAR");
		boton_limpiar.setBounds(100, 350, 125, 40);
		boton_limpiar.setBackground(Color.DARK_GRAY);
		boton_limpiar.setForeground(Color.white);
		Font fuente_limpiar = new Font("Arial", Font.PLAIN, 15);
		boton_limpiar.setFont(fuente_limpiar);
		boton_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				texto.setText("");
				texto.requestFocusInWindow();
			}
		});

		ventana.getContentPane().add(boton_limpiar);
	}



	public pulsarNumeros() {
		ventana();
	}
}
