package Act_3;
import java.io.*;
import java.util.*;

public class copiaFicheros {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void CopiaFicheros() {
		
		//Inicializamos los métodos en null para luego trabajar con ellos en un "try-catch"
		//para controlar los posibles problemas
		FileOutputStream fos = null;
		DataOutputStream escribir = null;
		
		FileInputStream fis = null;
		DataInputStream leer = null;
		
		String contenido = "";
		
		try {
			
			fos = new FileOutputStream("ficheros//lectura.txt");
			escribir = new DataOutputStream(fos);
			
			System.out.print("\nIntroduce lo que quieras: ");
			contenido = sc.nextLine();
			escribir.writeUTF(contenido);
			sc.close();
			
			fis = new FileInputStream("ficheros//lectura.txt");
			leer = new DataInputStream(fis);
			
			fos = new FileOutputStream("fichero_copia//escritura.txt");
			escribir = new DataOutputStream(fos);
			
			//Creamos un array de bytes para guardar el contenido del fichero, cuyo tamaño
			//es igual a los bytes disponibles dentro del fichero
			byte[] array = new byte[leer.available()];
			//Leemos dicho array
			leer.read(array);
			//Escribimos el array, cuyos datos son recogidos y guardados en las rutas indicadas
			escribir.write(array);
			
			
			//Indicamos que se ha copiado correctamente
			System.out.println("Fichero copiado con exito!");
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage().toString());
		} catch (IOException e) {
			System.out.println(e.getMessage().toString());
		} finally {
			try {
				if (leer != null) leer.close();
				if (fis != null) fis.close();
				if (escribir != null) escribir.close();
				if (fos != null) fos.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage().toString());
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		CopiaFicheros();
	}
	
}