public class arreglos {
    public static void main(String[] args) {
        // Creación de los arreglos
        int[] a = { 8, 5, 3, 10, 2, 8, 1 };
        int[] b = {};
        int tam = a.length; // tamaño del arreglo
 
        if (a.length % 2 == 0) { //Cuando el arreglo es par
            b = new int[tam / 2]; // Creación del arreglo d con la mitad del tamaño del arreglo original
            for (int i = 0; i < tam / 2; i++) {
                b[i] = a[i] + a[tam - 1 - i];
            }
        } else { //Cuando el arreglo es impar
            b = new int[tam / 2 + 1]; // Creación del arreglo d con la mitad del tamaño del arreglo original + 1
            for (int i = 0; i < a.length / 2 + 1; i++) {
                if(i == (tam / 2)){
                    b[i] = a[i];
                }else{
                    b[i] = a[i] + a[tam - 1 - i];
                }
            }
        }
 
        // imprimir el arreglo
        System.out.println(Arrays.toString(b));
 
    }
}