//Actividad 4. Colas: Atención de solicitudes universitarias
//Desarrolle una cola con capacidad para 10 objetos de tipo Solicitud, que contengan número, nombre del estudiante y tipo de trámite.
//Requerimientos:
//Registrar solicitudes en la cola.
//Atender y retirar la primera solicitud.
//Consultar la siguiente solicitud por atender.
//Mostrar las solicitudes pendientes en orden de llegada.
//Validar los estados de cola vacía y llena.
//Demuestre el funcionamiento del principio FIFO (First In, First Out).

import java.util.*;

public class Actividad4 {
    public static void main(String[] args) {
        Queue<Solicitud> cola = new LinkedList<>();

        System.out.println("----------------- COLA VACIA ----------------");
        Solicitud.mostrar(cola);
        Solicitud.atender(cola);
        Solicitud.siguiente(cola);

        System.out.println("----------------- REGISTRAR ----------------");
        Solicitud.registrar(cola, new Solicitud(1, "Pedro", "Certificado de notas"));
        Solicitud.registrar(cola, new Solicitud(2, "Juan", "Cancelacion de materia"));
        Solicitud.registrar(cola, new Solicitud(3, "Maria", "Carnet estudiantil"));
        Solicitud.mostrar(cola);

        System.out.println("----------------- SIGUIENTE ----------------");
        System.out.println("Siguiente por atender: " + Solicitud.siguiente(cola));

        System.out.println("----------------- PRINCIPIO FIFO ----------------");
        // La primera en llegar (1) es la primera en ser atendida
        System.out.println("Atendida: " + Solicitud.atender(cola));
        System.out.println("Atendida: " + Solicitud.atender(cola));
        Solicitud.mostrar(cola);

        System.out.println("----------------- COLA LLENA ----------------");
        for (int i = 4; i <= 13; i++) {
            Solicitud.registrar(cola, new Solicitud(i, "Estudiante " + i, "Paz y salvo"));
        }
        Solicitud.mostrar(cola);
    }
}