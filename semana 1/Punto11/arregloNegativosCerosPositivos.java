package Punto11;

public class arregloNegativosCerosPositivos {

    public static void main(String[] args) {
        int[] a = {-5, 12, 0, 8, -2, 1, 0, 7, -4, 0, 10, -22};

        // Se crean los arreglos del mismo tamaño que a
        int[] neg = new int[a.length];
        int[] cer = new int[a.length];
        int[] pos = new int[a.length];

        int iNeg = 0, iCer = 0, iPos = 0;

        // Se clasifican los elementos
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                neg[iNeg] = a[i];
                iNeg = iNeg + 1;
            } else if (a[i] == 0) {
                cer[iCer] = a[i];
                iCer = iCer + 1;
            } else {
                pos[iPos] = a[i];
                iPos = iPos + 1;
            }
        }

        // Aqui el sistema muestra el arreglo de los negativos
        System.out.print("Negativos: ");
        for (int i = 0; i < iNeg; i++) {
            System.out.print(neg[i] + " ");
        }
        System.out.println();

        // Aqui el sistema muestra el arreglo de los ceros
        System.out.print("Ceros: ");
        for (int i = 0; i < iCer; i++) {
            System.out.print(cer[i] + " ");
        }
        System.out.println();

        // Aqui el sistema muestra el arreglo de los positivos
        System.out.print("Positivos: ");
        for (int i = 0; i < iPos; i++) {
            System.out.print(pos[i] + " ");
        }
        System.out.println();
    }

}