package Talleres;

public class Punto23 {
    public static void main(String[] args) {
        OperacionesMatrices objM = new OperacionesMatrices();
        // llenar matriz con positivos y negativos
        int[][] matriz = objM.llenarMAtriz(4, 5, -50, 50);
        System.out.println("Matriz original:");
        System.out.println(objM.imprimirMatriz(matriz));
        // agregar ultima columna con el mayor valor absoluto de cada fila
        int[][] resultado = objM.mayorAbsolutoFilas(matriz);
        System.out.println("Matriz con el mayor valor absoluto en la ultima columna:");
        System.out.println(objM.imprimirMatriz(resultado));
    }
}