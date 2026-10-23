package semana6.SegundoMayor;

import java.util.*;

public class Mayores {

    // Metodo que encola todos los numeros del ArrayList
    public static Queue<Integer> encolar(ArrayList<Integer> numeros) {
        Queue<Integer> cola = new LinkedList<>();
        for (int n : numeros) {
            cola.offer(n);
        }
        return cola;
    }

    // Metodo que desencola los numeros manteniendo max1 y max2.
    // La pila guarda el historial: cada vez que aparece un nuevo maximo se apila.
    // Si todos los numeros son iguales no existe un segundo mayor distinto y se retorna ese valor.
    public static int segundoMayor(ArrayList<Integer> numeros) {
        if (numeros.size() < 2) {
            throw new IllegalArgumentException("Se necesitan al menos dos numeros");
        }
        Queue<Integer> cola = encolar(numeros);
        Stack<Integer> historialMaximos = new Stack<>();

        int max1 = cola.poll();
        int max2 = Integer.MIN_VALUE;
        historialMaximos.push(max1);

        while (!cola.isEmpty()) {
            int actual = cola.poll();
            if (actual > max1) {
                max2 = max1;
                max1 = actual;
                historialMaximos.push(actual);
            } else if (actual < max1 && actual > max2) {
                max2 = actual;
            }
        }
        System.out.println("Historial de maximos (pila): " + historialMaximos
                + " | max1 = " + max1);

        if (max2 == Integer.MIN_VALUE) {
            return max1; // todos los numeros eran iguales
        }
        return max2;
    }

    // Metodo que muestra la entrada y la salida
    public static void mostrar(ArrayList<Integer> numeros) {
        System.out.println("Entrada: " + numeros);
        System.out.println("Salida: " + segundoMayor(numeros));
    }
}