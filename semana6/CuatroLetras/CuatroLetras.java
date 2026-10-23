package semana6.CuatroLetras;
//7. Devolver cadenas de cuatro letras (Cola de filtrado)
//Enunciado. De una lista de palabras, retornar solo aquellas cuya longitud sea exactamente 4.
//Modela el proceso encolando todas y desencolando para filtrar.
//Ejemplos de verificacion.
//Entrada: ["Tomate", "Papa", "Par"] Salida esperada: ["Papa"]
//Entrada: ["Luz", "Casa", "Sol", "Lago"] Salida esperada: ["Casa", "Lago"]
//Entrada: ["Azul", "Rojo", "Verde"] Salida esperada: ["Azul", "Rojo"]


import java.util.*;

public class CuatroLetras {
    public static void main(String[] args) {
        System.out.println("----------------- CASO 1 ----------------");
        Filtro.mostrar(new ArrayList<>(Arrays.asList("Tomate", "Papa", "Par")));
        System.out.println("----------------- CASO 2 ----------------");
        Filtro.mostrar(new ArrayList<>(Arrays.asList("Luz", "Casa", "Sol", "Lago")));
        System.out.println("----------------- CASO 3 ----------------");
        Filtro.mostrar(new ArrayList<>(Arrays.asList("Azul", "Rojo", "Verde")));
    }
}