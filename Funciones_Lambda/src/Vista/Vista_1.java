package Vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.aluminium.AluminiumLookAndFeel;

import Controlador.Controlador_1;

public class Vista_1 extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	public static JButton botonAniadir;
	public static JTextField textfield;
	public static JLabel label1;
	public static JLabel label2;
	public static JLabel label3;
	public static JTextArea[] textarea;
	public static JTextArea textareaOrdenado;
	protected static Font fuente = new Font("Arial", Font.PLAIN, 18);
	public static Vista_1 vista;
	private Controlador_1 ejecutarAcciones;
	
	
	public Vista_1() {
		setTitle("Números Primos Lambda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        try {
            //Se establece el Look and Feel de JTattoo (AluminiumLookAndFeel en este caso)
            UIManager.setLookAndFeel(new AluminiumLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		
		configurarBoton();
		configurarTextField();
		configurarJLabel();
		configurarTextArea();
		configurarTextAreaOrdenado();
		ejecutar();
	}
	
	public static void configurarBoton() {
		botonAniadir = new JButton("Añadir Número");
		botonAniadir.setBounds(210, 30, 140, 40);
		Font fuenteBoton = new Font("Arial", Font.BOLD, 15);
		botonAniadir.setFont(fuenteBoton);
		
		contentPane.add(botonAniadir);
	}
	
	public static void configurarTextField() {
		textfield = new JTextField();
		textfield.setBounds(35, 30, 130, 40);
		textfield.setFont(fuente);
		
		contentPane.add(textfield);
	}
	
	private static void configurarJLabel() {
		label1 = new JLabel("Números primos");
		label1.setBounds(20, 60, 100, 40);
		label2 = new JLabel("Números enteros");
		label2.setBounds(200, 60, 100, 40);
		label3 = new JLabel("Números primos ordenados");
		label3.setBounds(20, 190, 300, 40);
		
		contentPane.add(label1);
		contentPane.add(label2);
		contentPane.add(label3);
	}
	
	public static void configurarTextArea() {
		textarea = new JTextArea[2];
		for (int i=0; i<2; i++) {
			textarea[i] = new JTextArea();
			JScrollPane scrollPane = new JScrollPane(textarea[i]);
			scrollPane.setBounds(20 + (i*180), 90, 160, 100);
			textarea[i].setFont(fuente);
			textarea[i].setEditable(false);
			textarea[i].setLineWrap(true);
			textarea[i].setWrapStyleWord(true);
			
			contentPane.add(scrollPane);
		}
	}
	
	public static void configurarTextAreaOrdenado() {
		textareaOrdenado = new JTextArea();
		textareaOrdenado.setBounds(20, 220, 340, 120);
		textareaOrdenado.setFont(fuente);
		textareaOrdenado.setEditable(false);
		
		contentPane.add(textareaOrdenado);
	}
	
	public void ejecutar() {
		ejecutarAcciones = new Controlador_1(this);
		ejecutarAcciones.escucharEventos();
	}

}