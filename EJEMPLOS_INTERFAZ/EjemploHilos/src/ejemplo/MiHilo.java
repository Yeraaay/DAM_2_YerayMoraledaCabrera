package ejemplo;

public class MiHilo implements  Runnable{

	//Crea un hilo implementando Runnable.
	//Los objetos de MiHilo se pueden ejecutar en sus propios hilos
	// porque MiHilo implementa Runnable.

	String nombreHilo;
	
	MiHilo(String nombre){
		this.nombreHilo=nombre;
	}
	//Punto de entrada del hilo
	//Los hilos comienzan a ejecutarse aquí
	public void run(){
		System.out.println("Comenzando "+nombreHilo);
		try {
			for (int contar=0; contar<20; contar++){
				Thread.sleep(100);
				System.out.println("En "+nombreHilo+", alumno número "+contar);
			}
		}catch (InterruptedException exc){
			System.out.println(nombreHilo + "Interrumpido.");
		}
		System.out.println("Terminando "+nombreHilo);
	}
}

