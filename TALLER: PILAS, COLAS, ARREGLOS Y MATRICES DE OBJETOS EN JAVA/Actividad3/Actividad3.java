//Actividad 3. Pilas: Historial de operaciones
//Implemente una pila con capacidad para 10 objetos de tipo Operacion, que contengan identificador, descripción y tipo de operación.
//Requerimientos:
//Apilar una nueva operación.
//Desapilar la última operación registrada.
//Consultar el elemento que se encuentra en el tope.
//Mostrar los elementos de la pila.
//Controlar los casos en que la pila esté vacía o llena.
//Demuestre el funcionamiento del principio LIFO (Last In, First Out).

import java.util.*;

public class Actividad3 {
    public static void main(String[] args) {
        Stack<Operacion> historial = new Stack<>();

        System.out.println("----------------- PILA VACIA ----------------");
        Operacion.mostrar(historial);
        Operacion.desapilar(historial);
        Operacion.tope(historial);

        System.out.println("----------------- APILAR ----------------");
        Operacion.apilar(historial, new Operacion(1, "Crear archivo", "Creacion"));
        Operacion.apilar(historial, new Operacion(2, "Escribir texto", "Edicion"));
        Operacion.apilar(historial, new Operacion(3, "Borrar parrafo", "Eliminacion"));
        Operacion.mostrar(historial);

        System.out.println("----------------- TOPE ----------------");
        System.out.println("Tope: " + Operacion.tope(historial));

        System.out.println("----------------- PRINCIPIO LIFO ----------------");
        // La ultima en entrar (3) es la primera en salir
        System.out.println("Desapilada: " + Operacion.desapilar(historial));
        System.out.println("Desapilada: " + Operacion.desapilar(historial));
        Operacion.mostrar(historial);

        System.out.println("----------------- PILA LLENA ----------------");
        for (int i = 4; i <= 13; i++) {
            Operacion.apilar(historial, new Operacion(i, "Operacion " + i, "Edicion"));
        }
        Operacion.mostrar(historial);
    }
}