package Calculadora;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

public class calculadora {
	
	private JFrame frame;
	private JTextField textField;
	private JButton btnNewButton_1;
	private JButton[] buttons;
	
	
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
		
		String[] numeros = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
		buttons = new JButton[numeros.length];
		Font fuente = new Font("Arial", Font.PLAIN, 30);
		
		for(int i=0; i<2; i++) {
			buttons[i] = new JButton(""+(i+1)+"");
			buttons[i].setBounds(30 + (i*80), 149, 70, 50);
			buttons[i].setFont(fuente);
			
//			for(int j=1; j<3; j++) {
//				JButton botonY = new JButton(""+j+"");
//			}
			
			frame.getContentPane().add(buttons[i]);
		}
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(28, 149, 69, 50);
		frame.getContentPane().add(btnNewButton);
		
		
		
	}
}
