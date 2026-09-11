Public class MayorMenorAleatorio {
    
    public static void main(String[] args) {
        // Arreglo con 30 números
        int[] numeros = {
            15, 8, 90, 3, 45, 90, 12, 3, 67, 23,
            89, 3, 54, 90, 11, 78, 34, 3, 90, 21,
            43, 65, 87, 12, 90, 3, 56, 76, 88, 3
        };

        // Buscar el mayor y el menor
        int mayor = numeros[0];
        int menor = numeros[0];

        for (int i = 0; i < 30; i++) {
            if (numeros[i] > mayor) {
                mayor = numeros[i];
            }
            if (numeros[i] < menor) {
                menor = numeros[i];
            }
        }

        // Contar las repeticiones
        int cantMayor = 0;
        int cantMenor = 0;

        for (int i = 0; i < 30; i++) {
            if (numeros[i] == mayor) {
                cantMayor = cantMayor + 1;
            }
            if (numeros[i] == menor) {
                cantMenor = cantMenor + 1;
            }
        }
        System.out.println("Número mayor: " + mayor);
        System.out.println("Repeticiones del mayor: " + cantMayor);

        System.out.println("Número menor: " + menor);
        System.out.println("Repeticiones del menor: " + cantMenor);
    }
}