package Cifrado;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class act_2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Le pedimos al usuario que introduzca una contraseña
        System.out.println("Por favor, ingrese una contraseña:");
        String password = sc.nextLine();

        String cifrado = cifrarPassword(password);

        System.out.println("Contraseña almacenada con éxito.");

        // Almacenar la contraseña cifrada en un archivo
        guardarCifradoEnArchivo(cifrado);

        while (true) {
            System.out.println("Por favor, ingrese la contraseña para acceder al sistema:");
            String inputPassword = sc.nextLine();
            if (verificarPassword(inputPassword, cifrado)) {
                System.out.println("Contraseña correcta. Acceso permitido.");
                break;
            } else {
                System.out.println("Contraseña incorrecta. Acceso denegado.");
            }
        }

        // Cerramos el Scanner
        sc.close();
    }

    public static String cifrarPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                hexString.append(String.format("%02x", b));
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al crear el hash de la contraseña: " + e.getMessage());
        }
    }

    public static boolean verificarPassword(String pw1, String pw2) {
        String cifrado = cifrarPassword(pw1);
        return cifrado.equals(pw2);
    }

    public static void guardarCifradoEnArchivo(String cifrado) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ficheros//password.txt"))) {
            writer.write(cifrado);
            System.out.println("Contraseña cifrada almacenada en el archivo password.txt.");
        } catch (IOException e) {
            System.err.println("Error al guardar la contraseña cifrada en el archivo: " + e.getMessage());
        }
    }
}