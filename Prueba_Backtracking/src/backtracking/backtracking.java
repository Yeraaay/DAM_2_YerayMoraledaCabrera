package backtracking;

import javax.swing.JOptionPane;

public class backtracking {

	public static String backtracking(int numeroInt) {
		if (numeroInt < 10) {
			return Integer.toString(numeroInt);
		} else {
			int ultimoInt = numeroInt % 10;
			int numeroDivisor = numeroInt / 10;
			String descomposicion = backtracking(numeroDivisor);
			return descomposicion + " y " + ultimoInt;
		}
	}

	public static void main(String[] args) {
		String numeroString = JOptionPane.showInputDialog(null, "Introduce un número");
		int numero = Integer.parseInt(numeroString);
		
		JOptionPane.showMessageDialog(null, backtracking(numero));
	}


}