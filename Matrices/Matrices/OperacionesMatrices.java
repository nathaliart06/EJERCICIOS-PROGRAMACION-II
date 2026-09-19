package Matrices;

public class OperacionesMatrices {

    // Método que permite llenar la matriz con números enteros
    public int[][] c(int f, int c, int min, int max) {
        int[][] m = new int[f][c]; // Declarando la matriz
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = (int) (Math.random() * (max - min - 1) + min);
            }
        }
        return m;
    }

    public String sumaFilas(int[][] m) {
        String cad = "";
        int sumaFilas;
        for (int i = 0; i < m.length; i++) {
            sumaFilas = 0;
            for (int j = 0; j < m[0].length; j++) {
                sumaFilas += m[i][j];
            }
            cad += "sumaFila " + i + " " + sumaFilas + "\n";
        }
        return cad;
    }

    public String sumaColumnas(int[][] m) {
        String cad = "";
        int sumaColumna;
        for (int i = 0; i < m.length; i++) {
            sumaColumna = 0;
            for (int j = 0; j < m[0].length; j++) {
                sumaColumna += m[j][i];
            }
            cad += "sumaColumna " + i + " " + sumaColumna + "\n";
        }
        return cad;
    }

    // Método que permite mostrar una matriz de enteros
    public String imprimirMatriz(int[][] m) {
        String cad = "";
        for (int i = 0; i < m.length; i++) {
            cad += "|";
            for (int j = 0; j < m[0].length; j++) {
                cad += m[i][j] + "|";
            }
            cad += "\n"; // Salto de línea --> \n
        }
        return cad;
    }

}
