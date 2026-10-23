package semana6.CuadradosCubo;

import java.util.Stack;

public class Raices {

    // Tolerancia numerica para comparar numeros decimales
    static final double TOLERANCIA = 1e-9;

    // Metodo que apila los dos numeros del par [a, b]
    public static Stack<Integer> apilar(int a, int b) {
        Stack<Integer> pila = new Stack<>();
        pila.push(a);
        pila.push(b);
        System.out.println("Pila: " + pila + " (tope = " + pila.peek() + ")");
        return pila;
    }

    // Metodo que desapila y evalua si la raiz cuadrada de a es igual a la raiz cubica de b
    // Como es LIFO, primero sale b y despues sale a
    public static boolean evaluar(Stack<Integer> pila) {
        int b = pila.pop();
        int a = pila.pop();
        double raizCuadrada = Math.sqrt(a);
        double raizCubica = Math.cbrt(b);
        System.out.println("raiz cuadrada de " + a + " = " + raizCuadrada
                + " | raiz cubica de " + b + " = " + raizCubica);
        return Math.abs(raizCuadrada - raizCubica) < TOLERANCIA;
    }

    // Metodo que muestra el resultado de un caso
    public static void mostrar(int a, int b) {
        System.out.println("Entrada: a = " + a + ", b = " + b);
        Stack<Integer> pila = apilar(a, b);
        System.out.println("Salida: " + evaluar(pila));
    }
}