package Principal;

import java.util.Random;

public class Algoritmo {

	
	public static boolean buscarEnMatriz(int[][] matriz, int valor) {
		int filas = matriz.length;
		int columnas = matriz[0].length;

		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (matriz[i][j] == valor) {
					return true; //El valor se encontró en la matriz
				}
			}
		}
		return false; //El valor no se encontró en la matriz
	}

	public static void main(String[] args) {
		int[][] matriz = new int[3][3];
		
		//Se generan números random en la matriz
		Random random = new Random();
		for (int i=0; i< matriz.length; i++) {
			for (int j=0; j < matriz[i].length; j++) {
				matriz[i][j] = random.nextInt(20) + 1; //Números entre el 1 y el 20
			}
		}
		
		//Se genera un número aleatorio a buscar
		int valorBuscado = random.nextInt(20) + 1; //Número entre el 1 y el 20

		boolean encontrado = buscarEnMatriz(matriz, valorBuscado);
		
		//Se muestra la matriz y el valor buscado para comprobar sus elementos
        System.out.println("Matriz:");
        for (int[] fila : matriz) {
            for (int i = 0; i < fila.length; i++) {
                System.out.print("[" + fila[i] + "]");
                if (i != fila.length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println("");
        }
        System.out.println("Valor buscado: " + valorBuscado);
        
		if (encontrado) {
			System.out.println("\nEl valor " + valorBuscado + " está presente en la matriz.");
		} else {
			System.out.println("\nEl valor " + valorBuscado + " no se encuentra en la matriz.");
		}
	}


}