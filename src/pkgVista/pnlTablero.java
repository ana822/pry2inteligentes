/*
 * En este panel se lleva a cabo todo el juego de othello 
 */
package pkgVista;

import Servidor.Movement;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import pkgModelo.clsJugador;
import pkgModelo.clsTablero;

/**
 *
 * @author Ana Paola Martinez y Carlos Garcia
 */
public class pnlTablero extends javax.swing.JPanel implements ActionListener {

    /**
     * Creates new form pnlTablero
     */
    JButton[][] fichas;
    ImageIcon imgvacia = new ImageIcon("src/imagenes/fondo.png");
    ImageIcon imgnegra = new ImageIcon("src/imagenes/ficha_negra.png");
    ImageIcon imgblanca = new ImageIcon("src/imagenes/ficha_blanca.png");
    ImageIcon imgmedionegra = new ImageIcon("src/imagenes/ficha_medionegra.png");
    ImageIcon imgmedioblanca = new ImageIcon("src/imagenes/ficha_medioblanca.png");
    clsTablero tablero;
    clsJugador jugador;
    int[] cantMov;
    int turno;
    frmTablero t;

    public pnlTablero() {

        initComponents();
obtenerformulario(t);
        cantMov = new int[2];
        turno = 1;
        tablero = new clsTablero();
        fichas = new JButton[8][8];
        this.setLayout(new GridLayout(8, 8, 0, 0));
        for (int i = 0; i < fichas.length; i++) {
            for (int j = 0; j < fichas.length; j++) {
                fichas[i][j] = new JButton(imgvacia);
                fichas[i][j].setPreferredSize(new Dimension(44, 44));
                fichas[i][j].setActionCommand("i" + i + j);
                fichas[i][j].addActionListener(this);
                tablero.llenarMatriz();
                tablero.estadoInicial();
                this.add(fichas[i][j]);
            }
        }

        fichas[3][3].setIcon(imgblanca);
        fichas[3][4].setIcon(imgnegra);
        fichas[4][3].setIcon(imgnegra);
        fichas[4][4].setIcon(imgblanca);
        //-----------------------------------------------------------------
        fichas[3][2].setIcon(imgmedionegra);
        fichas[5][4].setIcon(imgmedionegra);
        fichas[2][3].setIcon(imgmedionegra);
        fichas[4][5].setIcon(imgmedionegra);

        tablero.llenarPosiblesMovimientosInicial();
        try {
            jugador = new clsJugador();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "SERVIDOR NO DISPONIBLE");
            //Logger.getLogger(pnlTablero.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
 public void obtenerformulario(frmTablero frm) {
        this.t = frm;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
   /**
    * En este método se hace graficamente todos los movimientos y actualiza el tablero lógico 
    * @param e 
    */
    @Override
    public void actionPerformed(ActionEvent e) {

        int i = Integer.parseInt((e.getActionCommand().subSequence(1, 2).toString()));
        int j = Integer.parseInt((e.getActionCommand().subSequence(2, 3).toString()));

        String ficha = "i" + i + j;
        if (ficha.equals(e.getActionCommand())) {
            if (turno == 1) {
                if (tablero.puedoMover(new Point(i, j))) {
                    quitarPosibles();
                    fichas[i][j].setIcon(imgnegra);
                    tablero.ponerFicha(new Point(i, j), "negra");
                    pintarEntrePuntos(tablero.validarMedios(new Point(i, j), "negra"));
                    turno = 2;
                   t.mostrarfichas(tablero.contarfichas());
                
                    try {
                        tablero = jugador.EnviarJuagada(tablero.getTablero());

                    } catch (IOException ex) {
                        Logger.getLogger(pnlTablero.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(pnlTablero.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    quitarPosibles();
                    fichas[tablero.getX()][tablero.getY()].setIcon(imgblanca);
                    pintarEntrePuntos(tablero.validarMedios(new Point(tablero.getX(), tablero.getY()), "blanca"));
                    pintarPosibles(tablero.validarMovimientos("negra"));
                    turno = 1;
                    t.mostrarfichas(tablero.contarfichas());
                }
            }
            if (tablero.tableroLLeno()) {
                ganador(tablero.getTablero());
            }

        }
    }
/**
 * Pinta los posibles movimientos que tiene el jugador en el trablero de juego
 * @param mov 
 */
    public void pintarPosibles(LinkedList<Point> mov) {
        for (int i = 0; i < mov.size(); i++) {
            if (turno == 1) {
                fichas[(int) mov.get(i).getX()][(int) mov.get(i).getY()].setIcon(imgmedioblanca);
            } else if (turno == 2) {
                fichas[(int) mov.get(i).getX()][(int) mov.get(i).getY()].setIcon(imgmedionegra);
            }
        }
    }
/**
 * Le entra por parametro una lista de puntos del jugador contrario, los cuales los va a pintar del color de la ficha del jugador actual
 * @param f 
 */
    public void pintarEntrePuntos(LinkedList<Point> f) {
        for (int i = 0; i < f.size(); i++) {
            if (turno == 1) {
                tablero.ponerFicha(new Point((int) f.get(i).getX(), (int) f.get(i).getY()), "negra");
                fichas[(int) f.get(i).getX()][(int) f.get(i).getY()].setIcon(imgnegra);
            } else if (turno == 2) {
                tablero.ponerFicha(new Point((int) f.get(i).getX(), (int) f.get(i).getY()), "blanca");
                fichas[(int) f.get(i).getX()][(int) f.get(i).getY()].setIcon(imgblanca);
            }
        }
    }

 /**
  * Me quita los posibles movimientos del jugador contrario
  */   
    public void quitarPosibles() {
        for (int k = 0; k < fichas.length; k++) {
            for (int l = 0; l < fichas.length; l++) {

                if (fichas[k][l].getIcon().equals(imgmedionegra)) {

                    fichas[k][l].setIcon(imgvacia);
                } else if (fichas[k][l].getIcon().equals(imgmedioblanca)) {
                    fichas[k][l].setIcon(imgvacia);
                }
            }

        }
    }
/**
 * Compara la cantidad de fichas entre el jugador y la máquina y muestra si el jugador ganó, perdió o empató
 * @param tab 
 */
    public void ganador(String[][] tab) {
        int[] mov;

        mov = jugador.CantidadFichas(tab);
        if (mov[0] > mov[1]) {
            jugador.gano();
            JOptionPane.showMessageDialog(this, "Game Over");
        } else if (mov[0] < mov[1]) {
            jugador.perdio();
            JOptionPane.showMessageDialog(this, "Jugador 1 Gana");
        } else {
            jugador.empato();
            JOptionPane.showMessageDialog(this, "Empate!!");
        }
    }
}
