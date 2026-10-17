package TallerPilasColas.ActividadIntegradora;

import java.util.*;

public class Registro {
    private static int contador = 0; // genera el id automaticamente
    private int id;
    private String descripcion;

    // Capacidad maxima del historial: guarda solo las ultimas operaciones
    public static final int CAPACIDAD = 10;

    // Constructor
    public Registro(String descripcion) {
        contador++;
        this.id = contador;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "#" + id + " " + descripcion;
    }

    // Apila la operacion. Si la pila esta llena se descarta la mas antigua (la base)
    // para que el historial siempre tenga las ultimas operaciones
    public static void apilar(Stack<Registro> historial, Registro r) {
        if (historial.size() >= CAPACIDAD) {
            historial.remove(0);
        }
        historial.push(r);
    }

    // Deshace (saca) la ultima operacion registrada
    public static Registro desapilar(Stack<Registro> historial) {
        if (historial.empty()) {
            System.out.println("El historial esta vacio");
            return null;
        }
        return historial.pop();
    }

    // Muestra el historial desde la mas reciente a la mas antigua
    public static void mostrar(Stack<Registro> historial) {
        if (historial.empty()) {
            System.out.println("El historial esta vacio");
            return;
        }
        for (int i = historial.size() - 1; i >= 0; i--) {
            System.out.println("  " + historial.get(i));
        }
    }
}