package ejemplos2;

import javax.swing.*;

public class EjemploJScrollPane extends JFrame{

  /* Definimos variables. */
  private JTextField textfield1;
  private JTextArea textarea1;
  private JScrollPane scrollpane1;

  public EjemploJScrollPane() {

    /* Configuración del JFrame */
    setLayout(null);
    setBounds(0,0,540,400);
    setTitle("Ejemplo: JTextArea con JScrollPane");
    setResizable(false);
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    /* Campo de email */
    textfield1=new JTextField();
    textfield1.setBounds(10,10,200,30);
    add(textfield1);

    /* Cuerpo de mensaje en scrollPane*/
    textarea1=new JTextArea();
    textarea1.setLineWrap(true); //Para que salte de línea al llegar al final del ancho del jTextArea
    scrollpane1=new JScrollPane(textarea1);
    scrollpane1.setBounds(10,50,400,300);
    add(scrollpane1);
    
    /* Muestro el JFrame */
    setVisible(true);

  }

  public static void main(String[] args) {

    new EjemploJScrollPane();

  }

}