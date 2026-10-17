package semana6.VotosFavorVsContra;

import java.util.Queue;

public class Votos {

    static int aFavor = 0;
    static int enContra = 0;

    public static boolean registrar(Queue<String> votantes, String v) {
        votantes.add(v);
        System.out.println("Registrada: " + v);
        return true;
    }

    public static void aFavor(Queue<String> votantes) {

        if (votantes.isEmpty()) {
            System.out.println("No puedes votar, ya votaron todos");
            return;
        }

        votantes.poll();
        aFavor++;

        System.out.println("Votaste a favor");
    }

    public static void enContra(Queue<String> votantes) {

        if (votantes.isEmpty()) {
            System.out.println("No puedes votar, ya votaron todos");
            return;
        }

        votantes.poll();
        enContra++;

        System.out.println("Votaste en contra");
    }

    public static void mostrar(Queue<String> cola) {

        int salida = aFavor - enContra;

        System.out.println( "Entrada: a Favor: " + aFavor

                + " en Contra: " + enContra

                + " Salida esperada: " + salida);

        if (cola.isEmpty()) {
            System.out.println("No quedan votantes");
        }
    }
}