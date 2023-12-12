package Backtraking;

import java.util.ArrayList;

public class Backtraking_Diana {
	
	/**
	 * Método recursivo encargado de buscar las posibles soluciones del algoritmo
	 */	
	public static void dardosBacktraking(int[] regionesDiana, int totalPuntos, int dardos, int indice, ArrayList<Integer> solucionParcial, ArrayList<ArrayList<Integer>> soluciones) {
	    /**
	     * Se verifica que el total de puntos sea igual o inferior a 100 teniendo entre 1 y 5 dardos.
	     * Si se cumplen estas condiciones, se agrega la combinación a la lista de soluciones válidas.
	     */
		if (totalPuntos <= 100 && solucionParcial.size() > 0 && solucionParcial.size() <= 5) {
	        soluciones.add(new ArrayList<>(solucionParcial));
	    }
		
		/**
		 * Este condicional tiene como función detenerse si la suma de los puntos totales es superior a 100,
		 * al agotarse los dardos disponibles o si se han superado las regiones de la diana.
		 */
	    if (totalPuntos > 100 || dardos == 0 || indice == regionesDiana.length) {
	        return;
	    }
	    
	    /**
	     * Bucle que recorre las regiones de la diana, verificando si al lanzar un dardo en la región actual
	     * no se supera el límite de puntos disponibles.
	     * Si esto se cumple, se agrega el valor de las región a la lista de soluciones parciales, y así
	     * recursivamente para explorar todas las combinaciones posibles, eliminando el último dardo para así
	     * explorar más regiones.
	     */
	    for (int i = indice; i < regionesDiana.length; i++) {
	        if (totalPuntos - regionesDiana[i] >= 0) {
	            solucionParcial.add(regionesDiana[i]);
	            dardosBacktraking(regionesDiana, totalPuntos - regionesDiana[i], dardos - 1, i, solucionParcial, soluciones);
	            solucionParcial.remove(solucionParcial.size() - 1);
	        }
	    }
	}
	
	public static void main(String[] args) {
		/**
		 * Se declaran las variables funcionales del algoritmo, como un array de las distintas regiones existentes en
		 * la diana, el total de puntos y los dardos disponibles.
		 */
		int[] regionesDiana = {10, 25, 50, 75};
		int totalPuntos = 100;
		int dardos = 5;
		
		/**
		 * Además, se crean las listas que necesitaremos para nuestro algoritmo, como una lista con las soluciones
		 * parciales y otra con el conjunto de soluciones encontradas.
		 */
		ArrayList<ArrayList<Integer>> soluciones = new ArrayList<>();
		ArrayList<Integer> solucionParciales = new ArrayList<>();
		
		dardosBacktraking(regionesDiana, totalPuntos, dardos, 0, solucionParciales, soluciones);
		
		// Se muestra por pantalla todas las combinaciones posibles
		System.out.println("\n---Posibles combinaciones usando de 1 a 5 dardos---\n");
		int sumaSoluciones = 0;
		for (ArrayList<Integer> solucion: soluciones) {
			sumaSoluciones++;
			System.out.println(solucion);
		}
		System.out.println("\nEn total, hay " + sumaSoluciones + " posibles soluciones.");
	}
	
	
}