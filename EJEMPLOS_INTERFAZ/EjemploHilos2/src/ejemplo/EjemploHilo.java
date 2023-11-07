package ejemplo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



	

	public  class  EjemploHilo extends  JFrame  implements  ActionListener {
		
		public static void main( String []args ){
			

			new EjemploHilo();
		}
		
		public EjemploHilo(){
			//Datos del JFrame
			setTitle("Ejemplo Hilo");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLayout(null);
			setSize(700,700);
			setLocationRelativeTo(null);

			//Boton que funciona como el jugador
			JButton jugador = new JButton();
			jugador.addActionListener(this);
			jugador.setIcon( new ImageIcon( getClass().getResource("/imagenes/jugador.png") ) );
			jugador.setBounds( 400,10,120,300 );
			this.add(jugador);
			
			//Porteria
			JLabel porteria = new JLabel();
			porteria.setIcon( new ImageIcon( getClass().getResource("/imagenes/porteria.jpg") ) );
			porteria.setBounds( 50,25,200,200 );
			this.add(porteria);
			
			//Color de fondo del JFrame
			getContentPane().setBackground( new Color(112,198,0) );
			setVisible(true);
		}
		
		public void actionPerformed( ActionEvent ev ){
			//Cada vez que se presione el poton -jugador- crea una nueva pelota
			//Para esto se crea un hilo
			Pelota pelota = new Pelota();
			this.add(pelota);
			this.repaint();
			//Creacion del hilo
			Thread moverPelota = new Thread(pelota);
			//Llamada al hilo para ejecutar.
			moverPelota.start();
		}
	}