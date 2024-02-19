package Hilos;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

class TipoProducto {
    int tipo;
    Semaphore semaphore = new Semaphore(0);
    int totalRecogidos = 0;

    public TipoProducto(int tipo) {
        this.tipo = tipo;
    }
}

class Operario extends Thread {
    int id;
    TipoProducto tipoProducto;
    Semaphore cadenaSemaphore;
    int capacidadCinta;

    public Operario(int id, TipoProducto tipoProducto, Semaphore cadenaSemaphore, int capacidadCinta) {
        this.id = id;
        this.tipoProducto = tipoProducto;
        this.cadenaSemaphore = cadenaSemaphore;
        this.capacidadCinta = capacidadCinta;
    }

    public void colocarProducto() throws InterruptedException {
        while (true) {
            TipoProducto producto = new TipoProducto(ThreadLocalRandom.current().nextInt(1, 4));
            System.out.println("Producto de tipo " + producto.tipo + " colocado en la cinta en la posición " + (capacidadCinta - cadenaSemaphore.availablePermits() + 1));
            cadenaSemaphore.acquire();
            sleep(1000);
        }
    }

    public void recogerProducto() throws InterruptedException {
        while (true) {
            cadenaSemaphore.release();
            System.out.println("El Operario " + id + " ha recogido el producto de tipo " + tipoProducto.tipo + " de la posición " + (capacidadCinta - cadenaSemaphore.availablePermits()));
            if (tipoProducto.tipo == tipoProducto.tipo) {
                tipoProducto.totalRecogidos++;
            }
            sleep(1000);
        }
    }

    @Override
    public void run() {
        try {
            if (ThreadLocalRandom.current().nextBoolean()) {
                colocarProducto();
            } else {
                recogerProducto();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}