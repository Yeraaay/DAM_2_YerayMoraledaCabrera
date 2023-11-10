package Vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.jtattoo.plaf.texture.TextureLookAndFeel;

import Controlador.Controlador;

public class Vista extends JFrame {

    private static final long serialVersionUID = 1L;
    private static JPanel contentPane;
    private static String[] arrayBotones = {"Añadir Producto", "Ver producto", "Vender", "Eliminar Producto", "Ver ventas"};
    public static JButton botones[];
    private Controlador ejecutarAcciones;
    public static DefaultTableModel modeloTabla;
    public static JTable tabla;
    private static JScrollPane scrollPane;
	public static Vista vista;
    

    public static void configurarTabla() {
        modeloTabla = new DefaultTableModel();
        tabla = new JTable(modeloTabla);

        scrollPane = new JScrollPane(tabla);
        contentPane.add(scrollPane);
    }

    public static void configurarBotones() {
        botones = new JButton[arrayBotones.length];
        for (int i = 0; i < arrayBotones.length; i++) {
            botones[i] = new JButton(arrayBotones[i]);
            botones[i].setFont(botones[i].getFont().deriveFont(14.0f));

            contentPane.add(botones[i]);
        }
    }

    private void ejecutar() {
        ejecutarAcciones = new Controlador(this);
        ejecutarAcciones.escucharEventos();
    }

    public Vista() {
    	setTitle("Mapa Inventario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 750, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        try {
            //Se establece el Look and Feel de JTattoo (TextureLookAndFeel en este caso)
            UIManager.setLookAndFeel(new TextureLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        configurarBotones();
        configurarTabla();
        ubicarComponentes();
        ejecutar();
    }

    private void ubicarComponentes() {
        int buttonWidth = 135;
        int buttonHeight = 30;
        int margin = 10;

        for (int i = 0; i < arrayBotones.length; i++) {
            botones[i].setSize(buttonWidth, buttonHeight);
            botones[i].setLocation(margin + i * (buttonWidth + margin), 10);
        }

        int tableWidth = 460;
        int tableHeight = 300;
        scrollPane.setBounds((getWidth() - tableWidth) / 2, 50, tableWidth, tableHeight);
    }

    public static void main(String[] args) {
        Vista frame = new Vista();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}