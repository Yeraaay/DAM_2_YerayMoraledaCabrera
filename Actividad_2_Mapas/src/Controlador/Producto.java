package Controlador;

public class Producto {
	
	//Atributos
	protected String nombre;
	protected Double precio;
	protected int stock;
	
	//Métodos (Getter y Setter)
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	//CONSTRUCTORES
	
	//Constructor vacío
	public Producto() {
	}
	
	//Constructor con todos los parámetros
	public Producto(String nombre, Double precio, int stock) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.stock = stock;
	}
	
	//Método toString
	public String toString() {
		return nombre + ", " + precio + "$ y " + stock + " unidades";
	}
	
}