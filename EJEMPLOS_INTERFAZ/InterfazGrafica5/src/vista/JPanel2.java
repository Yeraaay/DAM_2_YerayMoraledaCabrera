package vista;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanel2 extends JPanel{
    
    JLabel label = new JLabel("panel # 2");  
    Font fuente = new Font("Comic Sans MS", Font.ITALIC, 40);
    
    GridBagLayout layout = new GridBagLayout();
    GridBagConstraints config = new GridBagConstraints();

    public JPanel2(){
    
        this.setLayout(layout);
        this.setBackground(Color.YELLOW);
        
        label.setFont(fuente);
        config.gridx=0;
        config.gridy=0;
        config.gridwidth=1;
        config.gridheight=1;
        config.anchor=GridBagConstraints.CENTER;
        config.fill= GridBagConstraints.BOTH;
        this.add(label, config);
        
        
    }
    
    @Override
    public void paint(Graphics g){
        Dimension dimension = this.getSize();
        ImageIcon icon = new ImageIcon(getClass().getResource("/imagenes/enseñar.jpg"));
        g.drawImage(icon.getImage(), 0, 0, dimension.width, dimension.height, null);
        setOpaque(false);
        super.paintChildren(g);
    }
    
}



