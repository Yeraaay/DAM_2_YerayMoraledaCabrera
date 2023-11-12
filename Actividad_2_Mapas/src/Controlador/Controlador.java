package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import Vista.Vista;
	
public class Controlador implements ActionListener {

	static Vista vista;
	static Map<Integer, Producto> mapaInventario = new HashMap<Integer, Producto>();
	static List<String> registroVentas = new ArrayList<>();
	static int ultimaClave = 0;

	//El constructor recibe por parámetro un objeto de tipo "InterfazGrafica_Mapa"
	public Controlador(Vista vista_interfaz) {
		Vista.vista=vista_interfaz;
	}

	//Este método se encarga de la escucha de los eventos de cada botón de la interfaz
	public void escucharEventos() {        
		for(int i=0; i<Vista.botones.length; i++) {
			Vista.botones[i].addActionListener(this);
		}
	}

	//Añadimos datos por defecto a nuestro mapa, en este caso, metemos datos de nuestro objeto Producto
	public static void datosPorDefecto(Map<Integer, Producto> mapaInventario) {
		//Guardamos un número random y la introducimos como clave en nuestro mapa

		Producto p1 = new Producto("Patatas", 5.0, 40);
		Producto p2 = new Producto("Naranjas", 21.3, 60);
		Producto p3 = new Producto("Tomates", 5.0, 25);
		//Añadimos valores por defecto
		mapaInventario.put(++ultimaClave, p1);
		mapaInventario.put(++ultimaClave, p2);
		mapaInventario.put(++ultimaClave, p3);
	}

	public static void aniadirProducto() {
		try {

			//Mostramos un mensaje al usuario para que nos introduzca un nuveo objeto, con su nombre, precio y stock
			String nombreProducto = JOptionPane.showInputDialog(null, "Introduce el nombre del producto: ");
			String precioString = JOptionPane.showInputDialog(null, "Introduce un precio: ");
			String stockString = JOptionPane.showInputDialog(null, "Introduce un stock: ");

			/**
			 * Pasamos el precio y el sotck de tipo "String" a "double" e "int",
			 * ambos para poder introducir los valores correctos en nuestro mapa.
			 */
			double precioProducto = Double.parseDouble(precioString);
			int stockProducto = Integer.parseInt(stockString);

			/**
			 * Comprobamos que los datos a introducir no existen previamente en nuestro mapa,
			 * si no existe, lo añade y verifica su inserción. Si se ha introducido correctamente
			 * nos mostrará un mensaje de éxito.
			 */
			if (!mapaInventario.containsKey(++ultimaClave)) {
				mapaInventario.put(ultimaClave, new Producto(nombreProducto, precioProducto, stockProducto));
				if (mapaInventario.containsKey(ultimaClave)) JOptionPane.showMessageDialog(null, "Datos insertados correctamente");
			} else JOptionPane.showMessageDialog(null, "Error al insertar los datos, inténtalo de nuevo!");

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error, introduce un valor válido");
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo!");
		}
	}

	public static void eliminarProducto() {
		try {

			//Pedimos al usuario el nombre que desee eliminar
			String claveString = JOptionPane.showInputDialog(null, "Introduce la clave del producto que quieras eliminar");

			//Pasamos la variable a tipo "int" para manejar mejor los valores dentro del mapa
			int claveAEliminar = Integer.parseInt(claveString);

			//Comprobamos que el nombre exista en nuestro mapa
			if (mapaInventario.containsKey(claveAEliminar)) {
				mapaInventario.remove(claveAEliminar);
				JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
			} else JOptionPane.showMessageDialog(null, "No se ha encontrado un producto con esa clave");

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error, introduce un valor válido");
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo!");
			e.printStackTrace();
		}
	}

	public static void venderProducto() {
		try {

			/**
			 * Le pedimos al usuario que introduzca la clave del producto que quiera vender
			 * y la cantidad de productos quiera vender.
			 */
			String claveString = JOptionPane.showInputDialog(null, "Introduce la clave del producto que quieras vender");
			String cantidadString = JOptionPane.showInputDialog(null, "Introduce la cantidad de productos que quieras vender");

			/**
			 * Pasamos los valores de tipo "String" a tipo "int" para manejar mejor el mapa con esos valores
			 */
			int clave = Integer.parseInt(claveString);
			int cantidad = Integer.parseInt(cantidadString);

			if (mapaInventario.containsKey(clave)) {
				Producto producto = mapaInventario.get(clave);
				//Verificamos si hay suficiente stock para poder vender
				if (producto.getStock() >= cantidad) {
					mapaInventario.get(clave).setStock(mapaInventario.get(clave).getStock() - cantidad);
					JOptionPane.showMessageDialog(null, "Se han vendido " + cantidad + " unidades correctamente");
					
					registroVentas.add("Venta: " + cantidad + " unidades de " + producto.getNombre() + ". Precio total: " + (cantidad * producto.getPrecio()) + "$.");
				} else {
					JOptionPane.showMessageDialog(null, "No hay suficiente stock del producto elegido");
				}
			} else JOptionPane.showMessageDialog(null, "No existe ningún poroducto con esa clave, veulve a intentarlo");

		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Error en el formato de entrada");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error, vuelve a intentarlo!");
		}
	}

	public static void mostrarProductos() {
		//Introducimos los datos por defecto de nuestro mapa
		if(mapaInventario.isEmpty()) datosPorDefecto(mapaInventario);

		@SuppressWarnings("serial")
		DefaultTableModel modelo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelo.addColumn("Clave");
		modelo.addColumn("Productos");

		//Recorremos el mapa y añadimos las filas al modelo de la tabla
		for (Map.Entry<Integer, Producto> entry: mapaInventario.entrySet()) {
			modelo.addRow(new Object[]{entry.getKey(), entry.getValue()});
		}

		//Se actualiza el modelo de la tabla
		Vista.tabla.setModel(modelo);
	}
	
	public static void verVentas() {
	    //Se verifica si hay ventas registradas
	    if (registroVentas.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Todavía no hay ventas existentes");
	    } else {
	        //Se muestran las ventas realizadas
	        StringBuilder mensajeVentas = new StringBuilder("Ventas realizadas:\n");

	        for (String venta : registroVentas) {
	            mensajeVentas.append(venta).append("\n");
	        }

	        JOptionPane.showMessageDialog(null, mensajeVentas.toString());
	    }
	}


	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(Vista.botones[0])) {
			aniadirProducto();
			mostrarProductos();
		}
		if (e.getSource().equals(Vista.botones[1])) {
			mostrarProductos();
		}
		if (e.getSource().equals(Vista.botones[2])) {
			venderProducto();
			mostrarProductos();
		}
		if (e.getSource().equals(Vista.botones[3])) {
			eliminarProducto();
			mostrarProductos();
		}
		if (e.getSource().equals(Vista.botones[4])) {
			verVentas();
		}
	}

}