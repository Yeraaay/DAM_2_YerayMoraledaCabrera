package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import Vista.Vista_3;

public class Controlador_3 implements ActionListener {

	static Vista_3 vista;
	static SimpleDateFormat formatoFecha;
	static String fechaActual;

	public Controlador_3(Vista_3 vista_interfaz_3) {
		Vista_3.vista = vista_interfaz_3;
	}

	public void escucharEventos() {
		Vista_3.botonAniadir.addActionListener(this);
	}

	//Método para obtener la fecha actual
	private static String configurarFecha() {
		formatoFecha = new SimpleDateFormat("'Las' HH:mm 'del' dd 'de' MMMM 'del' yyyy");
		fechaActual = formatoFecha.format(new Date());

		return fechaActual;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Vista_3.botonAniadir) {
			Vista_3.textarea.setText(null);
			Vista_3.textarea.setText(configurarFecha());
		}
	}


}