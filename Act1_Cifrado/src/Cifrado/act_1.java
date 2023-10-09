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
	
	/*
	 * Creamos un menú que nos muestra por pantalla 2 opciones, la primera para introducir un texto en el fichero
	 * que servirá de contraseña. La otra opción le da la oportunidad al usuario de introducir una nueva
	 * contraseña que comparamos con la contraseña ya existente en el fichero. Si ambas contraseñas coinciden,
	 * se mostrará por consola un mensaje diciendo que está correcto, en caso contrario, mostrará por consola
	 * un mensaje diciendo que está incorrecto
	 */
	public static void menu() {

		String opcion = "";

		do {

			System.out.println("\n MENU \n"
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
			
			//Escribimos la cadena String en el fichero
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
						
			//Leemos la contraseña almacenada en el fichero almacenndola en una variable con StringBuilder
			StringBuilder passwordAlmacenada = new StringBuilder();
			while (leer.available() > 0) {
				char c = leer.readChar();
				passwordAlmacenada.append(c);
			}
			
			//Pedimos al usuario una nueva contraseña
			System.out.println("Introduce la contraseña a probar: ");
			String password = sc.nextLine();
			
			//Comparamos la nueva contraseña con la contraseña ya existente en el fichero
			if (password.equals(passwordAlmacenada.toString())) System.out.println("Correcto, Acceso permitido!");
			else System.out.println("Incorrecto, Acceso denegado!");
			
			
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
	
	public static void main(String[] args) {
		menu();
	}


}