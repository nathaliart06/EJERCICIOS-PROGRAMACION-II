package Semana8.ProyectoFinal.estructuras;

import Semana8.ProyectoFinal.modelo.Reporte;

import java.util.*;

/**
 * Navegacion entre reportes con dos pilas, como los botones atras/adelante del navegador web.
 * - Ver un reporte nuevo: el actual va a "atras" y se vacia "adelante".
 * - Atras: el actual va a "adelante" y se saca el tope de "atras".
 * - Adelante: el actual va a "atras" y se saca el tope de "adelante".
 */
public class NavegadorReportes {
    private static Stack<Reporte> atras = new Stack<>();
    private static Stack<Reporte> adelante = new Stack<>();
    private static Reporte actual = null;

    /** Metodo que guarda un reporte recien generado como el actual */
    public static void visitar(Reporte r) {
        if (actual != null) {
            atras.push(actual);
        }
        actual = r;
        adelante.clear();
    }

    /** Metodo para ir al reporte anterior. Retorna false si no hay */
    public static boolean irAtras() {
        if (atras.empty()) {
            System.out.println(">> No hay reportes anteriores");
            return false;
        }
        adelante.push(actual);
        actual = atras.pop();
        return true;
    }

    /** Metodo para ir al reporte siguiente. Retorna false si no hay */
    public static boolean irAdelante() {
        if (adelante.empty()) {
            System.out.println(">> No hay reportes siguientes");
            return false;
        }
        atras.push(actual);
        actual = adelante.pop();
        return true;
    }

    public static Reporte getActual() {
        return actual;
    }

    public static int cantidadAtras() {
        return atras.size();
    }

    public static int cantidadAdelante() {
        return adelante.size();
    }

    /** Metodo que vacia el navegador */
    public static void limpiar() {
        atras.clear();
        adelante.clear();
        actual = null;
    }
}
