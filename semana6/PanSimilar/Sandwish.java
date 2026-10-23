package semana6.PanSimilar;

import java.util.*;

public class Sandwish {

    // Metodo que crea un sandwich (Deque) a partir de un ArrayList de ingredientes
    public static Deque<String> crearSandwich(ArrayList<String> ingredientes) {
        Deque<String> sandwich = new ArrayDeque<>();
        for (String ingrediente : ingredientes) {
            sandwich.addLast(ingrediente);
        }
        return sandwich;
    }

    // Metodo que verifica si dos sandwiches usan el mismo pan al inicio y al final
    public static boolean verificarPan(Deque<String> s1, Deque<String> s2) {
        if (s1.isEmpty() || s2.isEmpty()) {
            System.out.println("Uno de los sandwiches esta vacio");
            return false;
        }
        boolean mismoInicio = s1.peekFirst().equals(s2.peekFirst());
        boolean mismoFinal = s1.peekLast().equals(s2.peekLast());
        return mismoInicio && mismoFinal;
    }

    // Metodo que muestra los sandwiches y el resultado
    public static void mostrar(Deque<String> s1, Deque<String> s2) {
        System.out.println("S1 = " + s1 + " | inicio: " + s1.peekFirst() + " | final: " + s1.peekLast());
        System.out.println("S2 = " + s2 + " | inicio: " + s2.peekFirst() + " | final: " + s2.peekLast());
        System.out.println("Salida: " + verificarPan(s1, s2));
    }
}