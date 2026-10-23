package semana6.FormatoTelefono;

import java.util.*;


public class Telefono {

    // Metodo que valida que se entreguen exactamente 10 digitos (cada uno entre 0 y 9)
    public static boolean validar(ArrayList<Integer> digitos) {
        if (digitos.size() != 10) {
            System.out.println("Error: se necesitan exactamente 10 digitos y llegaron " + digitos.size());
            return false;
        }
        for (int d : digitos) {
            if (d < 0 || d > 9) {
                System.out.println("Error: " + d + " no es un digito valido");
                return false;
            }
        }
        return true;
    }

    // Metodo que encola los digitos en el mismo orden en que llegan
    public static Queue<Integer> encolar(ArrayList<Integer> digitos) {
        Queue<Integer> cola = new LinkedList<>();
        for (int d : digitos) {
            cola.offer(d);
        }
        return cola;
    }

    // Metodo que construye el formato (ddd) ddd-dddd consumiendo la cola en orden FIFO
    public static String formatear(ArrayList<Integer> digitos) {
        if (!validar(digitos)) {
            return null;
        }
        Queue<Integer> cola = encolar(digitos);
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        for (int i = 0; i < 3; i++) sb.append(cola.poll());
        sb.append(") ");
        for (int i = 0; i < 3; i++) sb.append(cola.poll());
        sb.append("-");
        for (int i = 0; i < 4; i++) sb.append(cola.poll());
        return sb.toString();
    }

    // Metodo que muestra la entrada y la salida
    public static void mostrar(ArrayList<Integer> digitos) {
        System.out.println("Entrada: " + digitos);
        String resultado = formatear(digitos);
        System.out.println("Salida: " + (resultado != null ? resultado : "Numero invalido"));
    }
}