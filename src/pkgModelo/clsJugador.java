/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgModelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author Ana
 */
public class clsJugador{
    
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
//    
    
   //-------------------------------------------------------------------
    Socket s;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    clsTablero juego;

    public clsJugador() throws IOException {
        this.Configurar();
    }

    private void Configurar() throws IOException {
        s = new Socket("localhost", 1399);
        oos = new ObjectOutputStream(s.getOutputStream());
        ois = new ObjectInputStream(s.getInputStream());
        juego = new clsTablero();
    }

    public String[][] EnviarJuagada(String[][] tablero) throws IOException, ClassNotFoundException {
        //juego.setMultiplicador(2);
        juego.setTablero(tablero);
        System.out.println("cliente!!!");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(" "+juego.getTablero()[i][j]);
            }
            System.out.println("");
        }
        oos.writeObject(juego);//escribe via socket la matriz
        juego = (clsTablero) ois.readObject();//lee via socket la matriz modificada en el server
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                System.out.print(" " + juego.getMATRIZ()[i][j]);
//            }
//            System.out.println("");
//        }
        return juego.getTablero();
    }

  
}
