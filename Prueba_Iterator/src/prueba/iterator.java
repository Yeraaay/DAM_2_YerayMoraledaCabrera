package prueba;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class iterator {

	static Scanner sc = new Scanner(System.in);
	static int i=0;
	static int tamanyo = 5;

	//Creamos un Map
	static Map<Integer, String> mapa = new HashMap<>();

	public static void Menu() {
		String opcion = "";

		do {

			System.out.println("\n\n MENU \n"
					+ "(PULSA 1) Insertar datos. \n"
					+ "(PULSA 2) Eliminar datos. \n"
					+ "(PULSA 3) Mostrar coleccion. \n"
					+ "(PULSA 0) Salir.");
			opcion = sc.nextLine();

			switch (opcion) {
			case "1":
				insertarDatos();
				break;
			case "2":
				eliminarDatos();
				break;
			case "3":
				mostrarDatos();
				break;
			case "0":
				System.out.println("Hasta luego!");
				System.exit(0);
				break;
			default:
				System.out.println("Error, intentalo de nuevo!");
				break;
			}

		}while(true);

	}

	public static void mostrarDatos() {

		//Recorremos nuestro hashMap con un bucle "for-each"
		for(Map.Entry<Integer, String> elemento: mapa.entrySet()) {

			//Obtenemos la clave y el valor y lo guardamos en variables
			int clave = elemento.getKey();
			String valor = elemento.getValue();

			//Mostramos los datos por consola
			System.out.println("Clave: " + clave + " Valor: " + valor);
		}


	}

	public static void insertarDatos() {

		if(mapa.size() >= tamanyo) {
			System.out.println("Lo sentimos, no hay más capacidad!");
		}else {
			System.out.print("Introduce el valor que quiera introducir: ");
			String valor_nuevo = sc.nextLine();
			
			mapa.put((i+1), valor_nuevo);
			i++;
		}
	}

	public static void eliminarDatos() {

		System.out.print("Introduce la clave del valor que desee eliminar: ");
		int clave = sc.nextInt();
		sc.nextLine();
		if(mapa.containsKey(clave)) mapa.remove(clave);
		else System.out.println("No se ha podido encontrar la clave: '" + clave +"', intentalo de nuevo.");
	}

	public static void main(String[] args) {
		Menu();
	}


}