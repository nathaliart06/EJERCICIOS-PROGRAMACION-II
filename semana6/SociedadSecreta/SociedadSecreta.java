package semana6.SociedadSecreta;

//6. Sociedad secreta (Cola + ordenamiento)
//Enunciado. Dada una lista de nombres, desencolar cada nombre, tomar su primera letra en mayusculas,
//ordenar alfabeticamente dichas letras y concatenarlas para formar el nombre de la sociedad.
//Ejemplos de verificacion.
//Entrada: ["Adam", "Sarah", "Malcolm"] Salida esperada: AMS
//Entrada: ["bruce", "clark", "diana"] Salida esperada: BCD
//Entrada: ["Zoe", "anna", "leo"] Salida esperada: ALZ

import java.util.*;

public class SociedadSecreta {
    public static void main(String[] args) {
        System.out.println("----------------- CASO 1 ----------------");
        Sociedad.mostrar(new ArrayList<>(Arrays.asList("Adam", "Sarah", "Malcolm")));
        System.out.println("----------------- CASO 2 ----------------");
        Sociedad.mostrar(new ArrayList<>(Arrays.asList("bruce", "clark", "diana")));
        System.out.println("----------------- CASO 3 ----------------");
        Sociedad.mostrar(new ArrayList<>(Arrays.asList("Zoe", "anna", "leo")));
    }
}