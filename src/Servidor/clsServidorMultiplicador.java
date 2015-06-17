
package Servidor;

import pkgModelo.clsTablero;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pkgModelo.clsJugador;

/**
 *
 * @author Ana Paola MArtinez y Carlos Garcia
 */
public class clsServidorMultiplicador implements Runnable {

    ServerSocket ss;
    Socket s;
    ObjectInputStream ois;
    ObjectOutputStream oos;
    clsTablero juego;
    clsJugador jugador;

    
    /**
     *Me genera el canal de conexion
     */
    private void AtenderConexion() {
        
        try {
            ss = new ServerSocket(1399);
            s = ss.accept(); 
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(clsServidorMultiplicador.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/**
 * El servidor realiza su jugada a partir del min-max
 */
    @Override
    public void run() {
        AtenderConexion();
        while (true /*codicion del final de la partida*/) {
            try {
                juego = (clsTablero) ois.readObject();
//                String[][] tablero =  MultiplicaMatriz();
                Movement mov = MiniMaxAlphaBetaDepth(juego, 1, 0, -1, 1);

                System.out.println("sevidor!!!");
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        System.out.print(" " + juego.getTablero()[i][j]);
                    }
                    System.out.println("");
                }
                System.out.println("PUNTOS A PINTAR SERVER");
                juego.setX(mov.getPositionX());
                juego.setY(mov.getPositionY());
                juego.setTablero(Generarjugada(juego, mov));
                oos.writeObject(juego);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(clsServidorMultiplicador.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    /**
     * Este método me genera la jugada que se eligió en el min-max y me retorna el tablero con la jugada
     * @param Juego
     * @param m
     * @return 
     */
    private String[][] Generarjugada(clsTablero Juego, Movement m) {
        juego.ponerFicha(new Point(m.getPositionX(), m.getPositionY()), "blanca");
             return juego.getTablero();
    }

   /**
    * Algoritmo de min-max con poda alpha-beta, el cual me elige la mejor jugada 
    * @param board
    * @param player
    * @param depth
    * @param alpha
    * @param beta
    * @return 
    */
    private Movement MiniMaxAlphaBetaDepth(clsTablero board, int player, int depth, int alpha, int beta) {
        if (board.GameEnded() || depth == 1) {
            Movement mov = new Movement();
            //mov.Value = board.Winner();
            mov.Value = Utilidad(board.getTablero());
            return mov;
        } else {
            LinkedList<Point> successors = board.validarMovimientos("blanca");
            Movement best = null;
            for (int i = 0; i < successors.size(); i++) {
                int poX = (int) successors.get(i).getX();
                int poY = (int) successors.get(i).getY();
                clsTablero successorBoard = (clsTablero) board.Clone();
                successorBoard.ponerFicha(new Point((int) successors.get(i).getX(), (int) successors.get(i).getY()), "blanca");
                Movement tmp = MiniMaxAlphaBetaDepth(successorBoard, player, depth + 1, alpha, beta);

                if (best == null || (player == -1 && tmp.Value < best.Value) || (player == 1 && tmp.Value > best.Value)) {
                    tmp.setPositionX(poX);
                    tmp.setPositionY(poY);
                    best = tmp;
                }
                if (player == -1 && best.Value < beta) {
                    beta = best.Value;
                    //System.out.println(" "+board.Winner()+" prof: "+depth+" beta: "+beta);
                }
                if (player == 1 && best.Value > alpha) {
                    alpha = best.Value;
                }
                if (alpha > beta) {
                    return best;
                }
            }
            return best;
        }
    }
/**
 * Este método es el punto clave del algoritmo min.max, me elige por donde debe seguir en su busqueda
 * @param t
 * @return 
 */
    public int Utilidad(String[][] t) {
        int winner = 0;
        winner = juego.terminado(t);
        if (winner != 0) {
            if (winner == 2) {
                winner = 111; // pc
            } else if (winner == 1) {
                winner = -111; // humano                     
            }
            return winner;
        }

        int cantFichasN = 0;
        int cantFichasB = 0;
        for (int i = 0; i < t.length; i++) {
            for (int j = 0; j < t.length; j++) {
                if (t[i][j].equals("negra")) {
                    cantFichasN += 1;
                } else if (t[i][j].equals("blanca")) {
                    cantFichasB += 1;
                }
            }
        }

        return cantFichasB - cantFichasN;
        // return Min-Max;
    }

}
