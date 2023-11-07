package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MultiPanel extends JFrame {

	// creamos un objeto de la clase GridBagLayout este sera nuestro layout el cual determinara 
    // el orden en que se distribuiran los componentes
	 GridBagLayout layout = new GridBagLayout();
	  
	   // creamos un objeto de la clase GridBagContraints 
	   // en el cual se guarda información de cómo y dónde añadir el componente. 
	  
	    GridBagConstraints config = new GridBagConstraints();
	    
	    // creamos tres objetos de la clase JPanel
	    JPanel1 panel1 = new  JPanel1();
	    JPanel2 panel2 = new  JPanel2();
	    JPanel3 panel3 = new  JPanel3();    
	    
	    // creamos dos objetos de la clase JButtoon
	    // el cual nos permitira administrar la visualizacion de los JPanel
	    JButton btn_next = new JButton("Siguiente");
	    JButton btn_prev = new JButton("Anterior");    
	    
	    public MultiPanel(){
	        
	        this.setSize(600, 600);  // asignamos el ancho y alto a la venta JFrame
	        this.setLocationRelativeTo(null);  // centramos la ventana en pantalla
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	        this.setLayout(layout);   // agregamos el layout al JFrame
	        
	         // agregamos los respectivos contenedores y componentes al JFrame
	        config.gridx=0;
	        config.gridy=0;
	        config.gridwidth=2;
	        config.gridheight=1;
	        config.weightx=1.0;
	        config.weighty=1.0;
	        config.anchor=GridBagConstraints.CENTER;
	        config.fill= GridBagConstraints.BOTH;
	        this.add(panel1, config);
	        this.add(panel2, config);
	        this.add(panel3, config);     
	        
	        config.gridx=0;
	        config.gridy=1;
	        config.gridwidth=1;
	        config.gridheight=1;
	        config.ipadx=100;
	        config.ipady=10;
	        config.weighty=0.0;
	        config.anchor=GridBagConstraints.CENTER;
	        config.fill= GridBagConstraints.CENTER;
	        this.add(btn_prev, config);

	        config.gridx=1;
	        config.gridy=1;
	        config.gridwidth=1;
	        config.gridheight=1;
	        config.ipadx=100;
	        config.ipady=10;
	        config.weighty=0.0;
	        config.anchor=GridBagConstraints.CENTER;
	        config.fill= GridBagConstraints.CENTER;
	        this.add(btn_next, config);  
	        
	        panel1.setVisible(true);
	        panel2.setVisible(false);
	        panel3.setVisible(false);        



	        // creamos los aventos 
	        btn_next.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                    
	                if(panel1.isVisible()){
	                    
	                    panel2.setVisible(true);
	                    panel3.setVisible(false);
	                    panel1.setVisible(false);
	                    validate();
	                    
	                }else if(panel2.isVisible()){

	                    panel3.setVisible(true);
	                    panel2.setVisible(false);
	                    panel1.setVisible(false);
	                    validate();
	                
	                }

	            }

	        });
	        
	        btn_prev.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                    
	                if(panel2.isVisible()){
	                
	                    panel1.setVisible(true);
	                    panel2.setVisible(false);
	                    panel3.setVisible(false);
	                    
	                }else if(panel3.isVisible()){
	                    
	                    panel2.setVisible(true);
	                    panel3.setVisible(false);
	                    panel1.setVisible(false);
	                    
	                }

	            }

	        });        
	        
	    }
	    // metodoo main
	    public static void main(String[] args) {
	        
	        // instaciamos la clase y hacemos visible nuestro JFrame
	        MultiPanel frame = new MultiPanel();
	        frame.setVisible(true);
	        
	    }
	    
	}
