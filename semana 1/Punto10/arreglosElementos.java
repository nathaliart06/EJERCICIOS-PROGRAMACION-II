package Punto10;


public class arreglosElementos {
    public static void main(String[] args) {
        int[] A = {8, 5, 3, 10, 2, 8, 1};
        int N = A.length;

        // Calcular el tamaño del arreglo B 
        int tamanoB = (N + 1) / 2;
        int[] B = new int[tamanoB];

        // Llenar el arreglo B sumando los opuestos
        for (int i = 0; i < tamanoB; i++) {
            if (i == N - 1 - i) {
                // Si es el elemento del centro queda igual
                B[i] = A[i];
            } else {
                // Sumar el elemento actual con su opuesto del extremo
                B[i] = A[i] + A[N - 1 - i];
            }
        }

        // Mostrar el arreglo resultante B
        System.out.print("Arreglo B: ");
        for (int i = 0; i < tamanoB; i++) {
            System.out.print(B[i] + " ");
        }
    }
}   