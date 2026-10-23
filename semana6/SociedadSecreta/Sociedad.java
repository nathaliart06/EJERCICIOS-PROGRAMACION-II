package semana6.SociedadSecreta;

import java.util.*;

public class Sociedad {

    // Metodo que encola los nombres
    public static Queue<String> encolar(ArrayList<String> nombres) {
        Queue<String> cola = new LinkedList<>();
        for (String n : nombres) {
            cola.offer(n);
        }
        return cola;
    }

    // Metodo que desencola cada nombre y guarda su primera letra en mayuscula en un ArrayList
    public static ArrayList<Character> obtenerIniciales(Queue<String> cola) {
        ArrayList<Character> iniciales = new ArrayList<>();
        while (!cola.isEmpty()) {
            String nombre = cola.poll();
            if (!nombre.isEmpty()) {
                iniciales.add(Character.toUpperCase(nombre.charAt(0)));
            }
        }
        return iniciales;
    }

    // Metodo que ordena alfabeticamente las iniciales y las concatena
    public static String nombreSociedad(ArrayList<String> nombres) {
        ArrayList<Character> iniciales = obtenerIniciales(encolar(nombres));
        Collections.sort(iniciales);
        StringBuilder sb = new StringBuilder();
        for (char c : iniciales) {
            sb.append(c);
        }
        return sb.toString();
    }

    // Metodo que muestra la entrada y la salida
    public static void mostrar(ArrayList<String> nombres) {
        System.out.println("Entrada: " + nombres);
        System.out.println("Salida: " + nombreSociedad(nombres));
    }
}