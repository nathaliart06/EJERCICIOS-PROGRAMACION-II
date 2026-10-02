package Talleres;

public class Punto21 {
    public static void main(String[] args) {
        OperacionesMatrices objM = new OperacionesMatrices();
        // llenar matriz
        int[][] matriz = objM.llenarMAtriz(4, 4, 1, 9);
        System.out.println(objM.imprimirMatriz(matriz));
        // construir vector B
        int[] b = objM.sumaParesFilas(matriz);
        System.out.println("Vector B:");
        System.out.println(objM.imprimirVector(b));
    }
}