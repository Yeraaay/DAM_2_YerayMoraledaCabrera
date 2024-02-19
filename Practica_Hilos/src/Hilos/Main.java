package Hilos;

import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int capacidadCinta = 5;
        Semaphore cadenaSemaphore = new Semaphore(capacidadCinta, true);

        TipoProducto tipo1 = new TipoProducto(1);
        TipoProducto tipo2 = new TipoProducto(2);
        TipoProducto tipo3 = new TipoProducto(3);

        Operario operario1 = new Operario(1, tipo1, cadenaSemaphore, capacidadCinta);
        Operario operario2 = new Operario(2, tipo2, cadenaSemaphore, capacidadCinta);
        Operario operario3 = new Operario(3, tipo3, cadenaSemaphore, capacidadCinta);

        operario1.start();
        operario2.start();
        operario3.start();
    }
}