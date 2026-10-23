//3. Cuadrados y cubos (Pila)
//Enunciado. Dado un par de enteros [a, b], evaluar si la raiz cuadrada de a es igual a la raiz
//cubica de b (con tolerancia numerica razonable). Modela el flujo usando una pila donde primero
//apilas ambos y luego desapilas para calcular.
//Ejemplos de verificacion.
//Entrada: a = 4, b = 8 Salida esperada: true
//Entrada: a = 16, b = 48 Salida esperada: false
//Entrada: a = 9, b = 27 Salida esperada: true

public class CuadradosCubo {
    public static void main(String[] args) {
        System.out.println("----------------- CASO 1 ----------------");
        Raices.mostrar(4, 8);
        System.out.println("----------------- CASO 2 ----------------");
        Raices.mostrar(16, 48);
        System.out.println("----------------- CASO 3 ----------------");
        Raices.mostrar(9, 27);
    }
}