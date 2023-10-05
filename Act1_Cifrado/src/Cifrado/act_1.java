package Cifrado;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class act_1 {

	static Scanner sc = new Scanner(System.in);
	//Inicialziamos a "null" para poder manejar los errores dentro de un "try-catch"
	static FileOutputStream fos = null;
	static DataOutputStream escribir = null;

	static FileInputStream fis = null;
	static DataInputStream leer = null;

	public static void menu() {

		String opcion = "";

		do {

			System.out.println("\n MENU \n "
					+ "(PULSA 1) Introducir contraseña. \n"
					+ "(PULSA 2) Probar contraseña. \n"
					+ "(PULSA 0) Salir. \n");
			opcion = sc.nextLine();

			switch (opcion) {
			case "1":
				creacionFichero();
				break;
			case "2":
				probarPassword();
				break;
			case "0":
				System.out.println("Hasta luego!");
				System.exit(0);
			default:
				System.out.println("Error, vuelve a intentarlo.");
				break;
			}
		} while (true);

	}

	public static void creacionFichero() {

		try {
			fos = new FileOutputStream("ficheros//password.txt");
			escribir = new DataOutputStream(fos);

			System.out.println("Introduce una contraseña: ");
			String cadena = sc.nextLine();

			for (char c: cadena.toCharArray()) {
				escribir.writeChar(c);
			}

			System.out.println("Se ha creado la contraseña correctamente!");
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage().toString());
		} catch (IOException e) {
			System.out.println(e.getMessage().toString());
		} finally {
			try {
				if(leer != null) leer.close();
				if(fis != null) fis.close();
				if(escribir != null) escribir.close();
				if(fos != null) fos.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage().toString());
			}
		}


	}
	
	public static void probarPassword() {
		
		try {
			fis = new FileInputStream("ficheros//password.txt");
			leer = new DataInputStream(fis);
			
			System.out.println("Introduce la contraseña a probar: ");
			String password = sc.nextLine();
			
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage().toString());
		} catch (IOException e) {
			System.out.println(e.getMessage().toString());
		} finally {
			try {
				if(leer != null) leer.close();
				if(fis != null) fis.close();
				if(escribir != null) escribir.close();
				if(fos != null) fos.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage().toString());
			}
		}
	}


}