package App_troll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Aplicacion_troll {

	private JFrame frame;
	private JButton botonPrincipal;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Aplicacion_troll window = new Aplicacion_troll();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Aplicacion_troll() {
		app_troll();
	}

	private void app_troll() {
		frame = new JFrame();
		frame.setSize(500, 200);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //Deshabilito el cierre del programa
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setVisible(true);

		// Botón principal
		botonPrincipal = new JButton("¿Quieres jugar a un juego? \n"
				+ "Encuentra la llave");
		botonPrincipal.setBounds(50, 50, 350, 80);
		botonPrincipal.setBackground(Color.white);
		botonPrincipal.setForeground(Color.black);
		botonPrincipal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Creamos ventanas en posiciones aleatorias
				crearVentanasAleatorias();
				frame.setEnabled(false);
				frame.setVisible(true);
			}
		});
		frame.getContentPane().add(botonPrincipal);
	}

	private void crearVentanasAleatorias() {
		Random random = new Random();
		int numVentanas = random.nextInt(120) + 1; //Crea entre 1 y 120 ventanas
		List<JFrame> ventanas = new ArrayList<>();

		//Para obtener el tamaño total de la pantalla hacemos lo siguiente
		GraphicsEnvironment tamanio = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] pantalla = tamanio.getScreenDevices();

		for (int i = 0; i < numVentanas; i++) {
			int screenIndex = random.nextInt(pantalla.length);
			GraphicsDevice screen = pantalla[screenIndex];
			Rectangle bounds = screen.getDefaultConfiguration().getBounds();

			JFrame ventana = new JFrame();
			ventana.setBounds(bounds.x + random.nextInt(bounds.width - 50), bounds.y + random.nextInt(bounds.height - 20), 300, 150);
			ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			ventana.setVisible(true);

			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask() {
				public void run() {
					int newWidth = random.nextInt(150) + 100;
					int newHeight = random.nextInt(100) + 50;
					ventana.setSize(newWidth, newHeight);
				}
			}, 1000, 1000);

			//Creamos el boton llave con caracteristicas diferentes
			if (i == 0) {
				int llaveX = bounds.x + random.nextInt(bounds.width - 200);
				int llaveY = bounds.y + random.nextInt(bounds.height - 50);

				JButton botonLlave = new JButton("Llave");
				botonLlave.setBounds(llaveX, llaveY, 5, 5);
				botonLlave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//Cerramos todas las ventanas
						for (JFrame v : ventanas) {
							v.dispose();
						}
					}
				});
				ventana.getContentPane().add(botonLlave);
			}

			//Hacemos que las ventanas parpadeen
			Timer blinkTimer = new Timer();
			blinkTimer.scheduleAtFixedRate(new TimerTask() {
				private boolean isVisible = true;
				public void run() {
					ventana.setVisible(isVisible);
					isVisible = !isVisible;
				}
			}, 0, 500);

			ventanas.add(ventana);
		}
	}
}