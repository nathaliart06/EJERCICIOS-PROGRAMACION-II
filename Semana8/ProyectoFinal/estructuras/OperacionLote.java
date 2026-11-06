package Semana8.ProyectoFinal.estructuras;

import java.util.*;

/**
 * Agrupa todos los cambios de un procesamiento por lotes para deshacerlos con un solo paso.
 * Deshacer los recorre al reves (con una pila); rehacer, en el orden original.
 */
public class OperacionLote extends Operacion {
    private LinkedList<Operacion> operaciones;

    // Constructor
    public OperacionLote(String descripcion, LinkedList<Operacion> operaciones) {
        super(descripcion);
        this.operaciones = new LinkedList<>(operaciones);
    }

    @Override
    public String deshacer() {
        Stack<Operacion> alReves = new Stack<>();
        for (Operacion op : operaciones) {
            alReves.push(op);
        }
        while (!alReves.empty()) {
            alReves.pop().deshacer();
        }
        return "Operacion deshecha: " + getDescripcion() + " (" + operaciones.size() + " cambios revertidos)";
    }

    @Override
    public String rehacer() {
        for (Operacion op : operaciones) {
            op.rehacer();
        }
        return "Operacion rehecha: " + getDescripcion() + " (" + operaciones.size() + " cambios aplicados)";
    }
}
