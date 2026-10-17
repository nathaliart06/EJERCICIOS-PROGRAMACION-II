import java.util.*;

public class Solicitud {
    private int numero;
    private String estudiante;
    private String tramite;

    // Capacidad maxima de la cola
    public static final int CAPACIDAD = 10;

    // Constructor
    public Solicitud(int numero, String estudiante, String tramite) {
        this.numero = numero;
        this.estudiante = estudiante;
        this.tramite = tramite;
    }

    public int getNumero() {
        return numero;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public String getTramite() {
        return tramite;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "numero=" + numero + ", estudiante=" + estudiante + ", tramite=" + tramite + '}';
    }

    // Metodo para registrar (encolar) controlando que la cola no este llena
    public static boolean registrar(Queue<Solicitud> cola, Solicitud s) {
        if (cola.size() >= CAPACIDAD) {
            System.out.println("Cola llena: no se puede registrar " + s);
            return false;
        }
        cola.add(s);
        System.out.println("Registrada: " + s);
        return true;
    }

    // Metodo para atender y retirar la primera solicitud (poll)
    public static Solicitud atender(Queue<Solicitud> cola) {
        if (cola.isEmpty()) {
            System.out.println("Cola vacia: no hay solicitudes por atender");
            return null;
        }
        return cola.poll();
    }

    // Metodo para consultar la siguiente solicitud sin retirarla (peek)
    public static Solicitud siguiente(Queue<Solicitud> cola) {
        if (cola.isEmpty()) {
            System.out.println("Cola vacia: no hay siguiente solicitud");
            return null;
        }
        return cola.peek();
    }

    // Metodo para mostrar las solicitudes pendientes en orden de llegada
    public static void mostrar(Queue<Solicitud> cola) {
        if (cola.isEmpty()) {
            System.out.println("La cola esta vacia");
            return;
        }
        System.out.println("Solicitudes pendientes (frente -> final), tamaño: " + cola.size());
        for (Solicitud s : cola) {
            System.out.println("  " + s);
        }
    }
}