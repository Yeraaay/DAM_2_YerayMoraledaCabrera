package ejemplo;

public class Principal {

	public static void main(String[] args) {
		 System.out.println("Hilo principal iniciando:");
	        //Primero, construye un objeto MiHilo.
	        MiHilo mihilo=new MiHilo("Soraya");
	        //Luego, construye un hilo de ese objeto.
	        Thread nuevoh=new Thread(mihilo);
	        //Finalmente, comienza la ejecución del hilo.
	        nuevoh.start();
	        try{
	            Thread.sleep(400);
	        }catch (InterruptedException exc){
	            System.out.println("Hilo principal interrumpido.");
	        }
	        System.out.println("Hilo principal finalizado.");
	    }
	}