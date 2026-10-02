package Talleres;

public class Punto20 {
    public static void main(String[] args) {
        OperacionesMatrices objM = new OperacionesMatrices();
        // generar matriz
        int[][] matriz = objM.ceroDiagonalArriba(10, 1, 9);
        System.out.println(objM.imprimirMatriz(matriz));
    }
}