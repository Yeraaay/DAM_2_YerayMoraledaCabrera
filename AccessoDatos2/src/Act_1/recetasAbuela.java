package Act_1;
import java.io.*;
import java.util.*;

public class recetasAbuela {

	static Scanner sc = new Scanner(System.in);
	static List<String> IngredientesReceta = new ArrayList<>();

	public static void RecetasAbuela() {

		System.out.println("Introduce el nombre de la receta: ");
		String nombre_receta = sc.nextLine();
		System.out.println("Introduce la cantidad de ingredientes que tiene dicha receta separados por coma: ");
		String total_ingredientes = sc.nextLine();

		for(String ingrediente: total_ingredientes.split(",")) {
			IngredientesReceta.add(ingrediente.trim());
		}

		List<String> recetas = new ArrayList<>();
		//Añadimos varias recetas
		recetas.add("Bizcocho de Yogurt: (3) 125gr. de yogur natural, 3 huevos, 10gr. levadura");
		recetas.add("Chuletas de Cerdo: (2) 5 chuletas de cerdo, 1 paquete de mezcla para relleno de pollo");


		//Comporbamos si la receta se puede hacer
		List<String> recetasPosibles = new ArrayList<>();
		for(String receta: recetas) {
			if(puedeHacerse(receta, IngredientesReceta)) {
				recetasPosibles.add(receta);
			}
		}

	}
	
	public static void guardarArchivoRAF(List<String> recetas, String rutaArchivo) {
		
		//Inicializamos el "RandomAccessFile" y "FileWriter" a null para poder controlar los errores en un "try-catch"
		RandomAccessFile raf = null;
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(rutaArchivo);
			
			for(String receta: recetas) fw.write(receta + "\n");
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage().toString());
		} catch (IOException e) {
			System.out.println(e.getMessage().toString());
		} finally {
			try {
				if(fw != null) fw.close();
				if(raf != null) raf.close();
			} catch (Exception e2) {
				System.out.println(e2.getMessage().toString());
			}
		}
	}

	public static boolean puedeHacerse(String receta, List<String> ingredientesDisponibles) {
		String[] partes = receta.split(":");
		if(partes.length != 2) return false;

		String[] listaIngredientes = partes[1].trim().split(",");
		for(String ingrediente: listaIngredientes) {
			if(!ingredientesDisponibles.contains(ingrediente.trim())) return false;
		}
		return true;
	}

	public static void main(String[] args) {
		RecetasAbuela();
	}


}