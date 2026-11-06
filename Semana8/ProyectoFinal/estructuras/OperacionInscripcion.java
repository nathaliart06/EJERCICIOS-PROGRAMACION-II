package Semana8.ProyectoFinal.estructuras;

import Semana8.ProyectoFinal.modelo.Estudiante;
import Semana8.ProyectoFinal.modelo.Materia;

import java.util.*;

/**
 * Operacion sobre inscritos y colas de espera (inscribir, cola de espera, cancelar
 * y eliminar estudiante).
 * Guarda una "foto" de cada materia ANTES del cambio y otra DESPUES:
 * deshacer pone la foto de antes y rehacer la de despues.
 * Asi, si al cancelar el cupo paso al primero de la cola, al deshacer ese estudiante
 * vuelve a su misma posicion en la cola.
 */
public class OperacionInscripcion extends Operacion {
    private LinkedList<Materia> materias;
    private LinkedList<LinkedList<String>> inscritosAntes = new LinkedList<>();
    private LinkedList<Queue<String>> colasAntes = new LinkedList<>();
    private LinkedList<LinkedList<String>> inscritosDespues = new LinkedList<>();
    private LinkedList<Queue<String>> colasDespues = new LinkedList<>();
    private String mensajeDeshacer = "";
    private String mensajeRehacer = "";

    // Solo para "eliminar estudiante": el objeto completo y su posicion en el arreglo
    private Estudiante[] estudiantes;
    private Map<String, Estudiante> indice;
    private Estudiante eliminado;
    private int posicion;

    // Constructor para una sola materia: toma la foto de antes
    public OperacionInscripcion(String descripcion, Materia materia) {
        this(descripcion, unaMateria(materia));
    }

    // Constructor para varias materias: toma la foto de antes de cada una
    public OperacionInscripcion(String descripcion, LinkedList<Materia> materias) {
        super(descripcion);
        this.materias = new LinkedList<>(materias);
        for (Materia m : materias) {
            inscritosAntes.add(m.fotoInscritos());
            colasAntes.add(m.fotoCola());
        }
    }

    // Constructor para eliminar estudiante: ademas guarda al estudiante y su posicion
    public OperacionInscripcion(Estudiante[] estudiantes, Map<String, Estudiante> indice, Estudiante eliminado,
                                int posicion, LinkedList<Materia> materias) {
        this("Eliminar estudiante " + eliminado.getId() + " - " + eliminado.getNombre(), materias);
        this.estudiantes = estudiantes;
        this.indice = indice;
        this.eliminado = eliminado;
        this.posicion = posicion;
        setMensajes(eliminado.getNombre() + " (" + eliminado.getId() + ") fue restaurado(a) con sus notas e inscripciones",
                eliminado.getNombre() + " fue eliminado(a) de nuevo");
    }

    private static LinkedList<Materia> unaMateria(Materia m) {
        LinkedList<Materia> lista = new LinkedList<>();
        lista.add(m);
        return lista;
    }

    /** Metodo que toma la foto de despues (se llama cuando el cambio ya se hizo) */
    public void guardarDespues() {
        inscritosDespues.clear();
        colasDespues.clear();
        for (Materia m : materias) {
            inscritosDespues.add(m.fotoInscritos());
            colasDespues.add(m.fotoCola());
        }
    }

    public void setMensajes(String mensajeDeshacer, String mensajeRehacer) {
        this.mensajeDeshacer = mensajeDeshacer;
        this.mensajeRehacer = mensajeRehacer;
    }

    // Metodo que pone las fotos en cada materia
    private void restaurar(LinkedList<LinkedList<String>> inscritos, LinkedList<Queue<String>> colas) {
        for (int i = 0; i < materias.size(); i++) {
            materias.get(i).restaurar(inscritos.get(i), colas.get(i));
        }
    }

    @Override
    public String deshacer() {
        if (eliminado != null) {
            Estudiante.reinsertar(estudiantes, indice, eliminado, posicion);
        }
        restaurar(inscritosAntes, colasAntes);
        return "Operacion deshecha: " + mensajeDeshacer;
    }

    @Override
    public String rehacer() {
        if (eliminado != null) {
            Estudiante.quitar(estudiantes, indice, eliminado);
        }
        restaurar(inscritosDespues, colasDespues);
        return "Operacion rehecha: " + mensajeRehacer;
    }
}
