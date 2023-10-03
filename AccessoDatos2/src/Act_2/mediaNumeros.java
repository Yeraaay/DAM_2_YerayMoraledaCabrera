package Act_2;
import java.io.*;
import java.util.*;

public class mediaNumeros {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void MediaNumeros() {
		
		FileOutputStream fos = null;
		DataOutputStream escribir = null;
		
		int cantidad_numeros;
		int numeros;
		Double media = 0.0;
		
		try {
			fos = new FileOutputStream("ficheros//numeros.txt");
			escribir = new DataOutputStream(fos);
			
			//Pedimos al usuario que introduzca la cantidad de numeros que desea introducir
			System.out.print("Introduce la cantidad de numeros que quieras escribir: ");
			cantidad_numeros = sc.nextInt();
			
			for(int i=0 ; i<cantidad_numeros ; i++) {
				System.out.println("Introduce el numero " + (i+1) + ": ");
				numeros = sc.nextInt();
				media = media + numeros;
				escribir.writeInt(numeros);
			}
			
			media = media / cantidad_numeros;
			System.out.println("La media de los numeros introducidos es: " + media);
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage().toString());
		} catch (IOException e) {
			System.out.println(e.getMessage().toString());
		} finally {
			try {
				if (escribir != null) escribir.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage().toString());
			}
		}
		
	}
	
	public static void main(String[] args) {
		MediaNumeros();
	}
	
	
}