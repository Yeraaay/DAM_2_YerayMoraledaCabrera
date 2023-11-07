package ejemplo;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

//Clase Hilo, hereada de un JLabel e implementada de un Runnable el cual permite el hilo
public class Pelota extends JLabel implements Runnable {

	//Contructor Pelota
	public Pelota(){
		this.setIcon( new ImageIcon( getClass().getResource( "/imagenes/pelota2.png" ) ) );
		this.setBounds(350,120,70,78);
	}
	
	//Metodo obligatorio por utilizar la interface Runnable
	public void run(){
		//Tres tipos de movimiento:
		//Recto
		//Izquiera -diagonal hacia abajo-
		//Derecha -diagonal hacia arriba-

		//Random de 1 a 3
		int tipoDisparo = (int)(Math.random()*3) + 1;
		switch( tipoDisparo ){
			case 1: disparoRecto(); break;
			case 2: disparoIzquierda(); break;
			case 3: disparoDerecha(); break;
		}
		setVisible(false);
	}
	
	//Disparo Recto
	public void disparoRecto(){
		
		while( this.getX() > 40 ){
			try{
				Thread.sleep( 10 );
			}catch( Exception e ){
				e.printStackTrace();
			}
			setLocation(this.getX()-5,this.getY());
		}
	}

	//Disparo Izquiera -diagonal hacia abajo-
	public void disparoIzquierda(){
		while( this.getX() > 40 ){
			try{
				Thread.sleep(10);
				setLocation(this.getX()-5,this.getY()+2);
			}catch( Exception e ){
				e.printStackTrace();
			}
		}
	}

	//Disparo Derecha -diagonal hacia arriba-
	public void disparoDerecha(){
		while( this.getX() > 40 ){
			try{
				Thread.sleep(10);
				setLocation(this.getX()-5,this.getY()-2);
			}catch( Exception e ){
				e.printStackTrace();
			}
		}
	}
}