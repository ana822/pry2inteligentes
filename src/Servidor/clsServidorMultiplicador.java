/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import pkgModelo.clsTablero;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author michael
 */
public class clsServidorMultiplicador implements Runnable {

    ServerSocket ss;
    Socket s;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    clsTablero juego;

    //quien me va a recibir el socket,
    /**
     *
     */
    private void AtenderConexion() {
        //genero el servidor
        try {
            ss = new ServerSocket(1399);
            s = ss.accept(); //se genera el canal de comunicacion
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(clsServidorMultiplicador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void run() {
        AtenderConexion();
        while (true /*codicion del final de la partida*/) {
            try {
                juego = (clsTablero) ois.readObject();
                //String[][] tablero =  MultiplicaMatriz();

                System.out.println("sevidor!!!");
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        System.out.print(" " + juego.getTablero()[i][j]);
                    }
                    System.out.println("");
                }
                System.out.println("");
                juego.setTablero(Generarjugada(juego));
                oos.writeObject(juego);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(clsServidorMultiplicador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * metodo que recibe una matriz y la multiplica
     *
     * @return la nueva matriz multiplicada
     */
    private String[][] Generarjugada(clsTablero Juego) {
  

        juego.ponerFicha(new Point(0,0), "negra");
        System.out.println(" estooooo" + juego.getTablero()[0][0]);
        return juego.getTablero();
    }

}
