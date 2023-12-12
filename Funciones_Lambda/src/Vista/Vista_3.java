package Vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.jtattoo.plaf.luna.LunaLookAndFeel;

import Controlador.Controlador_3;

public class Vista_3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	public static JButton botonAniadir;
	public static JTextArea textarea;
	protected static Font fuente = new Font("Arial", Font.PLAIN, 21);
	public static Vista_3 vista;
	private Controlador_3 ejecutarAcciones;
	
	
	public Vista_3() {
		setTitle("Fecha Actual Lambda");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 100);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        try {
            //Se establece el Look and Feel de JTattoo (LunaLookAndFeel en este caso)
            UIManager.setLookAndFeel(new LunaLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
		
        configurarTextArea();
        configurarBotones();
		ejecutar();
	}
	
	private static void configurarTextArea() {
		textarea = new JTextArea();
		textarea.setBounds(10, 15, 400, 25);
		textarea.setFont(fuente);
		textarea.setEditable(false);
		
		contentPane.add(textarea);
	}
	
	private static void configurarBotones() {
		botonAniadir = new JButton("Fecha Actual");
		botonAniadir.setFont(fuente);
		botonAniadir.setBounds(450, 5, 150, 50);
		
		contentPane.add(botonAniadir);
	}
	
	public void ejecutar() {
		ejecutarAcciones = new Controlador_3(this);
		ejecutarAcciones.escucharEventos();
	}

}