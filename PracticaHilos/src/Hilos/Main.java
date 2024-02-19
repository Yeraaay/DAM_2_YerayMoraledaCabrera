package Hilos;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

//Clase que representa la cinta de producción
class Cinta {
    private int capacidad;  //Capacidad máxima de la cinta
    private AtomicInteger productosEnCinta;  //Contador de productos en la cinta
    private Semaphore mutex;  //Semáforo para controlar el acceso a la cinta

    //Constructor de la clase Cinta
    public Cinta(int capacidad) {
        this.capacidad = capacidad;
        this.productosEnCinta = new AtomicInteger(0);
        this.mutex = new Semaphore(1);  //Se inicializa el semáforo con 1 permitiendo garantizar el acceso del proceso
    }

    //Método para colocar un producto en la cinta
    public void colocarProducto(int tipoProducto) throws InterruptedException {
        mutex.acquire();  // Se adquiere el semáforo para acceder a la cinta
        try {
            while (productosEnCinta.get() == capacidad) {
                System.out.println("No hay espacio en la cinta. Esperando...");
                mutex.release();  //Se libera el semáforo antes de esperar
                Thread.sleep(1000);  //Espera simulada antes de intentar nuevamente
                mutex.acquire();  //Se adquiere el semáforo nuevamente
            }

            productosEnCinta.incrementAndGet();  //Se incrementa el contador de productos en la cinta
            System.out.println("Producto de tipo " + tipoProducto + " colocado en la cinta");
        } finally {
            mutex.release();  //Se libera el semáforo al salir del bloque
        }
    }

    //Método para que un operario recoja un producto de la cinta
    public void recogerProducto(int tipoProducto, int operarioId) throws InterruptedException {
        mutex.acquire();  //Se adquiere el semáforo para acceder a la cinta
        try {
            if (productosEnCinta.get() > 0) {
                productosEnCinta.decrementAndGet();  //Se decrementa el contador de productos en la cinta
                System.out.println("El Operario " + operarioId + " ha recogido el producto de tipo " + tipoProducto);
            }
        } finally {
            mutex.release(); //Se vuelve a liberar el semáforo
        }
    }

    //Método para obtener la cantidad de productos en la cinta
    public int getProductosEnCinta() {
        return productosEnCinta.get();
    }
}

//Clase que representa a un operario
class Operario implements Runnable {
    private int tipoProducto;  //Tipo de producto (1, 2 o 3)
    private int operarioId;  //Identificación del operario
    private Cinta cinta;  //Referencia a la cinta
    static AtomicInteger totalProductos = new AtomicInteger(0);  //Variable compartida para contar el total de productos

    //Constructor de la clase Operario
    public Operario(int tipoProducto, int operarioId, Cinta cinta) {
        this.tipoProducto = tipoProducto;
        this.operarioId = operarioId;
        this.cinta = cinta;
    }

    //Método que se ejecuta cuando el hilo del operario inicia
    @Override
    public void run() {
        while (totalProductos.get() < 5) {
            try {
                cinta.colocarProducto(tipoProducto);  //Se coloca un producto en la cinta
                Thread.sleep(1000);
                cinta.recogerProducto(tipoProducto, operarioId);  // Recoger un producto de la cinta
                Thread.sleep(1000);
                totalProductos.incrementAndGet();  //Se incrementa el contador total de productos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

//Clase principal
public class Main {
    public static void main(String[] args) {
        int capacidadCinta = 5;
        Cinta cinta = new Cinta(capacidadCinta);  //Se crea una instancia de la cinta

        Thread[] operarios = new Thread[3];  //Se crea un arreglo de hilos para los operarios

        for (int i = 0; i < operarios.length; i++) {
            operarios[i] = new Thread(new Operario(i + 1, i + 1, cinta));  //Se crean los hilos para los operarios
            operarios[i].start();  //Se inicia el hilo de cada operario
        }

        //Se espera a que todos los operarios terminen
        for (Thread operario : operarios) {
            try {
                operario.join();  //Se espera a que el hilo del operario termine
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Se muestra un mensaje final por pantalla con la cantidad de productos recogidos
        System.out.println("\nSe han recogido " + Operario.totalProductos.get() + " productos. Fin del programa.");
    }
}