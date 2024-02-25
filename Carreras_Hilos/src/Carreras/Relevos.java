package Carreras;

class Testigo {
    private int corredorActual = 1;
    
    //Método utilizado por los corredores para pasar el testigo
    synchronized void pasarTestigo(int corredor) {
    	//Se espera hasta que sea el turno del corredor actual
        while (corredorActual != corredor) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        //Se notifica a todos los hilos que el testigo ha sido pasado de corredor
        notifyAll();
    }
    
    //Método para avanzar al siguiente corredor
    synchronized void siguienteCorredor() {
        corredorActual = (corredorActual % 4) + 1;
        //Se vuelve a notificar a los hilos que el próximo corredor puede pillar el testigo
        notifyAll();
    }
}

class Corredor implements Runnable {
    private final Testigo testigo;
    private final int numero;
    
    //Consturctor del corredor
    public Corredor(Testigo testigo, int numero) {
        this.testigo = testigo;
        this.numero = numero;
    }

    @Override
    public void run() {
        try {
            synchronized (testigo) {
            	//El corredor espera a que se le indique que pueda comenzar
                testigo.pasarTestigo(numero);
                //Una vez se le inqdique que puede comenzar, se imprime un mensaje por consola de inicio
                System.out.println("Corredor " + numero + " ha comenzado a correr.");
                
                //Se simula el tiempo que tarda en correr
                Thread.sleep(2000);
                
                //Una vez simulado el tiempo de carrera, se imprime un mensaje por consola de finalización
                System.out.println("Corredor " + numero + " ha terminado de correr.");
                //Se le indica al testigo que ha terminado y que el siguiente corredor puede avanzar
                testigo.siguienteCorredor();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

class Relevos {
	public static void main(String[] args) {
        Testigo testigo = new Testigo();
        Corredor[] corredores = new Corredor[4];
        Thread[] threads = new Thread[4];

        //Se inicializan los corredores y los hilos
        for (int i = 0; i < corredores.length; i++) {
            corredores[i] = new Corredor(testigo, i + 1);
            threads[i] = new Thread(corredores[i]);
        }

        //Damos la señal para empezar la carrera
        synchronized (testigo) {
            System.out.println("¡Comienza la carrera!");
            testigo.notifyAll();
        }

        //Se inicializan los hilos después de dar la señal
        for (Thread thread : threads) {
            thread.start();
        }

        //Se espera a que todos los corredores terminen
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        //Se muestra un mensaje final por consola para dar por concluida la carrera
        System.out.println("La carrera ha terminado.");
    }
}
