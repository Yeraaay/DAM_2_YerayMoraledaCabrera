package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JPanel3 extends JPanel{
    
    JLabel label = new JLabel("PRIMERO DAW");  
    Font fuente = new Font("Comic Sans MS", Font.ITALIC, 40);
    
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints config = new GridBagConstraints();
    
    public JPanel3(){
    
        this.setLayout(layout);
        this.setBackground(Color.GREEN);
        
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