package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Vista.Vista;

public class Controlador implements ActionListener {

	static Vista vista;
	static Map<Integer, Producto> mapaInventario = new HashMap<Integer, Producto>();
	static int randomKey;

	//El constructor recibe por parámetro un objeto de tipo "InterfazGrafica_Mapa"
	public Controlador(Vista vista_interfaz) {
		Vista.vista=vista_interfaz;
	}

	public static int generarKeyRandom() {
		randomKey = (int) (Math.random()*100+1);

		//Devolvemos el número radnom generado
		return randomKey;
	}

	//Este método se encarga de la escucha de los eventos de cada botón de la interfaz
	public void escucharEventos() {        
		for(int i=0; i<Vista.botones.length; i++) {
			Vista.botones[i].addActionListener(this);
		}
	}

	//Añadimos datos por defecto a nuestro mapa, en este caso, metemos datos de nuestro objeto Producto
	public static void datosPorDefecto(Map<Integer, Producto> mapaNotas) {
		//Guardamos un número random y la introducimos como clave en nuestro mapa
		do {
			
			
			//Añadimos valores por defecto
			mapaNotas.put("José", 7.9);
			mapaNotas.put("Elena", 3.6);
			mapaNotas.put("Cristina", 9.2);
			mapaNotas.put("Mario", 8.0);
			mapaNotas.put("Lucas", 5.1);
			mapaNotas.put("Pepe", 6.8);
			mapaNotas.put("Lola", 10.0);
		}while(true);
	}

	public static void aniadirDatos() {
		try {

			//Mostramos un mensaje al usuario para que nos introduzca un nombre con su respectiva nota
			String nombre = JOptionPane.showInputDialog(null, "Introduce el nombre del alumno/a");
			String nota = JOptionPane.showInputDialog(null, "Introduce una nota: ");

			//Pasamos la nota de tipo "String" a double para poder introducirla en nuestro mapa
			double notaDouble  = Double.parseDouble(nota);

			/**
			 * Comprobamos que los datos a introducir no existen previamente en nuestro mapa,
			 * si no existe, lo añade y verifica su inserción. Si se ha introducido correctamente
			 * nos mostrará un mensaje de éxito
			 */
			if (!mapaNotas.containsKey(nombre)) {
				mapaNotas.put(nombre, notaDouble);
				if (mapaNotas.containsKey(nombre)) JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
			} else JOptionPane.showMessageDialog(null, "Error al insertar los datos, inténtalo de nuevo!");

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error, introduce una nota válida");
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo!");
		}
	}

	public static void eliminarDatos() {
		try {

			//Pedimos al usuario el nombre que desee eliminar
			String nombreAEliminar = JOptionPane.showInputDialog(null, "Introduce el alumno que quieras eliminar");

			//Comprobamos que el nombre exista en nuestro mapa
			if (mapaNotas.containsKey(nombreAEliminar)) {
				mapaNotas.remove(nombreAEliminar);
				JOptionPane.showMessageDialog(null, "Dato eliminado correctamente");
			} else JOptionPane.showMessageDialog(null, "No se ha encontrado un alumno con ese nombre");

		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo!");
			e.printStackTrace();
		}
	}

	public static void actualizarDatos() {
		try {

			// Le pedimos al usuario qué datos quiere actualizar
			String[] opcionesMapa = mapaNotas.keySet().toArray(new String[0]);
			String nombreAModificar = (String) JOptionPane.showInputDialog(null, "Seleccione una opción:", "Cuadro de Diálogo Desplegable", JOptionPane.QUESTION_MESSAGE, null, opcionesMapa, opcionesMapa[0]);

			// Comprobamos que ese nombre exista en el mapa
			if (mapaNotas.containsKey(nombreAModificar)) {
				// Genero una lista de opciones para mostrarlo más tarde en un "JOptionPane.showOptionDialog"
				String[] opciones = {"Nombre", "Nota"};
				int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción:", "Opciones", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

				// Si la opción elegida ha sido la primera, realizamos la lógica de modificación de un nombre en el mapa
				if (seleccion == 0) {
					String nuevoNombre = JOptionPane.showInputDialog(null, "Introduce el nuevo nombre: ");

					// Comprobamos que no exista ya un nombre igual al nuevo nombre que vayamos a actualizar
					if (!mapaNotas.containsKey(nuevoNombre)) {
						// Obtenemos el valor de ese nombre a modificar
						double notaGuardada = mapaNotas.get(nombreAModificar);
						// Eliminamos el nombre y añadimos el nuevo, incluyendo además el valor guardado anteriormente
						mapaNotas.remove(nombreAModificar);
						mapaNotas.put(nuevoNombre, notaGuardada);
						if (mapaNotas.containsKey(nuevoNombre)) {
							JOptionPane.showMessageDialog(null, "Alumno actualizado correctamente");
						}
					} else {
						JOptionPane.showMessageDialog(null, "Ya existe un alumno con ese nombre, inténtalo de nuevo!");
					}
				}
				// Si la opción elegida es la segunda, indicamos la lógica para la modificación de la nota de un alumno
				if (seleccion == 1) {
					String notaNuevaString = JOptionPane.showInputDialog(null, "Introduce la nueva nota");
					// Convertimos el input a Double
					double notaNueva = Double.parseDouble(notaNuevaString);
					// Modificamos la nota del alumno elegido anteriormente
					mapaNotas.put(nombreAModificar, notaNueva);
					if (mapaNotas.get(nombreAModificar) == notaNueva) JOptionPane.showMessageDialog(null, "Nota actualizada correctamente");
					else JOptionPane.showMessageDialog(null, "Error al actualizar la nota");
				}
			}

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error, introduce una nota válida");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo!");
		}
	}

	public static void mostrarDatos() {

		//Introducimos los datos por defecto de nuestro mapa
		if(mapaNotas.isEmpty()) datosPorDefecto(mapaNotas);

		@SuppressWarnings("serial")
		DefaultTableModel modelo = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.addColumn("Nombre");
		modelo.addColumn("Nota");

		//Recorremos el mapa y añadimos las filas al modelo de la tabla
		for (Map.Entry<String, Double> entry: mapaNotas.entrySet()) {
			String nombre = entry.getKey();
			Double nota = entry.getValue();

			modelo.addRow(new Object[]{nombre, nota});
		}

		//Actualizamos el modelo de la tabla
		Vista.tabla.setModel(modelo);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(Vista.botones[0])) {
			aniadirDatos();
			mostrarDatos();
		}
		if (e.getSource().equals(Vista.botones[1])) {
			eliminarDatos();
			mostrarDatos();
		}
		if (e.getSource().equals(Vista.botones[2])) {
			actualizarDatos();
			mostrarDatos();
		}
		if (e.getSource().equals(Vista.botones[3])) {
			mostrarDatos();
			mostrarDatos();
		}
	}



}