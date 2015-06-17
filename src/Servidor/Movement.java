/*
 * La clase movement el cual es el punto que enviará el servidor a partir de la mejor jugada
 */
package Servidor;

/**
 *
 * @author Carlos Garcia y Ana Paola Martinez
 */
public class Movement {

    public int Value;
    private int PositionX;
    private int PositionY;

    public Movement() {
        PositionX = -1;
        PositionY = -1;
    } // en caso no haya movimiento 

    public int getValue() {
        return Value;
    }

    public int getPositionX() {
        return PositionX;
    }

    public int getPositionY() {
        return PositionY;
    }

    public void setPositionX(int PositionX) {
        this.PositionX = PositionX;
    }

    public void setPositionY(int PositionY) {
        this.PositionY = PositionY;
    }

    public void setValue(int Value) {
        this.Value = Value;
    }

}
