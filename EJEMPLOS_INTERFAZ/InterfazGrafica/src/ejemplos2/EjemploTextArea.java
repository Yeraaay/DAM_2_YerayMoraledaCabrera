package ejemplos2;

import javax.swing.*;

//Crear un campo de entrada de email de tipo JTextField y debajo el cuerpo del mensaje en formato jTextArea
public class EjemploTextArea extends JFrame {

	/* Definimos variables. */
	  private JTextField textfield1;
	  private JTextArea textarea1;

	  public EjemploTextArea() {

	    /* Configuración del JFrame */
	    setLayout(null);
	    setBounds(0,0,540,400);
	    setTitle("Ejemplo: JTextArea");
	    setResizable(false);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	    
	    /* Campo de email */
	    textfield1=new JTextField();
	    textfield1.setBounds(10,10,200,30);
	    add(textfield1);

	    /* Cuerpo de mensaje */
	    textarea1=new JTextArea();
	    textarea1.setBounds(10,50,400,300);
	    add(textarea1);
	    
	    /* Muestro el JFrame */
	    setVisible(true);

	  }

	  public static void main(String[] args) {

	    new EjemploTextArea();

	  }

	}