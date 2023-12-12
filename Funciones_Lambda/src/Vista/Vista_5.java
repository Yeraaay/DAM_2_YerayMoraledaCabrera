package Vista;

import java.awt.Font;

import javax.security.auth.login.AppConfigurationEntry;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.mint.MintLookAndFeel;

import Controlador.Controlador_5;

public class Vista_5 extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	public static JButton botonAniadir;
	public static JTextField textfield;
	public static JLabel labelPrincipal;
	public static JLabel label1;
	public static JLabel label2;
	public static JLabel label3;
	public static JTextArea[] textarea;
	public static JTextArea textareaSumas;
	protected static Font fuente = new Font("Arial", Font.PLAIN, 18);
	public static Vista_5 vista;
	private Controlador_5 ejecutarAcciones;


	public Vista_5() {
		setTitle("Mapeo de Datos Lambda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		try {
			//Se establece el Look and Feel de JTattoo (MintLookAndFeel en este caso)
			UIManager.setLookAndFeel(new MintLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		configurarBoton();
		configurarTextField();
		configurarJLabel();
		ejecutar();
	}

	public static void configurarBoton() {
		botonAniadir = new JButton("Añadir Número Divisor");
		botonAniadir.setBounds(210, 30, 240, 40);
		Font fuenteBoton = new Font("Arial", Font.BOLD, 15);
		botonAniadir.setFont(fuenteBoton);

		contentPane.add(botonAniadir);
	}

	public static void configurarTextField() {
		textfield = new JTextField();
		textfield.setBounds(35, 30, 130, 40);
		textfield.setFont(fuente);
		textfield.requestFocusInWindow();

		contentPane.add(textfield);
	}

	public static void configurarJLabel() {
		labelPrincipal = new JLabel("Introduce el número por el cual quieres dividir la lista de números");
		labelPrincipal.setBounds(21, 0, 500, 40);
		Font fuenteLabel = new Font("Arial", Font.PLAIN, 15);
		labelPrincipal.setFont(fuenteLabel);

		label1 = new JLabel("Listas");
		label1.setFont(fuenteLabel);
		label1.setBounds(20, 65, 100, 40);

		contentPane.add(labelPrincipal);
		contentPane.add(label1);
	}

	public static void configurarTextArea() {
		try {
			int numeroDivisor = Integer.parseInt(textfield.getText());
			textarea = new JTextArea[numeroDivisor];
			
			for (int i=0; i<numeroDivisor; i++) {
				textarea[i] = new JTextArea();
				JScrollPane scrollPane = new JScrollPane(textarea[i]);
				scrollPane.setBounds(20, 95 + (i*60), 300, 30);
				textarea[i].setFont(fuente);
				textarea[i].setEditable(false);
				textarea[i].setLineWrap(true);
				textarea[i].setWrapStyleWord(true);

				contentPane.add(scrollPane);
			}
			
			// Revalida el contenedor y muestra los nuevos datos
			contentPane.revalidate();
			// Vuelte a pintar el contenedor para reflejar los cambios realizados
			contentPane.repaint();
			
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Ingresa un número válido");
		}
	}
	
	public static void configurarTextAreaSumas() {
		textareaSumas = new JTextArea();
		JScrollPane scrollpane = new JScrollPane(textareaSumas);
		scrollpane.setBounds(20, 95 + (textarea.length * 60), 300, 100);
		textareaSumas.setFont(fuente);
		textareaSumas.setEditable(false);
		textareaSumas.setLineWrap(true);
		textareaSumas.setWrapStyleWord(true);
		
		contentPane.add(scrollpane);
	}

	public void ejecutar() {
		ejecutarAcciones = new Controlador_5(this);
		ejecutarAcciones.escucharEventos();
	}

}