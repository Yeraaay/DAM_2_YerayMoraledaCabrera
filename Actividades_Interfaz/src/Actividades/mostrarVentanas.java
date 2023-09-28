package Actividades;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class mostrarVentanas {
	
	private JFrame ventana1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mostrarVentanas window = new mostrarVentanas();
					window.ventana1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void ventana1() {
		
		//Creamos una ventana y le apropiamos un tamaño determinado
		ventana1 = new JFrame("Varias ventanas");
		ventana1.setSize(400, 300);
		
		//Hacemos que la ventana no pueda variar su tamaño
		ventana1.getContentPane().setLayout(null);
		ventana1.setResizable(true);
		
		JButton boton= new JButton("Crear Ventana");
		boton.setBounds(100, 150, 160, 60);
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana2();
				ventana1.setEnabled(false);
			}
		});
		ventana1.getContentPane().add(boton);
		ventana1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/*
		JLabel panel = new JLabel();
		panel.setBounds(10, 10, 50, 50);
		panel.setText("Hola buenas tardes");
		ventana.getContentPane().add(panel);
		*/
	}
	
	public void ventana2() {
		
		//Creamos una ventana y le damos sus respectivas caracteristicas
		JFrame ventana2 = new JFrame("Ventana 2");
		ventana2.setVisible(true);
		ventana2.setSize(300, 200);
		
		ventana2.getContentPane().setLayout(null);
		ventana2.setResizable(false);
		
		//Creamos un boton
		JButton boton2 = new JButton("Cancelar");
		boton2.setBounds(70,120, 130, 30);
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana2.dispose();
				ventana1.setEnabled(true);
				ventana1.setVisible(true);
			}
		});
		ventana2.getContentPane().add(boton2);
		
		ventana2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
	}
	
	
	public mostrarVentanas() {
		ventana1();
	}
}
