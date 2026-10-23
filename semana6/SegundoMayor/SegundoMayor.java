package semana6.SegundoMayor;

import java.util.*;

public class SegundoMayor {
    public static void main(String[] args) {
        System.out.println("----------------- CASO 1 ----------------");
        Mayores.mostrar(new ArrayList<>(Arrays.asList(10, 40, 30, 20, 50)));
        System.out.println("----------------- CASO 2 ----------------");
        Mayores.mostrar(new ArrayList<>(Arrays.asList(5, 5, 5, 5, 9)));
        System.out.println("----------------- CASO 3 ----------------");
        Mayores.mostrar(new ArrayList<>(Arrays.asList(-3, -1, -7, -2)));
    }
}