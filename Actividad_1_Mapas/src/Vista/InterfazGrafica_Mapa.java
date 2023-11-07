package Vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class InterfazGrafica_Mapa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String[] arraybotones = {"Añadir Nota", "Eliminar Nota", "Corregir Nota", "Ver notas"};
	public JButton botones[];
	
	
	public InterfazGrafica_Mapa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 400); 
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		botones = new JButton[arraybotones.length];
		for (int i=0; i<arraybotones.length; i++) {
			botones[i] = new JButton(arraybotones[i]);
			botones[i].setSize(80, 20);
			
			setFocusableWindowState(false);
			
			contentPane.add(botones[i]);
		}
		
		DefaultTableModel modeloTabla = new DefaultTableModel();
		JTable tabla = new JTable(modeloTabla);
		
		JScrollPane scrollPane = new JScrollPane(tabla);
		add(scrollPane);
	}

}