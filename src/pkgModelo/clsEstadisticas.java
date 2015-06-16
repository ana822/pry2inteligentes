/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgModelo;

import java.util.Enumeration;
import java.util.StringTokenizer;
import java.util.Vector;
/**
 *
 * @author Ana
 */
public class clsEstadisticas {
    /*Archivo donde guardamos estadisticas.*/

    Archivo archivo;
    String ruta;

    /*Formato.*/
    StringTokenizer cortador;

    /*Rango de ganados y perdidos.*/
    int mayor, menor;

    /*Estadisticas.*/
    String[][] estadisticas;
    Vector<String[]> tabla = new Vector<String[]>();

    /**
     * Crear nuevas Estadisticas
     */
    public clsEstadisticas() {
        /*Archivo de estadisticas.*/
        this.ruta = "estadisticas.txt";
        archivo = new Archivo(ruta);

        /*Comprobamos si existe, si no lo creamos.*/
        if (archivo.LeerContenido() == null) {
            archivo.EscribirContenido("Camilo 0 0 0");
        }

        /*Leemos las estadisticas.*/
        estadisticas = leerEstadisticas();

    }

    /*Leer las estadisticas, regresa un vector con el nombre[0], juegos ganados[1],
     juegos perdidos[2] y juegos empatados[3]*/
    public String[][] leerEstadisticas() {

        /*Leemos las lï¿½neas del archivo.*/
        String[] lineas = archivo.LeerLineas();
        String[][] datos = new String[archivo.NumeroLineas()][4];

        /*Cortamos las lï¿½neas del archivo.*/
        for (int i = 0; i < lineas.length; i++) {
            cortador = new StringTokenizer(lineas[i]);

            datos[i][0] = cortador.nextToken();
            datos[i][1] = cortador.nextToken();
            datos[i][2] = cortador.nextToken();
            datos[i][3] = cortador.nextToken();

        }

        /*Recuperamos el mayor y el menor dato.*/
        mayor = Integer.parseInt(datos[0][1]);
        menor = Integer.parseInt(datos[datos.length - 1][1]);

        /*Regresar datos.*/
        return datos;
    }

    public String cargarDatos(String nombre) {
        String[][] datos = this.leerEstadisticas();
        for (int i = 0; i < datos.length; i++) {
            if (datos[i][0].equals(nombre)) {
                return ("NOMBRE  -   G   -   P   -   E\r\n\n" + datos[i][0] + " - " + datos[i][1] + " - " + datos[i][2] + " - " + datos[i][3]);
            }
        }
        return "";
    }


    /*Metodo que guarda un nuevo jugador en las estadisticas.*/
    public boolean guardarJugador(clsJugador jugador) {

        /*Volvemos a leer las estadisticas.*/
        estadisticas = leerEstadisticas();

        /*Creamos su representaciï¿½n en un String[]*/
        String[] player = {jugador.getNombre(), "" + jugador.getJuegosGanados(), "" + jugador.getJuegosPerdidos(), "" + jugador.getJuegosEmpatados()};

        /*Preguntamos si entrï¿½ al record de los mejores.*/
        if (jugador.getJuegosGanados() >= menor) {

            /*Agregado.*/
            boolean agregado = false;
            /*Recorremos e insertamos.*/
            for (int i = 0; i < estadisticas.length; i++) {

                if (jugador.getJuegosGanados() > mayor && !agregado) {
                    tabla.add(player);
                    tabla.add(estadisticas[i]);
                    agregado = true;
                    continue;
                } else if (jugador.getJuegosGanados() <= Integer.parseInt(estadisticas[i][1]) && jugador.getJuegosGanados() > Integer.parseInt(estadisticas[(i != estadisticas.length - 1) ? i + 1 : i][1]) && !agregado) {
                    tabla.add(player);
                    tabla.add(estadisticas[i]);
                    agregado = true;
                    continue;
                }

                tabla.add(this.estadisticas[i]);

            }
            guardarArchivo();
            return true;
        }

        return false;

    }

    /*Metodo que recupera la tabla actual.*/
    public String imprimeTabla() {
        Enumeration lista = tabla.elements();
        String res = "";
        String[] tmp;
        int c = 0;
        while (lista.hasMoreElements()) {
            tmp = (String[]) lista.nextElement();
            res += tmp[0] + " " + tmp[1] + " " + tmp[2] + " " + tmp[3] + "\r\n";
            c++;
            if (c == 10) {
                break;
            }
        }
        return res;
    }

    /*Metodo para leer las estadisticas del archivo directamente.*/
    public String leerDatos() {
        String[][] datos = this.leerEstadisticas();
        String res = "NOMBRE  -   G   -   P   -   E\r\n\n";
        for (int i = 0; i < datos.length; i++) {
            res += datos[i][0] + " - " + datos[i][1] + "  - " + datos[i][2] + "  -  " + datos[i][3] + "\r\n";
        }

        return res;
    }

    /*Metodo que guarda las estadisticas en el archivo.*/
    public void guardarArchivo() {

        /*Guardamos el contenido en el archivo.*/
        this.archivo.EscribirContenido(imprimeTabla());

    }

    public void guardarJugador(String nombre) {
        this.archivo.EscribirLinea(nombre);
    }
    /*Metodo que limpia las variables.*/

    public void refrescar() {
        archivo = new Archivo(ruta);
        this.estadisticas = this.leerEstadisticas();
        this.tabla.clear();
        cortador = null;
    }
}
