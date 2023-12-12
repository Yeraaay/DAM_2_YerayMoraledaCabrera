package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import Vista.Vista_5;

public class Controlador_5 implements ActionListener {

	static Vista_5 vista;
	List<Integer> listaNumeros = new Random().doubles(20, 0, 100).mapToInt(d -> (int) d).boxed().collect(Collectors.toList());
	static List<List<Integer>> subListas;
	static int sumaTotalPartes;

	public Controlador_5(Vista_5 vista_interfaz_5) {
		Vista_5.vista = vista_interfaz_5;
	}

	public void escucharEventos() {
		Vista_5.botonAniadir.addActionListener(this);
	}

	// Método para dividir la lsita de números en la cantidad de partes que el usuario indique
	public static List<List<Integer>> dividirLista(List<Integer> numeros, int cantidad) {
		int tamanioSubLista = (int) Math.ceil((double) numeros.size() / cantidad);
		List<List<Integer>> subListas = numeros.stream().collect(Collectors.groupingBy(i -> (numeros.indexOf(i) / tamanioSubLista)))
				.values().stream().collect(Collectors.toList());

		for (int i = 0; i < cantidad; i++) {
			if (i < subListas.size()) {
				List<Integer> subLista = subListas.get(i);
				StringBuilder contenidoTextArea = new StringBuilder();
				for (Integer num : subLista) {
					contenidoTextArea.append(num).append(", ");
				}
				Vista_5.textarea[i].setText(contenidoTextArea.toString());
			}
		}
		return subListas;
	}

	// Método para sumar y mostrar las sumas de las partes y el total de partes
	public static void mostrarSumas() {
		StringBuilder sumaPorPartes = new StringBuilder();
		sumaTotalPartes = 0;

		for (List<Integer> subLista: subListas) {
			int suma = subLista.stream().mapToInt(Integer::intValue).sum();
			sumaPorPartes.append("Suma de la parte ").append(subListas.indexOf(subLista) + 1).append(": ").append(suma).append("\n");
			sumaTotalPartes += suma;
		}

		Vista_5.textareaSumas.setText(sumaPorPartes.toString() + "\nSuma Total de las partes: " + sumaTotalPartes + ".");
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Vista_5.botonAniadir) {

					int cantidadDivisores = Integer.parseInt(Vista_5.textfield.getText());
					Vista_5.configurarTextArea();
					Vista_5.configurarTextAreaSumas();
					subListas = dividirLista(listaNumeros, cantidadDivisores);
					mostrarSumas();
				} else JOptionPane.showMessageDialog(null, "Ingresa un número válido");
	}

	private void reiniciarValores() {
		Vista_5.textfield.setText("");
		if (Vista_5.textarea != null) {
			for (JTextArea area : Vista_5.textarea) {
				area.setText("");
			}
		}
		if (Vista_5.textareaSumas != null) {
			Vista_5.textareaSumas.setText("");
		}
	}


}