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
	
	public static void menu() {
		String opcion = "";
		
		System.out.println("Elige una opción: \n"
				+ "(PULSA 1) Cifrar un mensaje. \n"
				+ "(PULSE 2) Descrifrar un mensaje. \n"
				+ "(PULSE 3) Regenerar clave pública y clave privada. \n"
				+ "(PULSE 0) Salir. \n");
		opcion = sc.nextLine();
		
		switch (opcion) {
		case "1":
			cifrarAsimetrico();
			break;
		case "2":
			descifrarAsimetrico(textoSinCifrar);
			break;
		case "3":
			generarKeysAsimetrica();
			break;
		case "0":
			System.exit(0);
			System.out.println("Adiós, que tenga un buen día!");
		default:
			System.out.println("Error, vuelve a intentarlo!");
			break;
		}
	}
	
	public static void generarKeysAsimetrica() {
		
		try {
			
			//Instanciamos la clase keyPairGenerator
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
			
			//Inicializamos el "KeyPairGen" a 2048 bytes
			keyPairGen.initialize(2048);
			
			//Generamos una pareja de claves
			parejaClaves = keyPairGen.generateKeyPair();
			
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
			
			//Devolvemos la cadena de texto encriptada
			return Base64.getEncoder().encodeToString(textoCifradoBytes);
			
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
	
	public static String descifrarAsimetrico(String textoCifrado) {
		
		try {
			//Instanciamos la clase "Cipher"
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, parejaClaves.getPrivate());
			
			byte[] textoCifradoBytes = Base64.getDecoder().decode(textoCifrado);
			
			//Desencriptamos el array de Bytes
			byte[] textoDesencriptado = cipher.doFinal(textoCifradoBytes);
			
			return new String(textoDesencriptado, StandardCharsets.UTF_8);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		generarKeysAsimetrica();
		menu();
	}
	
	
}