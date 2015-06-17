/**
 * Fichas que podran ir en el tablero, las cuales son blancas o negras y las vacias simulan las casillas que no tienen ficha
 */
package pkgModelo;

import java.io.Serializable;

/**
 *
 * @author Ana Paola Martínez y Carlos Garcia
 */
public class clsFicha implements Serializable{

    private String vacia="vacia";
    private String blanca="blanca";
    private String negra="negra";
    private int posicionX;
    private int posicionY;

    public String getVacia() {
        return vacia;
    }

    public String getBlanca() {
        return blanca;
    }

    public String getNegra() {
        return negra;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public void setPosicionX(int posicionX) {
        this.posicionX = posicionX;
    }

    public void setPosicionY(int posicionY) {
        this.posicionY = posicionY;
    }

    public clsFicha() {
    }

    public clsFicha(int posicionX, int posicionY, String blanca,String negra, String vacia) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.blanca=blanca;
        this.negra=negra;
        this.vacia=vacia;
    }

    
    public void setVacia(String vacia) {
        this.vacia = vacia;
    }

    public void setBlanca(String blanca) {
        this.blanca = blanca;
    }

    public void setNegra(String negra) {
        this.negra = negra;
    }
    
    
}
