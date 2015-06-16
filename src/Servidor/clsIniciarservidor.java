/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

/**
 *
 * @author Ana
 */
public class clsIniciarservidor {
    public static void main(String[] args) {
         Thread t = new Thread(new clsServidorMultiplicador());
        t.start();
        System.out.println("inicio servidor");
    }
    
}
