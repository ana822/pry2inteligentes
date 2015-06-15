/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgModelo;

import java.awt.Point;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Ana
 */
public class clsTablero implements Serializable {

    private String[][] tablero = new String[8][8];
    private clsFicha ficha = new clsFicha();
    private LinkedList<Point> mov;
    LinkedList<Point> puntosPintar;

    public LinkedList<Point> getMov() {
        return mov;
    }

    public void setMov(LinkedList<Point> mov) {
        this.mov = mov;
    }

    public void llenarMatriz() {
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                tablero[i][j] = ficha.getVacia();
            }
        }
    }

    public void estadoInicial() {
        tablero[3][3] = ficha.getBlanca();
        tablero[3][4] = ficha.getNegra();
        tablero[4][3] = ficha.getNegra();
        tablero[4][4] = ficha.getBlanca();
    }

//    public void mostrar() {
//        for (int i = 0; i < tablero.length; i++) {
//            for (int j = 0; j < tablero.length; j++) {
//                System.out.println(" " + tablero[i][j] + i + j);
//            }
//
//        }
//    }
    /**
     * meter una ficha en le tablero
     *
     * @param pos
     * @param ficha
     */
    public void ponerFicha(Point pos, String ficha) {

        tablero[(int) pos.getX()][(int) pos.getY()] = ficha;
    }

    /**
     * Método que inserta en la lista de posbles movimientos, los posibles
     * movimientos iniciales
     */
    public void llenarPosiblesMovimientosInicial() {
        mov = new LinkedList<>();
        mov.add(new Point(3, 2));
        mov.add(new Point(5, 4));
        mov.add(new Point(2, 3));
        mov.add(new Point(4, 5));
    }

    /**
     * Revisa si el punto seleccionado se encuentra en la lista de posibles
     * movimientos
     *
     * @param pos la posición que de la casilla seleccionada
     * @return Retorna true o false según sea el caso
     */
    public boolean puedoMover(Point pos) {
        for (int i = 0; i < mov.size(); i++) {
            if (pos.getX() == mov.get(i).getX() && pos.getY() == mov.get(i).getY()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Se valida los posibles movimientos que el jugador puede hacer
     *
     * @param ficha la ficha a la cual se le evaluará los posibles movimientos
     * @return
     */
    public LinkedList<Point> validarMovimientos(String ficha) {
        mov = new LinkedList();
        Point p;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
//arriba

                if ((i - 1) > 0 && tablero[i][j].equals(ficha) && tablero[i - 1][j].equals(fichaContraria(ficha))) {
                    //System.out.println("holaaaaaaaaaaaa mamáaa");
                    p = movReversi(new Point(i, j), ficha, 0);
                    if (p.getX() != -100 && p.getY() != -100) {

                        mov.add(p);
                        //System.out.println("estos son " + mov);
                    }
                }
//abajo               
                if ((i + 1) < 7 && tablero[i][j].equals(ficha) && tablero[i + 1][j].equals(fichaContraria(ficha))) {
                    //System.out.println("holaaaaaaaaaaaa mamáaa");
                    p = movReversi(new Point(i, j), ficha, 1);
                    if (p.getX() != -100 && p.getY() != -100) {

                        mov.add(p);
                        //System.out.println("estos son " + mov);
                    }
                }
//izquierda   
                if ((j - 1) > 0 && tablero[i][j].equals(ficha) && tablero[i][j - 1].equals(fichaContraria(ficha))) {
                    //System.out.println("holaaaaaaaaaaaa mamáaa");
                    p = movReversi(new Point(i, j), ficha, 2);
                    if (p.getX() != -100 && p.getY() != -100) {

                        mov.add(p);
                        //System.out.println("estos son " + mov);
                    }
                }
//derecha
                if ((j + 1) < 7 && tablero[i][j].equals(ficha) && tablero[i][j + 1].equals(fichaContraria(ficha))) {
                    //System.out.println("holaaaaaaaaaaaa mamáaa");
                    p = movReversi(new Point(i, j), ficha, 3);
                    if (p.getX() != -100 && p.getY() != -100) {

                        mov.add(p);
                        //System.out.println("estos son " + mov);
                    }
                }
//arriba-izquierda
                if ((i - 1) > 0 && (j - 1) > 0 && tablero[i][j].equals(ficha) && tablero[i - 1][j - 1].equals(fichaContraria(ficha))) {
                    //System.out.println("holaaaaaaaaaaaa mamáaa");
                    p = movReversi(new Point(i, j), ficha, 4);
                    if (p.getX() != -100 && p.getY() != -100) {

                        mov.add(p);
                        //System.out.println("estos son " + mov);
                    }
                }
//abajo-izquierda
                if ((i + 1) < 7 && (j - 1) > 0 && tablero[i][j].equals(ficha) && tablero[i + 1][j - 1].equals(fichaContraria(ficha))) {
                    //System.out.println("holaaaaaaaaaaaa mamáaa");
                    p = movReversi(new Point(i, j), ficha, 5);
                    if (p.getX() != -100 && p.getY() != -100) {

                        mov.add(p);
                        //System.out.println("estos son " + mov);
                    }
                }
//arriba-derecha
                if ((i - 1) > 0 && (j + 1) < 7 && tablero[i][j].equals(ficha) && tablero[i - 1][j + 1].equals(fichaContraria(ficha))) {
                    //System.out.println("holaaaaaaaaaaaa mamáaa");
                    p = movReversi(new Point(i, j), ficha, 6);
                    if (p.getX() != -100 && p.getY() != -100) {

                        mov.add(p);
                        //System.out.println("estos son " + mov);
                    }
                }
//abajo-derecha  
                if ((i + 1) < 7 && (j + 1) < 7 && tablero[i][j].equals(ficha) && tablero[i + 1][j + 1].equals(fichaContraria(ficha))) {
                    //System.out.println("holaaaaaaaaaaaa mamáaa");
                    p = movReversi(new Point(i, j), ficha, 7);
                    if (p.getX() != -100 && p.getY() != -100) {

                        mov.add(p);
                        //System.out.println("estos son " + mov);
                    }
                }
            }
        }

        return mov;
    }

    /**
     * Evalúa cada uno de los posibles movimientos y se verifica la casilla
     * vacia para agregarla a la lista de posibles movimientos
     *
     * @param punto a partir del cual se hará la búsqueda de las casillas del
     * color contrario
     * @param ficha para saber a que color corresponde esa casilla
     * @param mov
     * @return
     */
    public Point movReversi(Point punto, String ficha, int mov) {
        Point p = new Point(-100, -100);

//arriba
        if (mov == 0) {

            for (int i = (int) punto.getX() - 1; i > 0; i--) {

                if (tablero[i][(int) punto.getY()].equals(fichaContraria(ficha))) {

                    if (tablero[i - 1][(int) punto.getY()].equals("vacia")) {
                        p.setLocation(i - 1, (int) punto.getY());
                        break;
                    }
                }
            }
            return p;
        }
//abajo        
        if (mov == 1) {

            for (int i = (int) punto.getX() + 1; i < 7; i++) {

                if (tablero[i][(int) punto.getY()].equals(fichaContraria(ficha))) {

                    if (tablero[i + 1][(int) punto.getY()].equals("vacia")) {
                        p.setLocation(i + 1, (int) punto.getY());
                        break;
                    }
                }
            }
            return p;
        }
//izquierda
        if (mov == 2) {
            for (int i = (int) punto.getY() - 1; i > 0; i--) {
                if (tablero[(int) punto.getX()][i].equals(fichaContraria(ficha))) {
                    if (tablero[(int) punto.getX()][i - 1].equals("vacia")) {
                        p.setLocation((int) punto.getX(), i - 1);
                        break;
                    }
                }
            }
            return p;
        }
//derecha
        if (mov == 3) {
            for (int i = (int) punto.getY() + 1; i < 7; i++) {
                if (tablero[(int) punto.getX()][i].equals(fichaContraria(ficha))) {

                    if (tablero[(int) punto.getX()][i + 1].equals("vacia")) {
                        p.setLocation((int) punto.getX(), i + 1);
                        break;
                    }
                }
            }
            return p;
        }
//arriba-izquierda
        if (mov == 4) {

            for (int i = (int) punto.getX() - 1, j = (int) punto.getY() - 1; i > 0 && j > 0; i--, j--) {

                if (tablero[i][j].equals(fichaContraria(ficha))) {

                    if (tablero[i - 1][j - 1].equals("vacia")) {
                        p.setLocation(i - 1, j - 1);
                        break;
                    }
                }
            }
            return p;
        }
//abajo-izquierda
        if (mov == 5) {

            for (int i = (int) punto.getX() + 1, j = (int) punto.getY() - 1; i < 7 && j > 0; i++, j--) {

                if (tablero[i][j].equals(fichaContraria(ficha))) {

                    if (tablero[i + 1][j - 1].equals("vacia")) {
                        p.setLocation(i + 1, j - 1);
                        break;
                    }
                }
            }
            return p;
        }
//arriba-derecha
        if (mov == 6) {

            for (int i = (int) punto.getX() - 1, j = (int) punto.getY() + 1; i > 0 && j < 7; i--, j++) {

                if (tablero[i][j].equals(fichaContraria(ficha))) {

                    if (tablero[i - 1][j + 1].equals("vacia")) {
                        p.setLocation(i - 1, j + 1);
                        break;
                    }
                }
            }
            return p;
        }
//abajo-derecha     
        if (mov == 7) {

            for (int i = (int) punto.getX() + 1, j = (int) punto.getY() + 1; i < 7 && j < 7; i++, j++) {

                if (tablero[i][j].equals(fichaContraria(ficha))) {

                    if (tablero[i + 1][j + 1].equals("vacia")) {
                        p.setLocation(i + 1, j + 1);
                        break;
                    }
                }
            }
            return p;
        }
        return p;
    }

    /**
     * Me permite determinar el color contrario de una ficha
     *
     * @param ficha
     * @return la ficha del color contrario
     */
    public String fichaContraria(String ficha) {
        if (ficha.equals("negra")) {
            ficha = "blanca";
        } else if (ficha.equals("blanca")) {
            ficha = "negra";
        }
        return ficha;
    }

    /**
     *
     * @param pinicial la posición del movimiento realizado
     * @param ficha Color de la ficha
     * @param mov
     * @return el punto hasta el cual se debe evaluar para poder pintar las
     * fichas del color contrario
     */
    public Point ultimaFicha(Point pinicial, String ficha, int mov) {
        Point pfinal = new Point();
//arriba
        if (mov == 0) {

            for (int i = (int) pinicial.getX(); i >= 0; i--) {

                if (tablero[i][(int) pinicial.getY()].equals(ficha)) {
                    pfinal.setLocation(i, (int) pinicial.getY());

                }

            }
        }
//abajo        
        if (mov == 1) {
            //if (pfinal.getX() >= pinicial.getX() && pfinal.getY() == pinicial.getY()) {
            for (int i = (int) pinicial.getX(); i < 8; i++) {

                if (tablero[i][(int) pinicial.getY()].equals(ficha)) {
                    pfinal.setLocation(i, (int) pinicial.getY());

                }
            }
            //}
        }
//izquierda        
        if (mov == 2) {

            //if (pfinal.getY() <= pinicial.getY() && pfinal.getX() == pinicial.getX()) {
            for (int i = (int) pinicial.getY(); i >= 0; i--) {

                if (tablero[(int) pinicial.getX()][i].equals(ficha)) {
                    pfinal.setLocation((int) pinicial.getX(), i);

                }
            }
            //}
        }
//derecha        
        if (mov == 3) {
            for (int i = (int) pinicial.getY(); i < 8; i++) {
                if (tablero[(int) pinicial.getX()][i].equals(ficha)) {
                    pfinal.setLocation((int) pinicial.getX(), i);
                }
            }
        }
        if (mov == 4) {

            //if (pfinal.getY() <= pinicial.getY() && pfinal.getX() == pinicial.getX()) {
            for (int i = (int) pinicial.getX(), j = (int) pinicial.getY(); i >= 0 && j >= 0; i--, j--) {

                if (tablero[i][j].equals(ficha)) {
                    pfinal.setLocation(i, j);

                }
            }
        }
//arriba-derecha        
        if (mov == 5) {

            for (int i = (int) pinicial.getX(), j = (int) pinicial.getY(); i >= 0 && j < 8; i--, j++) {

                if (tablero[i][j].equals(ficha)) {
                    pfinal.setLocation(i, j);

                }
            }
        }
//abajo-izquierda         
        if (mov == 6) {

            for (int i = (int) pinicial.getX(), j = (int) pinicial.getY(); i < 8 && j >= 0; i++, j--) {

                if (tablero[i][j].equals(ficha)) {
                    pfinal.setLocation(i, j);

                }
            }
        }
//abajo-derecha          
        if (mov == 7) {

            for (int i = (int) pinicial.getX(), j = (int) pinicial.getY(); i < 8 && j < 8; i++, j++) {

                if (tablero[i][j].equals(ficha)) {
                    pfinal.setLocation(i, j);

                }
            }
        }

        return pfinal;
    }

    /**
     * Se evalúan los puntos que serán cambiados de color por el color contrario
     *
     * @param posIni
     * @return la lista de los puntos que hay entre punto inicial y un punto
     * final.
     */
    public LinkedList<Point> validarMedios(Point posIni, String ficha) {
        puntosPintar = new LinkedList<>();

        for (int mov = 0; mov < 8; mov++) {
            Point posFinal = ultimaFicha(posIni, ficha, mov);
            // arriba           
            if (mov == 0) {

                //if (posFinal.getX() <= posIni.getX() && posIni.getY() == posFinal.getY()) {
                for (int i = (int) posIni.getX(); i >= (int) posFinal.getX(); i--) {
                    if (tablero[i][(int) posIni.getY()].equals("vacia")) {
                        puntosPintar.clear();
                        break;

                    } else {
                        puntosPintar.add(new Point(i, (int) posIni.getY()));
                    }
                }
            }
            //}
            //abajo
            if (mov == 1) {

                // if (posFinal.getX() >= posIni.getX() && posIni.getY() == posFinal.getY()) {
                for (int i = (int) posIni.getX(); i <= (int) posFinal.getX(); i++) {
                    if (tablero[i][(int) posIni.getY()].equals("vacia")) {
                        puntosPintar.clear();
                        break;

                    } else {
                        puntosPintar.add(new Point(i, (int) posIni.getY()));
                    }
                }
                //}
            }
//izquierda
            if (mov == 2) {

                //if (posFinal.getY() >= posIni.getX() && posIni.getX() == posFinal.getX()) {
                for (int i = (int) posIni.getY(); i >= (int) posFinal.getY(); i--) {
                    if (tablero[(int) posIni.getX()][i].equals("vacia")) {
                        puntosPintar.clear();
                        break;

                    } else {
                        puntosPintar.add(new Point((int) posIni.getX(), i));
                    }
                }
            }
//derecha            
            if (mov == 3) {

                //if (posFinal.getY() >= posIni.getX() && posIni.getX() == posFinal.getX()) {
                for (int i = (int) posIni.getY(); i < (int) posFinal.getY(); i++) {
                    if (tablero[(int) posFinal.getX()][i].equals("vacia")) {
                        puntosPintar.clear();
                        break;

                    } else {
                        puntosPintar.add(new Point((int) posIni.getX(), i));
                    }
                }
            }
//arriba - izquierda  
            if (mov == 4) {

                //if (posFinal.getY() >= posIni.getX() && posIni.getX() == posFinal.getX()) {
                for (int i = (int) posIni.getX(), j = (int) posIni.getY(); i >= (int) posFinal.getX() && j >= (int) posFinal.getY(); i--, j--) {
                    if (tablero[i][j].equals("vacia")) {
                        puntosPintar.clear();
                        break;

                    } else {
                        puntosPintar.add(new Point(i, j));
                        System.out.println("jmmm aquui " + i + j);
                    }
                }
            }
//arriba-derecha            
            if (mov == 5) {

                //if (posFinal.getY() >= posIni.getX() && posIni.getX() == posFinal.getX()) {
                for (int i = (int) posIni.getX(), j = (int) posIni.getY(); i >= (int) posFinal.getX() && j < (int) posFinal.getY(); i--, j++) {
                    if (tablero[i][j].equals("vacia")) {
                        puntosPintar.clear();
                        break;

                    } else {
                        puntosPintar.add(new Point(i, j));
                        System.out.println("jmmm aquui " + i + j);
                    }
                }
            }
// abajo -  izquierda       
            if (mov == 6) {

                //if (posFinal.getY() >= posIni.getX() && posIni.getX() == posFinal.getX()) {
                for (int i = (int) posIni.getX(), j = (int) posIni.getY(); i < (int) posFinal.getX() && j >= (int) posFinal.getY(); i++, j--) {
                    if (tablero[i][j].equals("vacia")) {
                        puntosPintar.clear();
                        break;

                    } else {
                        puntosPintar.add(new Point(i, j));
                        System.out.println("jmmm aquui " + i + j);
                    }
                }
            }
//abajo-derecha            
            if (mov == 7) {

                //if (posFinal.getY() >= posIni.getX() && posIni.getX() == posFinal.getX()) {
                for (int i = (int) posIni.getX(), j = (int) posIni.getY(); i < (int) posFinal.getX() && j < (int) posFinal.getY(); i++, j++) {
                    if (tablero[i][j].equals("vacia")) {
                        puntosPintar.clear();
                        break;

                    } else {
                        puntosPintar.add(new Point(i, j));
                        System.out.println("jmmm aquui " + i + j);
                    }
                }
            }
        }
        return puntosPintar;
    }

    /**
     * Me determina si una casilla está vacía o no
     *
     * @param f
     * @return
     */
    public boolean esVacio(String f) {

        if (f.equals(ficha.getVacia())) {
            return true;
        }
        return false;
    }

    /**
     * me verifica si el tablero tiene todas las fichas, que no quede vacíos
     *
     * @return si está o no lleno el tablero
     */
    public boolean tableroLLeno() {
        int cont = 0;

        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero.length; j++) {
                if (!tablero[i][j].equals("vacia")) {
                    cont++;
                    //System.out.println("tiene ficha " + tablero[i][j]);
                }
            }
            if (cont == 64) {
                return true;
            }

        }
        return false;
    }

    public String[][] getTablero() {
        return tablero;
    }

    public void setTablero(String[][] tablero) {
        this.tablero = tablero;
    }

    /// Chekea el tablero, determina si hay ganador, Return -1, 1 ó 0
    public int GetWinner() {
        int winner = 0;
        winner = terminado(getTablero());
        if (winner != 0) {
            if (winner == 2) {
                winner = -1; // pc
            } else if (winner == 1) {
                winner = 1; // humano                     
            }
            return winner;
        }
        return winner;
    }

    /// chekea si todas las posiciones estan ocupadas        
    /// retorna true o false
    public boolean IsTie() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (!getTablero()[i][j].equals("vacia")) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean GameEnded() {
        return (GetWinner() != 0 || IsTie());
    }

    //devolver quien es el ganador; 0=empate;1=Jugador;2=Maquina
    public int terminado(String[][] tablero) {
        int cantFichasN = 0;
        if (tableroLLeno()) {
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero.length; j++) {
                    if (tablero[i][j].equals("negra")) {//negra=juagador
                        cantFichasN += 1;
                    }
                }
            }
            if (cantFichasN > 32) {
                return 1;
            } else if (cantFichasN < 32) {
                return 2;
            }
        }
        return 0;
    }

    public clsTablero(String[][] tablero1) {
        tablero = new String[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = tablero1[i][j];
            }
        }
    }

    public Object Clone() {
        clsTablero board = new clsTablero(tablero);
//        for (int i = 0; i < board.getTablero().length; i++) {
//            for (int j = 0; j < board.getTablero().length; j++) {
//                System.out.print(board.getTablero()[i][j]);
//            }
//            System.out.println("");
//        }
        return board;
    }

    public clsTablero() {
    }
}
