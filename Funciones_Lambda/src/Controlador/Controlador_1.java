package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Vista.Vista_1;

public class Controlador_1 implements ActionListener {
	
	static Vista_1 vista;
	static List<Integer> numerosPrimos = new ArrayList<>();
	
	public Controlador_1(Vista_1 vista_interraz_1) {
		Vista_1.vista = vista_interraz_1;
	}
	
	public void escucharEventos() {
		Vista_1.botonAniadir.addActionListener(this);
	}
	
	//Método para comprobar si un número es primo
    public static boolean esPrimo(int numero) {
        if (numero <= 1) return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }
	
	//Método utilizado para ordenar de forma descendente los números primos previamente obtenidos
    public static void ordenarPrimos() {
        numerosPrimos.sort((a, b) -> b.compareTo(a));
    }
    
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(Vista_1.botonAniadir)) {
			String entrada = Vista_1.textfield.getText().trim();
			if (!entrada.isEmpty()) {
				try {
					int numero = Integer.parseInt(entrada);
					if (esPrimo(numero)) {
						numerosPrimos.add(numero);
						//Si el número es primo, lo añadimos al primer textarea
						Vista_1.textarea[0].setText(Vista_1.textarea[0].getText() + numero + ", ");
						
						/**
						 * Se actualiza el textarea encargado de colecionar los números primos y
						 * ordenarlos de forma descendiente.
						 */
						ordenarPrimos();
						Vista_1.textareaOrdenado.setText(numerosPrimos.toString());
					} else {
						//Si el número no es primo, lo añadimos al segundo textarea
						Vista_1.textarea[1].setText(Vista_1.textarea[1].getText() + numero + ", ");
					}
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(null, "Ingresa un número válido");
				}
				
				//Una vez procesada la entrada, se limpia el campo de texto
				Vista_1.textfield.setText("");
				Vista_1.textfield.requestFocusInWindow();
			}
		}
	}
	
	
}