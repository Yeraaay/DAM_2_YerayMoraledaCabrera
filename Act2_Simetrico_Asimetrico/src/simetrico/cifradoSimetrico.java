package simetrico;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class cifradoSimetrico {

	static Scanner sc = new Scanner(System.in);
	static SecretKey secretKey;
	static String cadena;

	public static void menu() {
		String opcion;

		System.out.println("Introduce el contenido que quieras cifrar: ");
		cadena = sc.nextLine();
		System.out.println("Gracias!,\n¿Tienes una clave? (si / no)");
		opcion = sc.next();

		switch (opcion) {
		case "si":
			obtenerClave();
			break;
		case "no":
			generarClave();
			break;
		default:
			System.out.println("Error, vuelve a intentarlo!");
			break;
		}



	}

	public static void obtenerClave() {
		System.out.println("Introduce la clave ya existente: ");
		String clave = sc.next();
		byte[] claveBytes = clave.getBytes();

		//Realizamos el cifrado de la cadena de texto introducida
		/*
		 * Generamos una instancia de "SecretKeySpec", construyendo una "secretKey" a partir
		 * de los bytes proporcionados.
		 */
		secretKey = new SecretKeySpec(claveBytes, 0, claveBytes.length, "AES");
		/*
		 * La clase "SecretKeySpec" tiene 2 parámetros fundamentales, "hashedBytes" y "AES",
		 * indicando la cadena de bytes que se quiere cifrar y el algoritmo utilizado para
		 * el mismo.
		 * Además, usa 2 parámetros adicionales indicando desde qué posición del array de
		 * bytes quiere que empiece a cifrar, y la longitud de bytes que quieres que se
		 * utilicen, en este caso, queremos cifrar todo el array de bytes(0, hashBytes.length)
		 */
	}

	public static void generarClave() {
		/*
		 * Generamos una clave secreta mediante keyGenerator para luego poder cifrarla
		 * y descrifrarla
		 */

		try {
			//Obtenemos una instancia de KeyGenerator para el cifrado "AES"
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");

			//Podemos seleccionar la cantidad de bytes que ocupe dicha calve secreta
			keyGenerator.init(128);
			
			/*
			 * Generamos la clave secreta y la guardamos en una variable estática para
			 * poder manejarla fuera del método para cifrar y descifrar una cadena de texto
			 */
			secretKey = keyGenerator.generateKey();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	public static String cifrarSimetrico(SecretKey secretKey, String textoSinCifrar) {

		try {
			//Creamos una instancia de la clase "Cipher"
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);

			//Convertimos el contenido a bytes
			byte[] textoSinCifrarBytes = textoSinCifrar.getBytes();

			//Ciframos el contenido
			byte[] textoCifradoBytes = cipher.doFinal(textoSinCifrarBytes);
			
			return Base64.getEncoder().encodeToString(textoCifradoBytes);
			
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String descifrarSimetrico(SecretKey secretKey, String textoCifrado) {

		try {
			//Inicializamos el Cipher para descrifrar el contenido
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);

			byte[] textoCifradoBytes = Base64.getDecoder().decode(textoCifrado);

			//Desciframos el contenido
			byte[] textoDescifradoBytes = cipher.doFinal(textoCifradoBytes);

			return new String(textoDescifradoBytes, StandardCharsets.UTF_8);


		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void mostrarDatos() {

		String textoCifrado = cifrarSimetrico(secretKey, cadena);
		String textoDescifrado = descifrarSimetrico(secretKey, textoCifrado);

		System.out.println("\nTexto original: " + cadena);
		System.out.println("Texto cifrado: " + textoCifrado);
		System.out.println("Texto descrifrado: " + textoDescifrado);
		System.out.println("\nAlgoritmo usado para cifrar y descifrar el contenido: " + secretKey.getAlgorithm());

	}

	public static void main(String[] args) {
		menu();
		mostrarDatos();
	}


}