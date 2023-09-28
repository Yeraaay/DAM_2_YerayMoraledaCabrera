package PruebaInterfaz;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSlider;
import java.awt.List;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;

public class interfazEjemplo {

	private JFrame frame;
	private static JTextField textField;
	private static JTextField txtAdivineElNmero;

	/**
	 * Launch the application.
	 */
	
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfazEjemplo window = new interfazEjemplo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	public static void main(String[] args) {
		
		//Creamos un JFrame que nos hará de vetana
		JFrame ventana = new JFrame("Titulo_ventana");
		
		//Ponemos la ventana visible
		ventana.setVisible(true);
		ventana.getContentPane().setLayout(null);
		
		txtAdivineElNmero = new JTextField();
		txtAdivineElNmero.setBounds(191, 43, 92, 26);
		ventana.getContentPane().add(txtAdivineElNmero);
		txtAdivineElNmero.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Adivine un número:");
		lblNewLabel.setBounds(62, 46, 134, 21);
		ventana.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("REINTENTAR");
		btnNewButton_1.setToolTipText("");
		btnNewButton_1.setBounds(121, 150, 115, 26);
		ventana.getContentPane().add(btnNewButton_1);
		
		//Le damos un tamaño a nuestra ventana
		ventana.setSize(400,250);
		
		
		//BOTONES ENABLE
		/*
		JButton boton1 = new JButton("BOTON 1");
		JButton boton2 = new JButton("BOTON 2");
		boton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boton1.setEnabled(false);
				boton2.setEnabled(true);
			}
		});
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boton1.setEnabled(true);
				boton2.setEnabled(false);
			}
		});
		ventana.getContentPane().add(boton1, BorderLayout.WEST);
		ventana.getContentPane().add(boton2, BorderLayout.EAST);
		*/
	}

	/**
	 * Create the application.
	 */
	public interfazEjemplo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JButton btnNewButton = new JButton("PULSATE");
		btnNewButton.setBackground(new Color(0, 128, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.ITALIC, 11));
		btnNewButton.setForeground(new Color(128, 0, 255));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hola");
			}
		});
		frame.getContentPane().add(btnNewButton, BorderLayout.NORTH);
	}
}
