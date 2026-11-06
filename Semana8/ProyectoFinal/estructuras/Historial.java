package Semana8.ProyectoFinal.estructuras;

import Semana8.ProyectoFinal.excepciones.PilaDeshacerVaciaException;

import java.util.*;

/**
 * Sistema deshacer/rehacer con dos pilas.
 * - Cada operacion nueva se apila en pilaDeshacer y se vacia pilaRehacer.
 * - deshacer(): saca de pilaDeshacer, revierte y la pasa a pilaRehacer.
 * - rehacer(): saca de pilaRehacer, vuelve a aplicar y la pasa a pilaDeshacer.
 */
public class Historial {
    private static Stack<Operacion> pilaDeshacer = new Stack<>();
    private static Stack<Operacion> pilaRehacer = new Stack<>();

    /** Metodo para apilar una operacion nueva */
    public static void registrar(Operacion op) {
        pilaDeshacer.push(op);
        pilaRehacer.clear(); // una operacion nueva borra lo que se podia rehacer
    }

    /** Metodo para deshacer la ultima operacion */
    public static String deshacer() throws PilaDeshacerVaciaException {
        if (pilaDeshacer.empty()) {
            throw new PilaDeshacerVaciaException("No hay operaciones para deshacer");
        }
        Operacion op = pilaDeshacer.pop();
        String mensaje = op.deshacer();
        pilaRehacer.push(op);
        return mensaje;
    }

    /** Metodo para rehacer lo ultimo que se deshizo */
    public static String rehacer() throws PilaDeshacerVaciaException {
        if (pilaRehacer.empty()) {
            throw new PilaDeshacerVaciaException("No hay operaciones para rehacer");
        }
        Operacion op = pilaRehacer.pop();
        String mensaje = op.rehacer();
        pilaDeshacer.push(op);
        return mensaje;
    }

    /** Metodo que muestra la pila de deshacer desde el tope hasta la base */
    public static void mostrar() {
        if (pilaDeshacer.empty()) {
            System.out.println("  (no hay operaciones para deshacer)");
            return;
        }
        for (int i = pilaDeshacer.size() - 1; i >= 0; i--) {
            System.out.println("  " + pilaDeshacer.get(i));
        }
    }

    public static int tamanoDeshacer() {
        return pilaDeshacer.size();
    }

    public static int tamanoRehacer() {
        return pilaRehacer.size();
    }

    /** Metodo que vacia las dos pilas (se usa despues de cargar los datos de prueba) */
    public static void limpiar() {
        pilaDeshacer.clear();
        pilaRehacer.clear();
    }
}
