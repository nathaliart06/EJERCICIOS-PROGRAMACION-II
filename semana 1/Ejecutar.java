public class Ejecutar {
    public static void main(String[] args) {
        
        //Declaración del arreglo-array-vector (Forma 1)
        int[] a = {5, 16, 8, 9, 10, 25, 19, 3, 6, 22, 2};

        for (int i = 0; i < a.length; i++) {
            System.out.println("a[" + i + "]=" + a[i]);
        }

        //Sumar todos los elementos del arreglo a
        int suma = 0;
        for (int i = 0; i < a.length; i++) {
            suma += a[i];
        }

        System.out.println("La suma de los elementos del arreglo a: " + suma);

        //Declaración del arreglo-array-vector (Forma 2)
        int[] b = new int[11];
        int min = 1, max = 30;
        for (int i = 0; i < b.length; i++) {
            //Llenar el arreglo b con números aleatorios entre 1(min) y 50(max)
            b[i] = (int)(Math.random() * (max - min - 1 ) + min);
        } 

        //Mostrar el arreglo b
        for (int i = 0; i < b.length; i++) {
            System.out.println("b[" + i + "]=" + b[i] + " potencia 2 = " + Math.pow(b[i],2));
        }

        //Punto 3 del taller
        /*
        Dado un arreglo lineal de números, sumar separadamente los números pares 
        y los números impares. 
        int[] a = {5, 16, 8, 9, 10, 25, 19, 3, 6, 22, 2};
        */
        int sumaPar = 0 , sumaImpar = 0;
        for (int i = 0; i < a.length; i++) {
            if(a[i] % 2 == 0){
                sumaPar += a[i];
            }else{
                sumaImpar += a[i];
            }
        }

        System.out.println("Suma pares: " + sumaPar + " - Suma impares: " + sumaImpar);


    }
}