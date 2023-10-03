package AccesoDatos;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class AcessoDatos {
	
	//Introducimos las características necesarias para hacer la conexión con la base de datos
	private static final String usuario = "root";
	private static final String clave = "root";
	private static final String BD = "root";
	private static final String servidor = "localhost:3306/"+BD;
	private static final String url = "jdbc:mysql://"+servidor;

	private static final String controlador = "com.mysql.cj.jdbc.Driver";

	static Scanner sc = new Scanner(System.in);
	
	//Creamos la conexión con la base de datos
	public static Connection Conexion() {
		Connection conex = null;
		try {
			Class.forName(controlador);
			conex = DriverManager.getConnection(url,usuario,clave);
		}catch (Exception e) {
			System.out.println("Error al conectar a la BD \n" + e.getMessage().toString());
		}

		return conex;
	}
	
	//Creamos el cerrado de la conexión para evitar problemas con la base de datos
	public static void cerrarConex(Connection conex) {
		try {
			conex.close();
		}catch(SQLException e) {
			System.out.println(e.getMessage().toString());
		}
	}
	
	//Creamos un menuú
	public static void Menu() {

		String opcion = "";

		do {
			System.out.println("\n\n ELIGE UNA OPCION \n\n"
					+ "(PULSA 1) Dar de alta a un asistente en la base de datos. \n"
					+ "(PULSA 2) Cambiar el DNI de un asistente. \n"
					+ "(PULSA 3) Mostrar los datos de los asistentes. \n"
					+ "(PULSA 4) Eliminar un asistente. \n"
					+ "(PULSA 0) Salir. \n");

			opcion = sc.next();

			switch (opcion) {
			case "1":
				DarAlta();
				break;
			case "2":
				CambiarDNI();
				break;
			case "3":
				MostrarDatos();
				break;
			case "4":
				EliminarDatos();
				break;
			case "0":
				System.out.println("Hasta luego!");
				System.exit(0);
				break;
			default:
				System.out.println("Error, Intentalo de nuevo.");
			}

		} while (true);

	}
	
	//Metodo para dar de alta a un nuevo asistente
	public static void DarAlta() {
		Connection conex = Conexion();
		
		System.out.println("Introduce el DNI del nuevo asistente: ");
		String dni = sc.next();
		System.out.println("Introduce el nombre del nuevo asistente: ");
		String nombre = sc.next();
		System.out.println("Introduce el nombre del centro: ");
		String nombre_centro = sc.next();
		System.out.println("Introduce la edad del nuevo asistente: ");
		int edad = sc.nextInt();
		
		try {
	        String contenido = "INSERT INTO `Asistentes` VALUES (`" + dni + "`, `" + nombre + "`, `" + nombre_centro + "`, `" + edad + "`)";
	        Statement consulta = conex.createStatement();
	        ResultSet salida = consulta.executeQuery(contenido);
	        
	        System.out.println("El nuevo asistente ha sido añadido correctamente, gracias!");
	    } catch (SQLException ex) {
	    	System.out.println(ex.getMessage().toString());
	    }
	}
	
	//Metodo para cambiar el DNI del asistente
	public static void CambiarDNI() {
		Connection conex = Conexion();

		System.out.println("Seguro que quieres cambiar el DNI? (si / no)");
		String respuesta = sc.next();
		if (respuesta.equals("si")) {
			System.out.println("Introduce tu antiguo DNI: ");
			String dni_antiguo = sc.next();

			try {
				String dni_inicial = "";
				String contenido = "select '" + dni_inicial +"' ifrom Asistentes";
				Statement consulta = conex.createStatement();
				ResultSet salida = consulta.executeQuery(contenido);
				ResultSetMetaData metadata = salida.getMetaData();

				if(dni_inicial.equals(dni_antiguo)) {					
					System.out.println("Introduce el nuevo DNI: ");
					String dni_nuevo = sc.next();
				}

			} catch (SQLException e) {
				System.out.println(e.getMessage().toString());
			}



			System.out.println("El DNI ha sido actualizado, gracias!");

		} else if (respuesta.equals("no")) {
			System.out.println("Gracias, vuelva pronto!");
		} else System.out.println("Debe introducir una opción correcta!");
	}
	
	//Con este método podremos mostrar todos los datos de los asistentes
	public static void MostrarDatos() {
		Connection conex = Conexion();

		try {
			String contenido = "select * from Asistentes";
			Statement consulta = conex.createStatement();

			ResultSet salida = consulta.executeQuery(contenido);

			ResultSetMetaData metadata = salida.getMetaData();

			int contadorColumnas = metadata.getColumnCount();
			for (int i = 1; i < contadorColumnas; i++) {
				System.out.printf("%-15s",metadata.getColumnName(i));
			}
			
			System.out.println("\n");

			while(salida.next()) {
				for (int i = 1; i < contadorColumnas;i++) {
					System.out.printf("%-15s", salida.getObject(i));
				}
				System.out.println();
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage().toString());
		}
	}
	
	//Metodo para eliminar un asistente dependiendo de su DNI
	public static void EliminarDatos() {
		Connection conex = Conexion();

		try {
			System.out.print("Introduce el DNI del usuario que quieras eliminar: ");
			String dni_introducido = sc.next();

			String contenido = "delete dni from Asistentes where dni = '" + dni_introducido + "'";
			Statement consulta = conex.createStatement();
			ResultSet salida = consulta.executeQuery(contenido);

		} catch (SQLException e) {
			System.out.println(e.getMessage().toString());
		}
	}
	
	//Main para mostrar el menú de todos los métodos disponibles
	public static void main(String[] args) {
		Connection conex = null;
		conex = Conexion();

		Menu();


	}


}