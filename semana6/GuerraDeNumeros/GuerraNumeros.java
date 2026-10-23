package semana6.GuerraDeNumeros;

import java.util.*;

public class GuerraNumeros {
    public static void main(String[] args) {
        System.out.println("----------------- CASO 1 ----------------");
        Guerra.mostrar(new ArrayList<>(Arrays.asList(2, 8, 7, 5)));
        System.out.println("----------------- CASO 2 ----------------");
        Guerra.mostrar(new ArrayList<>(Arrays.asList(1, 3, 5, 9)));
        System.out.println("----------------- CASO 3 ----------------");
        Guerra.mostrar(new ArrayList<>(Arrays.asList(2, 4, 6, 8)));
    }
}