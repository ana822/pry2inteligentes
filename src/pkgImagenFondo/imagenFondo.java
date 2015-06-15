/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgImagenFondo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Ana
 */
public class imagenFondo extends JPanel{
   public imagenFondo(){    
        this.setSize(400,280);
    }
        
    @Override
    public void paint(Graphics g){
        Dimension tamanio = getSize();
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/imagenes/tablero.png"));        
        g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);        
        setOpaque(false);
        super.paintComponent(g);
    }    
}
