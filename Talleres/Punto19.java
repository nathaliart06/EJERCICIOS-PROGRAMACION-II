package Talleres;

public class Punto19 {
    public static void main(String[] args) {
        OperacionesMatrices objM = new OperacionesMatrices();
        // generar matriz
        int[][] matriz = objM.matrizDiagonal(4, 4);
        System.out.println(objM.imprimirMatriz(matriz));
    }
}