/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgModelo;

/**
 *
 * @author Ana
 */
public class clsJugador {
    
private int id;
private String nombre;
private int juegosGanados;
int cantFichasB;
int cantFichasN;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getJuegosGanados() {
        return juegosGanados;
    }

    public int[] CantidadFichas(String[][] tablero){
        int [] cantFichas=new int[2];
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if(tablero[i][j].equals("negra")){
                    cantFichasN+=1;
                } else  if(tablero[i][j].equals("blanca")){
                    cantFichasB+=1;
                }
            }
            cantFichas[0]=cantFichasN;
            cantFichas[1]=cantFichasB;
        }
        return cantFichas;
    }
  
}
