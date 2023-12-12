package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Vista.Vista_2;

public class Controlador_2 implements ActionListener {
	
	static Vista_2 vista;
	static List<String> listaNombres = new ArrayList<>();
	static List<Integer> cantidadCaracteres = new ArrayList<>();
	
	public Controlador_2(Vista_2 vista_interfaz_2) {
		Vista_2.vista = vista_interfaz_2;
	}
	
	public void escucharEventos() {
		Vista_2.botonAniadir.addActionListener(this);
	}
	
	// Método para calcular la cantidad de caractéres que tienen las cadenas de caractéres
	public static List<Integer> cantidadCaracteres() {
		cantidadCaracteres = listaNombres.stream().map(listaNombres -> listaNombres.length()).collect(Collectors.toList());
		
		return cantidadCaracteres;
	}
	
	// Método para sumar la cantidad total de caractéres obtenidos de las cadenas de texto anteriores
	public static int sumarCaracteres() {
		return cantidadCaracteres().stream().mapToInt(Integer::intValue).sum();
	}
    
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Vista_2.botonAniadir) {
			String texto = Vista_2.textfield.getText();
			listaNombres.add(texto);
			
			Vista_2.textarea[0].setText(Vista_2.textarea[0].getText() + texto + ", ");
			Vista_2.textfield.setText("");
			Vista_2.textarea[1].setText(cantidadCaracteres().toString());
			Vista_2.textareaSumas.setText(String.valueOf(sumarCaracteres()));
			
			Vista_2.textfield.requestFocusInWindow();
		}
	}
	
	
}