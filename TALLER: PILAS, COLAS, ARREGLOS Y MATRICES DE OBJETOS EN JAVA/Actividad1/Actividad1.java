//Actividad 1. Arreglos de objetos: Gestión de estudiantes
//Desarrolle una aplicación que permita administrar la información académica de un grupo de hasta 10 estudiantes.
//Cada estudiante debe tener código, nombre, programa académico y promedio.
//Requerimientos:
//Registrar y listar estudiantes.
//Buscar un estudiante por código.
//Mostrar los estudiantes con promedio igual o superior a 4.0.
//Identificar al estudiante con el promedio más alto.


import java.util.*;

public class Actividad1 {
    public static void main(String[] args) {
        // Arreglo de objetos con capacidad para 10 estudiantes
        Estudiante[] estudiantes = new Estudiante[10];

        System.out.println("----------------- ARREGLO VACIO ----------------");
        Estudiante.listar(estudiantes);
        System.out.println("Mejor promedio: " + Estudiante.mejorPromedio(estudiantes));

        System.out.println("----------------- REGISTRO DE ESTUDIANTES ----------------");
        Estudiante.registrar(estudiantes, new Estudiante(101, "Michel", "Ingenieria de Sistemas", 4.5));
        Estudiante.registrar(estudiantes, new Estudiante(102, "Steven", "Ingenieria Civil", 3.8));
        Estudiante.registrar(estudiantes, new Estudiante(103, "Natalia", "Derecho", 4.2));
        Estudiante.registrar(estudiantes, new Estudiante(104, "Juan", "Medicina", 3.5));
        Estudiante.registrar(estudiantes, new Estudiante(105, "Maria", "Ingenieria de Sistemas", 4.8));
        Estudiante.listar(estudiantes);

        System.out.println("----------------- BUSCAR POR CODIGO ----------------");
        Estudiante encontrado = Estudiante.buscarPorCodigo(estudiantes, 103);
        System.out.println("Buscar 103: " + (encontrado != null ? encontrado : "No existe"));
        encontrado = Estudiante.buscarPorCodigo(estudiantes, 999);
        System.out.println("Buscar 999: " + (encontrado != null ? encontrado : "No existe"));

        System.out.println("----------------- PROMEDIO >= 4.0 ----------------");
        Estudiante.mostrarDestacados(estudiantes);

        System.out.println("----------------- MEJOR PROMEDIO ----------------");
        System.out.println(Estudiante.mejorPromedio(estudiantes));

        System.out.println("----------------- ARREGLO LLENO ----------------");
        Estudiante.registrar(estudiantes, new Estudiante(106, "Pedro", "Derecho", 3.9));
        Estudiante.registrar(estudiantes, new Estudiante(107, "Laura", "Medicina", 4.1));
        Estudiante.registrar(estudiantes, new Estudiante(108, "Daniel", "Ingenieria Civil", 3.2));
        Estudiante.registrar(estudiantes, new Estudiante(109, "Sofia", "Psicologia", 4.0));
        Estudiante.registrar(estudiantes, new Estudiante(110, "Andres", "Contaduria", 3.7));
        // El estudiante 11 ya no cabe
        Estudiante.registrar(estudiantes, new Estudiante(111, "Camila", "Derecho", 4.9));
        System.out.println("Cantidad de estudiantes: " + estudiantes.length);
    }
}