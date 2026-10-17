package TALLER: PILAS, COLAS, ARREGLOS Y MATRICES DE OBJETOS EN JAVA.ActividadIntegradora;

import java.util.*;

public class Prestamo {
    private int numero;
    private String estudiante;
    private int codigoLibro;

    // Capacidad maxima de la cola de prestamos
    public static final int CAPACIDAD = 10;

    // Constructor
    public Prestamo(int numero, String estudiante, int codigoLibro) {
        this.numero = numero;
        this.estudiante = estudiante;
        this.codigoLibro = codigoLibro;
    }

    public int getNumero() {
        return numero;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public int getCodigoLibro() {
        return codigoLibro;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "numero=" + numero + ", estudiante=" + estudiante + ", codigoLibro=" + codigoLibro + '}';
    }

    // Agrega una solicitud de prestamo al final de la cola
    public static boolean solicitar(Queue<Prestamo> cola, Prestamo p) {
        if (cola.size() >= CAPACIDAD) {
            System.out.println("Cola de prestamos llena: no se registro " + p);
            return false;
        }
        cola.add(p);
        return true;
    }

    // Atiende la primera solicitud: si el libro existe y esta disponible lo presta.
    // Todo lo que pasa se guarda en el historial (pila)
    public static void procesar(Queue<Prestamo> cola, Libro[] libros, Stack<Registro> historial) {
        if (cola.isEmpty()) {
            System.out.println("No hay solicitudes de prestamo pendientes");
            return;
        }
        Prestamo p = cola.poll();
        Libro libro = Libro.buscarPorCodigo(libros, p.getCodigoLibro());

        if (libro == null) {
            System.out.println("Rechazado: el libro " + p.getCodigoLibro() + " no existe");
            Registro.apilar(historial, new Registro("Prestamo rechazado a " + p.getEstudiante() + " (libro inexistente)"));
        } else if (!libro.isDisponible()) {
            System.out.println("Rechazado: " + libro.getTitulo() + " ya esta prestado");
            Registro.apilar(historial, new Registro("Prestamo rechazado a " + p.getEstudiante() + " (" + libro.getTitulo() + " no disponible)"));
        } else {
            libro.setDisponible(false);
            System.out.println("Prestado: " + libro.getTitulo() + " a " + p.getEstudiante());
            Registro.apilar(historial, new Registro("Prestamo de " + libro.getTitulo() + " a " + p.getEstudiante()));
        }
    }

    // Muestra las solicitudes pendientes en orden de llegada
    public static void mostrar(Queue<Prestamo> cola) {
        if (cola.isEmpty()) {
            System.out.println("No hay solicitudes pendientes");
            return;
        }
        for (Prestamo p : cola) {
            System.out.println("  " + p);
        }
    }
}