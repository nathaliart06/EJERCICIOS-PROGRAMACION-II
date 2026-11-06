package Semana8.ProyectoFinal.excepciones;

/**
 * Se lanza cuando el estudiante no ha aprobado todos los pre-requisitos de la materia.
 */
public class PreRequisitoNoAprobadoException extends Exception {

    public PreRequisitoNoAprobadoException(String mensaje) {
        super(mensaje);
    }
}
