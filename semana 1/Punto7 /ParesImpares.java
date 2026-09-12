public class ParesImpares {
    public static void main(String[] args) {
        // Arreglo original con los números que necesitas
        int[] a = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        
        // Contamos cuántos pares e impares hay
        int Pares = 0, Impares = 0;
        for (int num : a) {
            if (num % 2 == 0) {
                Pares++;
            } else {
                Impares++;
            }
        }

        // Se crean los arreglos para guardarlos
        int[] pares = new int[Pares];
        int[] impares = new int[Impares];
        int iPares = 0, iImpares = 0;

        // Separamos los números
        for (int num : a) {
            if (num % 2 == 0) {
                pares[iPares] = num;
                iPares++;
            } else {
                impares[iImpares] = num;
                iImpares++;
            }
        }
        System.out.println("PARES");
        for (int p : pares) {
            System.out.print(p + " ");
        }
        System.out.println();

        System.out.println("IMPARES");
        for (int i : impares) {
            System.out.print(i + " ");
        }
    }
}