package Punto9;


public class arregloUnidimensional {
    public static void main(String[] args) {
        int[] arreglo = {4, 7, 2, 3, 7, 71, 15, 22, 7, 35, 48, 7, 9, 7, 1};
        int buscar = 7;
        int veces = 0;

        for (int i = 0; i < arreglo.length; i++) {
            if (arreglo[i] == buscar) {
                veces = veces + 1;
            }
        }

        System.out.println("El numero " + buscar + " se repite " + veces + " veces.");
    }
}