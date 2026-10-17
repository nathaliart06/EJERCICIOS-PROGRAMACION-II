
import java.util.*;

public class Operacion {
    private int id;
    private String descripcion;
    private String tipo;

    // Capacidad maxima de la pila
    public static final int CAPACIDAD = 10;

    // Constructor
    public Operacion(int id, String descripcion, String tipo) {
        this.id = id;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Operacion{" + "id=" + id + ", descripcion=" + descripcion + ", tipo=" + tipo + '}';
    }

    // Metodo para apilar (push) controlando que la pila no este llena
    public static boolean apilar(Stack<Operacion> pila, Operacion op) {
        if (pila.size() >= CAPACIDAD) {
            System.out.println("Pila llena: no se puede apilar " + op);
            return false;
        }
        pila.push(op);
        System.out.println("Apilada: " + op);
        return true;
    }

    // Metodo para desapilar (pop) controlando que la pila no este vacia
    public static Operacion desapilar(Stack<Operacion> pila) {
        if (pila.empty()) {
            System.out.println("Pila vacia: no hay operaciones para desapilar");
            return null;
        }
        return pila.pop();
    }

    // Metodo para consultar el tope (peek) sin eliminarlo
    public static Operacion tope(Stack<Operacion> pila) {
        if (pila.empty()) {
            System.out.println("Pila vacia: no hay tope");
            return null;
        }
        return pila.peek();
    }

    // Metodo para mostrar la pila desde el tope hasta la base
    public static void mostrar(Stack<Operacion> pila) {
        if (pila.empty()) {
            System.out.println("La pila esta vacia");
            return;
        }
        System.out.println("Elementos de la pila (tope -> base), tamaño: " + pila.size());
        for (int i = pila.size() - 1; i >= 0; i--) {
            System.out.println("  " + pila.get(i));
        }
    }
}