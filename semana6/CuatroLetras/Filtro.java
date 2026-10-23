package semana6.CuatroLetras;


import java.util.*;

public class Filtro {

    // Metodo que encola todas las palabras
    public static Queue<String> encolar(ArrayList<String> palabras) {
        Queue<String> cola = new LinkedList<>();
        for (String p : palabras) {
            cola.offer(p);
        }
        return cola;
    }

    // Metodo que desencola cada palabra y guarda en un ArrayList solo las de 4 letras
    public static ArrayList<String> filtrarCuatroLetras(ArrayList<String> palabras) {
        Queue<String> cola = encolar(palabras);
        ArrayList<String> resultado = new ArrayList<>();
        while (!cola.isEmpty()) {
            String palabra = cola.poll();
            if (palabra.length() == 4) {
                resultado.add(palabra);
            }
        }
        return resultado;
    }

    // Metodo que muestra la entrada y la salida
    public static void mostrar(ArrayList<String> palabras) {
        System.out.println("Entrada: " + palabras);
        System.out.println("Salida: " + filtrarCuatroLetras(palabras));
    }
}