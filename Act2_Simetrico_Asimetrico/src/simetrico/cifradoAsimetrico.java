package simetrico;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class cifradoAsimetrico {

	static Scanner sc = new Scanner(System.in);
	static KeyPair parejaClaves;
	static String textoSinCifrar;
	static KeyPairGenerator keyPairGen;
	static String textoCifrado;
	static String textoDescifrado;
	
	/*
	 * Este menú dará las opciones al usuario de cifrar un mensaje, que se cifrará con
	 * unas claves generadas inicialmente. Luego tendrá la opción de descifrar, que
	 * en el caso de no tener ningún texto cifrado previamente, le pedirá al usuario
	 * la opción de agregar un texto.
	 * Si pulsamos el "4" en el menú sin haber cifrado o descifrado antes un texto,
	 * nos mostrará por pantalla un mensaje nulo, pues hasta que no cifremos o descrifremos
	 * algo, permancerá nulo por defecto, cuando completemos las opciones necesarias,
	 * nos mostrará un resultado correcto del cifrado y descifrado.
	 */
	public static void menu() {
		String opcion = "";

		do {
			System.out.println("\nElige una opción: \n"
					+ "(PULSA 1) Cifrar un mensaje. \n"
					+ "(PULSE 2) Descrifrar un mensaje. \n"
					+ "(PULSE 3) Regenerar clave pública y clave privada. \n"
					+ "(PULSA 4) Mostrar Datos. \n"
					+ "(PULSE 0) Salir. \n");
			opcion = sc.nextLine();

			switch (opcion) {
			case "1":
				cifrarAsimetrico();
				break;
			case "2":
				
				descifrarAsimetrico();
				break;
			case "3":
				generarKeysAsimetrica();
				break;
			case "4":
				mostrarDatos();
				break;
			case "0":
				System.exit(0);
				System.out.println("Adiós, que tenga un buen día!");
			default:
				System.out.println("Error, vuelve a intentarlo!");
				break;
			}
		}while(true);
	}

	public static void generarKeysAsimetrica() {

		try {

			//Instanciamos la clase keyPairGenerator
			keyPairGen = KeyPairGenerator.getInstance("RSA");

			//Inicializamos el "KeyPairGen" a 2048 bytes
			keyPairGen.initialize(2048);

			//Generamos una pareja de claves
			parejaClaves = keyPairGen.generateKeyPair();
			
			//Mostramos un aviso por pantalla al usuario de la creación de las claves
			System.out.println("Claves generadas correctamente!");

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}

	public static String cifrarAsimetrico() {
		System.out.println("Introduce un texto que quieras cifrar: ");
		textoSinCifrar = sc.nextLine();

		try {
			// Instanciamos la clase "Cipher"
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, parejaClaves.getPublic());

			//Convertimos a Bytes el cifrado
			byte[] textoSinCifrarBytes = textoSinCifrar.getBytes();

			//Ciframos el array de bytes
			byte[] textoCifradoBytes = cipher.doFinal(textoSinCifrarBytes);

			//Guardamos el texto cifrado en una variable para mostrarla más tarde por pantalla
			textoCifrado = Base64.getEncoder().encodeToString(textoCifradoBytes);
			
			//Mostramos por pantalla la validación del texto cifrado
			System.out.println("Texto cifrado correctamente");

			//Devolvemos la cadena de texto encriptada
			return textoCifrado;
			

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String descifrarAsimetrico() {
		byte[] textoCifradoBytes = null;
		
		try {
			//Instanciamos la clase "Cipher"
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, parejaClaves.getPrivate());
			
			/*
			 * Comprobamos que exista un texto ya cifrado. En caso contrario,
			 * el programa pedirá al usuario si quiere introducir un texto a cifrar
			 * o no.
			 */
			if (textoCifrado != null) {
				//Pasamos a bytes el texto cifrado
				textoCifradoBytes = Base64.getDecoder().decode(textoCifrado);
				
				//Desencriptamos el array de Bytes
				byte[] textoDesencriptado = cipher.doFinal(textoCifradoBytes);

				// Al igual que con el texto cifrado, guardamos el texto descifrado en una variable para mostrarla por pantalla
				textoDescifrado = new String(textoDesencriptado, StandardCharsets.UTF_8);

				//Mostramos por pantalla si el descifrado se ha realizado correctamente
				System.out.println("Texto descifrado con éxito!");
			} else {
				System.out.println("No tienes un texto cifrado todavía,\n¿Quieres añadirlo? (si / no)");
				String opciones = sc.nextLine();
				if (opciones.equalsIgnoreCase("si")) cifrarAsimetrico();
				else if(opciones.equalsIgnoreCase("no")) System.out.println("Vaya, la próxima será...");
			}
			
			
			//Devolvemos el resultado tras realizar el método
			return textoDescifrado;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// Método que nos muestra los datos por consola para poder ver el texto original con su cifrado y descrifrado
	public static void mostrarDatos() {
		System.out.println("Texto original: " + textoSinCifrar);
		System.out.println("Texto cifrado: " + textoCifrado);
		System.out.println("Texto descifrado: " + textoDescifrado);
		System.out.println("El algoritmo utilizado ha sido: " + keyPairGen.getAlgorithm());
	}

	public static void main(String[] args) {
		generarKeysAsimetrica();
		menu();
	}


}