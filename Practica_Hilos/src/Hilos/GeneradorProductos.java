package Hilos;

import java.util.Random;
import java.util.concurrent.Semaphore;

class Producto {
    int tipo;

    public Producto(int tipo) {
        this.tipo = tipo;
    }
}

class Operario extends Thread {
    CadenaMontaje cadenaMontaje;
    int tipoOperario;
    int productosEmpaquetados;
    int[] productosPorTipo; // Contador para cada tipo de producto
    boolean esperando;

    public Operario(CadenaMontaje cadenaMontaje, int tipoOperario) {
        this.cadenaMontaje = cadenaMontaje;
        this.tipoOperario = tipoOperario;
        this.productosEmpaquetados = 0;
        this.productosPorTipo = new int[3]; // Tres tipos de productos
        this.esperando = false;
    }

    @Override
    public void run() {
        while (productosEmpaquetados < Main.MAX_PRODUCTOS_EMPAQUETADOS) {
            try {
                if (!esperando) {
                    System.out.println("Operario tipo " + tipoOperario + ": Esperando por un producto de mi tipo...");
                    esperando = true;
                }

                // Esperar a que se coloque un producto de su tipo en la cadena
                Producto producto = cadenaMontaje.retirarProducto(tipoOperario);

                // Si se retiró un producto, empaquetarlo, actualizar el contador por tipo y cambiar estado de espera
                if (producto != null) {
                    productosEmpaquetados++;
                    productosPorTipo[producto.tipo - 1]++; // Restamos 1 porque los tipos de producto empiezan en 1
                    System.out.println("Operario tipo " + tipoOperario + ": Producto tipo " + producto.tipo +
                            " empaquetado. Total: " + productosEmpaquetados);
                    esperando = false;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Operario tipo " + tipoOperario + " ha empaquetado " + Main.MAX_PRODUCTOS_EMPAQUETADOS + " productos. Deteniendo operario.");
    }
}

class GeneradorProductos extends Thread {
    CadenaMontaje cadenaMontaje;
    Random rand;

    public GeneradorProductos(CadenaMontaje cadenaMontaje) {
        this.cadenaMontaje = cadenaMontaje;
        this.rand = new Random();
    }

    @Override
    public void run() {
        int productosEmpaquetados = 0;
        while (productosEmpaquetados < Main.MAX_PRODUCTOS_EMPAQUETADOS) {
            try {
                // Simula tiempo de fabricación de un producto
                Thread.sleep(rand.nextInt(2000) + 500);

                // Verificar si se ha alcanzado el límite máximo de productos
                if (productosEmpaquetados >= Main.MAX_PRODUCTOS_EMPAQUETADOS) {
                    break; // Salir del bucle si se alcanza el límite
                }

                // Crear un producto aleatorio y colocarlo en la cadena
                Producto producto = new Producto(rand.nextInt(3) + 1);
                cadenaMontaje.colocarProducto(producto);
                System.out.println("Producto tipo " + producto.tipo + " creado y colocado en la cadena.");
                productosEmpaquetados++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Se han empaquetado " + productosEmpaquetados + " productos. Deteniendo generación de productos.");
    }
}

class CadenaMontaje {
    int capacidad;
    Semaphore mutex;
    Semaphore espaciosDisponibles;
    Producto[] productos;

    public CadenaMontaje(int capacidad) {
        this.capacidad = capacidad;
        mutex = new Semaphore(1);
        espaciosDisponibles = new Semaphore(capacidad);
        productos = new Producto[capacidad];
    }

    public void colocarProducto(Producto producto) throws InterruptedException {
        espaciosDisponibles.acquire();
        mutex.acquire();
        try {
            for (int i = 0; i < capacidad; i++) {
                if (productos[i] == null) {
                    if (producto.tipo >= 1 && producto.tipo <= 3) {
                        productos[i] = producto;
                        System.out.println("Producto tipo " + producto.tipo + " colocado en la cadena.");
                        return; // Salir del método después de colocar el producto
                    }
                }
            }
        } finally {
            mutex.release(); // Liberar el mutex después de colocar el producto
        }
    }

    public Producto retirarProducto(int tipoOperario) throws InterruptedException {
        mutex.acquire();
        try {
            for (int i = 0; i < capacidad; i++) {
                if (productos[i] != null && productos[i].tipo == tipoOperario) {
                    Producto producto = productos[i];
                    productos[i] = null;
                    System.out.println("Producto tipo " + producto.tipo + " retirado de la cadena.");
                    espaciosDisponibles.release();
                    return producto;
                }
            }
        } finally {
            mutex.release();
        }
        return null;
    }
}

public class Main {
    // Número máximo de productos a empaquetar antes de detener el programa
    static final int MAX_PRODUCTOS_EMPAQUETADOS = 5;

    public static void main(String[] args) {
        int capacidadCadena = 5; // Longitud máxima de la cadena
        CadenaMontaje cadenaMontaje = new CadenaMontaje(capacidadCadena);

        // Crear operarios de diferentes tipos
        Operario operarioTipo1 = new Operario(cadenaMontaje, 1);
        Operario operarioTipo2 = new Operario(cadenaMontaje, 2);
        Operario operarioTipo3 = new Operario(cadenaMontaje, 3);

        // Iniciar los hilos de los operarios
        operarioTipo1.start();
        operarioTipo2.start();
        operarioTipo3.start();

        // Iniciar el hilo que genera productos automáticamente
        GeneradorProductos generador = new GeneradorProductos(cadenaMontaje);
        generador.start();

        // Esperar a que todos los operarios terminen
        try {
            operarioTipo1.join();
            operarioTipo2.join();
            operarioTipo3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}