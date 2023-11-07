package ejemplos2;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Color;

//Crear un programa que permita ingresar el nombre de usuario y cuando se presione un botón mostrar el valor ingresado en la barra de títulos del JFrame.
public class EjemploText extends JFrame implements ActionListener{

	/* Definimos variables. */
	  private JTextField textfield1;
	  private JLabel label1;
	  private JButton boton1;

	  /* En el constructor de la clase llamamos al método heredado de la clase JFrame llamado setLayout y le pasamos como parámetro un valor null, con esto estamos informándole a la clase JFrame que utilizaremos posicionamiento absoluto para los controles visuales dentro del JFrame*/
	  public EjemploText() {
	  	setBackground(new Color(255, 128, 128));

	    /* Configuración del JFrame */
	    getContentPane().setLayout(null);
	    setBounds(100,50,500,257); //Valores x,y, ancho y alto
	    setTitle("Ejemplo: JTextField");
	   // setLocationRelativeTo(null); para centrar
	    setResizable(false);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);

	    /* Etiqueta de usuario */
	    label1=new JLabel("Usuario:");
	    label1.setBounds(10,10,100,30);
	    getContentPane().add(label1);

	    /* Campo de texto */
	    textfield1=new JTextField();
	    textfield1.setBounds(220,20,150,20);
	    getContentPane().add(textfield1);

	    /* Botón de Aceptar */
	    boton1=new JButton("Aceptar");
	    boton1.setBounds(10,80,100,30);
	    getContentPane().add(boton1);

	    /* Inicializo escuchador del botón */
	    boton1.addActionListener(this);

	    /* Muestro el JFrame */
	    setVisible(true);

	  }

	  /* Método que implementa la acción del botón */
	  public void actionPerformed(ActionEvent e) {
	    if (e.getSource()==boton1) {
	      String titulo=textfield1.getText();
	      setTitle(titulo);
	    }
	  }

	  /* En el método main creamos un objeto de la clase Ejemplo6 */
	  public static void main(String[] args) {

	    new EjemploText();

	  }

}
