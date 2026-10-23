package semana6.GuerraDeNumeros;

import java.util.*;

public class Guerra {

    // Metodo que encola los numeros
    public static Queue<Integer> encolar(ArrayList<Integer> numeros) {
        Queue<Integer> cola = new LinkedList<>();
        for (int n : numeros) {
            cola.offer(n);
        }
        return cola;
    }

    // Metodo que desencola cada numero, suma por separado pares e impares
    // y retorna la diferencia absoluta entre ambas sumas
    public static int diferencia(ArrayList<Integer> numeros) {
        Queue<Integer> cola = encolar(numeros);
        int sumaPares = 0;
        int sumaImpares = 0;
        while (!cola.isEmpty()) {
            int n = cola.poll();
            if (n % 2 == 0) {
                sumaPares += n;
            } else {
                sumaImpares += n;
            }
        }
        System.out.println("Impares: " + sumaImpares + " | Pares: " + sumaPares);
        return Math.abs(sumaImpares - sumaPares);
    }

    // Metodo que muestra la entrada y la salida
    public static void mostrar(ArrayList<Integer> numeros) {
        System.out.println("Entrada: " + numeros);
        System.out.println("Salida: " + diferencia(numeros));
    }
}