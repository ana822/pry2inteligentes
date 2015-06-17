/**
 * En esta clase se le pone la imagen al panel superior del tablero donde van las letras
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
public class ImagenArriba extends JPanel{
   public ImagenArriba(){    
        this.setSize(395,43);
    }
        
    @Override
    public void paint(Graphics g){
        Dimension tamanio = getSize();
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/imagenes/arriba.png"));        
        g.drawImage(imagenFondo.getImage(),0,0,tamanio.width, tamanio.height, null);        
        setOpaque(false);
        super.paintComponent(g);
    }    
}
