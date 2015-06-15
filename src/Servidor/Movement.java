/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

/**
 *
 * @author cagj__000
 */
public class Movement {

    public int Value;
    public int PositionX;
    public int PositionY;

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
