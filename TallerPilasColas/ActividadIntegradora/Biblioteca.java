package TALLER: PILAS, COLAS, ARREGLOS Y MATRICES DE OBJETOS EN JAVA.ActividadIntegradora;

//Actividad integradora: Biblioteca universitaria
//Desarrolle una aplicación que integre las estructuras anteriores para administrar una biblioteca.
//El sistema deberá:
//Utilizar un arreglo de objetos para registrar libros.
//Organizar los libros por secciones mediante una matriz de objetos.
//Gestionar las solicitudes de préstamo con una cola.
//Registrar las últimas operaciones realizadas mediante una pila.
//La aplicación deberá permitir registrar y consultar libros, procesar solicitudes de préstamo y visualizar el historial de operaciones.


package Semana5.Taller.ActividadIntegradora;
import java.util.*;

public class Biblioteca {
    public static void main(String[] args) {
        // Las cuatro estructuras del sistema
        Libro[] catalogo = new Libro[10];             // arreglo de objetos
        Libro[][] estante = new Libro[3][4];          // matriz: 3 secciones x 4 posiciones
        Queue<Prestamo> solicitudes = new LinkedList<>(); // cola de prestamos
        Stack<Registro> historial = new Stack<>();    // pila de operaciones

        System.out.println("----------------- ESTRUCTURAS VACIAS ----------------");
        Libro.listar(catalogo);
        Prestamo.procesar(solicitudes, catalogo, historial);
        Registro.mostrar(historial);

        System.out.println("----------------- REGISTRAR LIBROS ----------------");
        Libro[] nuevos = {
                new Libro(1, "Estructuras de Datos en Java", "Joyanes", 0),
                new Libro(2, "Clean Code", "Robert Martin", 0),
                new Libro(3, "Cien anos de soledad", "Garcia Marquez", 1),
                new Libro(4, "Don Quijote", "Cervantes", 1),
                new Libro(5, "Breve historia del tiempo", "Hawking", 2),
                new Libro(6, "El origen de las especies", "Darwin", 2),
                new Libro(7, "Algoritmos", "Cormen", 0)
        };
        for (int i = 0; i < nuevos.length; i++) {
            if (Libro.registrar(catalogo, nuevos[i])) {
                Libro.ubicarEnSeccion(estante, nuevos[i]);
                Registro.apilar(historial, new Registro("Registro de " + nuevos[i].getTitulo()));
            }
        }
        // Codigo repetido
        Libro.registrar(catalogo, new Libro(3, "Libro repetido", "Anonimo", 1));
        Libro.listar(catalogo);

        System.out.println("----------------- ESTANTE POR SECCIONES ----------------");
        Libro.mostrarEstante(estante);

        System.out.println("----------------- CONSULTAR LIBRO ----------------");
        Libro l = Libro.buscarPorCodigo(catalogo, 5);
        System.out.println("Buscar 5: " + (l != null ? l : "No existe"));
        l = Libro.buscarPorCodigo(catalogo, 50);
        System.out.println("Buscar 50: " + (l != null ? l : "No existe"));

        System.out.println("----------------- SOLICITUDES DE PRESTAMO ----------------");
        Prestamo.solicitar(solicitudes, new Prestamo(1, "Michel", 1));
        Prestamo.solicitar(solicitudes, new Prestamo(2, "Steven", 3));
        Prestamo.solicitar(solicitudes, new Prestamo(3, "Natalia", 1));  // ya estara prestado
        Prestamo.solicitar(solicitudes, new Prestamo(4, "Juan", 99));    // no existe
        System.out.println("Pendientes en orden de llegada:");
        Prestamo.mostrar(solicitudes);

        System.out.println("----------------- PROCESAR PRESTAMOS (FIFO) ----------------");
        while (!solicitudes.isEmpty()) {
            Prestamo.procesar(solicitudes, catalogo, historial);
        }
        Prestamo.procesar(solicitudes, catalogo, historial); // cola vacia

        System.out.println("----------------- ESTANTE DESPUES DE PRESTAMOS ----------------");
        Libro.mostrarEstante(estante);

        System.out.println("----------------- HISTORIAL (LIFO) ----------------");
        System.out.println("Ultimas " + Registro.CAPACIDAD + " operaciones (mas reciente primero):");
        Registro.mostrar(historial);
        System.out.println("Ultima operacion retirada: " + Registro.desapilar(historial));

        System.out.println("----------------- CATALOGO Y SECCION LLENOS ----------------");
        Libro.registrar(catalogo, new Libro(8, "Python", "Lutz", 0)); // seccion Sistemas: 4/4
        Libro.ubicarEnSeccion(estante, Libro.buscarPorCodigo(catalogo, 8));
        Libro extra = new Libro(9, "Java Avanzado", "Deitel", 0);    // seccion Sistemas llena
        if (Libro.ubicarEnSeccion(estante, extra)) {               // si no cabe en el estante no se registra
            Libro.registrar(catalogo, extra);
        }
        Libro[] masLibros = {
                new Libro(10, "Rayuela", "Cortazar", 1),
                new Libro(11, "Cosmos", "Sagan", 2),
                new Libro(12, "Fisica", "Serway", 2) // el catalogo ya tiene 10: no cabe
        };
        for (int i = 0; i < masLibros.length; i++) {
            if (Libro.registrar(catalogo, masLibros[i])) {
                Libro.ubicarEnSeccion(estante, masLibros[i]);
            }
        }
        Libro.listar(catalogo);
    }
}