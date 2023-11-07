package ventanaimagen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

public class VentanaPrincipal extends JFrame{
    
    panelImagen panel = new panelImagen();
       
    public VentanaPrincipal(){
        
        this.setTitle("Ventana con Imagen");
        this.setSize(new Dimension(700, 700));      
        this.setLocationRelativeTo(null);
        
        this.add(panel, BorderLayout.CENTER);
    }

    public static void main(String [] args){
        VentanaPrincipal frame = new VentanaPrincipal();
        frame.setVisible(true);
    }
    
    
}
 