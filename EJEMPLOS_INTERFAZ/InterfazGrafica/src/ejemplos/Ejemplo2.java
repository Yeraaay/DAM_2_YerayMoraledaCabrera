package ejemplos;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import java.awt.Font;

public class Ejemplo2 extends JFrame {

	private JPanel contentPane;
	private JTextField Texto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ejemplo2 frame = new Ejemplo2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ejemplo2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setToolTipText("Primera Aplicaci\u00F3n");
		FlowLayout flowLayout = (FlowLayout) contentPane.getLayout();
		flowLayout.setHgap(7);
		contentPane.setBackground(new Color(0, 0, 160));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 64)));

		setContentPane(contentPane);
		
		Texto = new JTextField();
		Texto.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		Texto.setText("Hola mundo");
		Texto.setBackground(new Color(192, 192, 192));
		Texto.setBounds(100, 150, 200, 200);
		contentPane.add(Texto);
		Texto.setColumns(10);
	}

}
