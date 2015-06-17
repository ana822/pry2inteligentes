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
 *La clase jugador contiene el lado cliente de la conexión y los atributos de un jugador para llevar las estadisticas del juego
 * @author Ana Paola Martinez y Carlos Garcia
 */
public class clsJugador {

    private int id;
    private String nombre;
    private int juegosGanados=0;
    private int juegosPerdidos=0;
    private int juegosEmpatados=0;
    int cantFichasB = 0;
    int cantFichasN = 0;
/**
 * Me aumenta en uno si el jugador gana
 */
    public void gano() {
        juegosGanados++;
    }
/**
 * Me aumenta en uno si el jugador pierde
 */
    public void perdio() {
        juegosPerdidos++;
        System.out.println("cant perdida "+ juegosPerdidos);
    }
/**
 * Me aumenta en uno si el jugador empata
 */
    public void empato() {
        juegosEmpatados++;
         System.out.println("cant empatada "+ juegosEmpatados);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getJuegosGanados() {
        return juegosGanados;
    }

    public int getJuegosEmpatados() {
        return juegosEmpatados;
    }

    public int getJuegosPerdidos() {
        return juegosPerdidos;
    }
/**
 * Me de vuelve en un vector de dos posiciones la v¿cantidad de fichas negras y la cantidad de fichas blancas que tiene el tablero
 * @param tablero
 * @return 
 */
    public int[] CantidadFichas(String[][] tablero) {
        int[] cantFichas = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (tablero[i][j].equals("negra")) {
                    cantFichasN = cantFichasN + 1;
                } else if (tablero[i][j].equals("blanca")) {
                    cantFichasB = cantFichasB + 1;
                }
            }
            cantFichas[0] = cantFichasB;
            cantFichas[1] = cantFichasN;
        }
        System.out.println("Fichas Blancas" + cantFichas[0]);
        System.out.println("Fichas Negras" + cantFichas[1]);
        return cantFichas;
    }

    public clsJugador(String nombre) {
        this.nombre = nombre;
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
/**
 * Envía la jugada y recibe la jugada del servidor
 * @param tablero
 * @return
 * @throws IOException
 * @throws ClassNotFoundException 
 */
    public clsTablero EnviarJuagada(String[][] tablero) throws IOException, ClassNotFoundException {
        juego.setTablero(tablero);
        oos.writeObject(juego);//escribe via socket la matriz
        juego = (clsTablero) ois.readObject();//lee via socket la matriz modificada en el server
        return juego;
    }
}
