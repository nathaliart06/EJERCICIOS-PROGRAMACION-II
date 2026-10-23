package semana6.PanSimilar;

import java.util.*;

public class PanSimilar {
    public static void main(String[] args) {
        System.out.println("----------------- CASO 1 ----------------");
        Deque<String> s1 = Sandwish.crearSandwich(new ArrayList<>(Arrays.asList("pan blanco", "lechuga", "pan blanco")));
        Deque<String> s2 = Sandwish.crearSandwich(new ArrayList<>(Arrays.asList("pan blanco", "tomate", "pan blanco")));
        Sandwish.mostrar(s1, s2);

        System.out.println("----------------- CASO 2 ----------------");
        Deque<String> s3 = Sandwish.crearSandwich(new ArrayList<>(Arrays.asList("pan integral", "queso", "pan integral")));
        Deque<String> s4 = Sandwish.crearSandwich(new ArrayList<>(Arrays.asList("pan blanco", "queso", "pan integral")));
        Sandwish.mostrar(s3, s4);
    }
}