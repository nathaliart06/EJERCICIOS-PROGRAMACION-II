import java.util.Arrays;

public class OperacionesConArreglos {
   
    //Mostrar un arreglo
    public void mostrarArreglo(int[] a){
        System.out.println(Arrays.toString(a));
    }
 
    //Crear y llenar un arreglo con numeros aleatorios entre un mínimo y un máximo
    public int[] llenarArrglo(int tam, int min, int max){
        int[] a = new int[tam];
        for (int i = 0; i < a.length; i++) {
            //Llenar el arreglo b con números aleatorios entre 1(min) y 50(max)
            a[i] = (int)(Math.random() * (max - min - 1 ) + min);
        }
        return a;
    }
 
    //Sumar los extremos de un arreglo
    public int[] sumaExtremos(int[] a){
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
        return b;
    }
}