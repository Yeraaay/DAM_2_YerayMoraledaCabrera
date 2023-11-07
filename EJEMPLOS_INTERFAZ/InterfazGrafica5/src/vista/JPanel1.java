package vista;

import javax.swing.JPanel;

	import java.awt.Color;
	import java.awt.Font;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
	import javax.swing.JLabel;
	import javax.swing.JPanel;

	public class JPanel1 extends JPanel{

	    JLabel label = new JLabel("panel # 1");

	    Font fuente = new Font("Comic Sans MS", Font.ITALIC, 40);
	    
	    GridBagLayout layout = new GridBagLayout();
	    GridBagConstraints config = new GridBagConstraints();
	    
	    public JPanel1(){
	        this.setLayout(layout);
	        this.setBackground(Color.ORANGE);
	        
	        label.setFont(fuente);
	        
	        config.gridx=0;
	        config.gridy=0;
	        config.gridwidth=1;
	        config.gridheight=1;
	        config.anchor=GridBagConstraints.CENTER;
	        config.fill= GridBagConstraints.BOTH;
	        this.add(label, config);
	        
	    }
	    
	}

