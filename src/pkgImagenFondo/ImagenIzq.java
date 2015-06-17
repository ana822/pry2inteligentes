/**
 * En esta clase se le pone la imagen al panel izquierdo del tablero donde van los números
 */
package pkgImagenFondo;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Ana Paola Martinez y Carlos Garcia
 */
public class ImagenIzq extends JPanel{
   public ImagenIzq(){    
        this.setSize(43,352);
    }
        
    @Override
    public void paint(Graphics g){
        Dimension tamanio = getSize();
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/imagenes/izquierda.png"));        
        g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);        
        setOpaque(false);
        super.paintComponent(g);
    }    
}
