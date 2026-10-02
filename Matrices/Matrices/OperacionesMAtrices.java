import java.util.Scanner;

public class OperacionesMAtrices {
    
    public int[][] llenarMAtriz(int f, int c, int min, int max) {
        int[][] m = new int[f][c];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = min + (int) (Math.random() * (max - min + 1));
            }
        }
        return m;
    }

    public int[][] leerMatriz(int f, int c, Scanner sc) {
        int[][] m = new int[f][c];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print("Posicion [" + i + "][" + j + "]: ");
                m[i][j] = sc.nextInt();
            }
        }
        return m;
    }

    public String imprimirMatriz(int[][] m) {
        String cad = "";
        for (int i = 0; i < m.length; i++) {
            cad += "|";
            for (int j = 0; j < m[0].length; j++) {
                cad += String.format("%4d|", m[i][j]);
            }
            cad += "\n";
        }
        return cad;
    }

    public String imprimirVector(int[] v) {
        String cad = "|";
        for (int i = 0; i < v.length; i++) {
            cad += v[i] + "|";
        }
        return cad;
    }

    // ===================== METODOS PARA (VECTORES) =====================

    public int[] totalesPorFila(int[][] m) {
        int[] v = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            int suma = 0;
            for (int j = 0; j < m[0].length; j++) {
                suma += m[i][j];
            }
            v[i] = suma;
        }
        return v;
    }

    public int[] totalesPorColumna(int[][] m) {
        int[] v = new int[m[0].length];
        for (int j = 0; j < m[0].length; j++) {
            int suma = 0;
            for (int i = 0; i < m.length; i++) {
                suma += m[i][j];
            }
            v[j] = suma;
        }
        return v;
    }

    public double promedio(int[] v) {
        int suma = 0;
        for (int i = 0; i < v.length; i++) {
            suma += v[i];
        }
        return (double) suma / v.length;
    }

    public int contarMayores(int[] v, double valor) {
        int cont = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i] > valor) {
                cont++;
            }
        }
        return cont;
    }

    public int contarMenores(int[] v, double valor) {
        int cont = 0;
        for (int i = 0; i < v.length; i++) {
            if (v[i] < valor) {
                cont++;
            }
        }
        return cont;
    }

    public int posicionMayor(int[] v) {
        int pos = 0;
        for (int i = 1; i < v.length; i++) {
            if (v[i] > v[pos]) {
                pos = i;
            }
        }
        return pos;
    }

    public int filaMayorEnColumna(int[][] m, int c) {
        int fila = 0;
        for (int i = 1; i < m.length; i++) {
            if (m[i][c] > m[fila][c]) {
                fila = i;
            }
        }
        return fila;
    }

    // ===================== PUNTO 17 =====================

    public String SumaFilasColumnas(int[][] m) {
        String cad = "";
        int sumaFilas, sumaColumnas;
        for (int i = 0; i < m.length; i++) {
            sumaFilas = 0;
            sumaColumnas = 0;
            for (int j = 0; j < m[0].length; j++) {
                sumaFilas += m[i][j];
                sumaColumnas += m[j][i];
            }
            cad += "suma Fila" + " " + i + ": ---->" + " " + sumaFilas + "\n";
            cad += "suma Columna" + " " + i + ": ---->" + " " + sumaColumnas + "\n";
        }
        return cad;
    }

    // ===================== PUNTO 18 =====================

    public int[][] tablaMultiplicar(int n) {
        int[][] m = new int[n][n];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] = (i + 1) * (j + 1);
            }
        }
        return m;
    }

    public String imprimirTablaMultiplicar(int[][] m) {
        String cad = "  X |";
        for (int j = 0; j < m[0].length; j++) {
            cad += String.format("%4d|", j + 1);
        }
        cad += "\n";
        for (int i = 0; i < m.length; i++) {
            cad += String.format("%3d |", i + 1);
            for (int j = 0; j < m[0].length; j++) {
                cad += String.format("%4d|", m[i][j]);
            }
            cad += "\n";
        }
        return cad;
    }

    // ===================== PUNTO 19 =====================

    public int[][] matrizDiagonal(int n, int m) {
        int[][] mat = new int[n][m];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (i == j) {
                    mat[i][j] = 1;
                } else {
                    mat[i][j] = 0;
                }
            }
        }
        return mat;
    }

    // ===================== PUNTO 20 =====================

    public int[][] ceroDiagonalArriba(int n, int min, int max) {
        int[][] mat = new int[n][n];
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (i <= j) {
                    mat[i][j] = 0;
                } else {
                    mat[i][j] = min + (int) (Math.random() * (max - min + 1));
                }
            }
        }
        return mat;
    }

    // ===================== PUNTO 21 =====================

    public int[] sumaParesFilas(int[][] m) {
        int[] b = new int[m.length];
        for (int i = 0; i < m.length; i++) {
            int suma = 0;
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] % 2 == 0) {
                    suma += m[i][j];
                }
            }
            b[i] = suma;
        }
        return b;
    }

    // ===================== PUNTO 22 =====================

    public String mayorMenor(int[][] m) {
        int mayor = m[0][0], menor = m[0][0];
        int filaMayor = 0, colMayor = 0, filaMenor = 0, colMenor = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (m[i][j] > mayor) {
                    mayor = m[i][j];
                    filaMayor = i;
                    colMayor = j;
                }
                if (m[i][j] < menor) {
                    menor = m[i][j];
                    filaMenor = i;
                    colMenor = j;
                }
            }
        }
        String cad = "";
        cad += "Mayor: " + mayor + " en la posicion [" + filaMayor + "][" + colMayor + "]\n";
        cad += "Menor: " + menor + " en la posicion [" + filaMenor + "][" + colMenor + "]\n";
        return cad;
    }

    // ===================== PUNTO 23 =====================

    public int[][] mayorAbsolutoFilas(int[][] m) {
        int ultima = m[0].length;
        int[][] r = new int[m.length][ultima + 1];
        for (int i = 0; i < m.length; i++) {
            int mayor = m[i][0];
            for (int j = 0; j < m[0].length; j++) {
                r[i][j] = m[i][j];
                if (Math.abs(m[i][j]) > Math.abs(mayor)) {
                    mayor = m[i][j];
                }
            }
            r[i][ultima] = mayor;
        }
        return r;
    }

    // ===================== PUNTO 26 =====================

    public int[][] recaudoPorModelo(int[][] ventas, int[] precios) {
        int[][] r = new int[ventas.length][ventas[0].length];
        for (int i = 0; i < ventas.length; i++) {
            for (int j = 0; j < ventas[0].length; j++) {
                r[i][j] = ventas[i][j] * precios[i];
            }
        }
        return r;
    }
}