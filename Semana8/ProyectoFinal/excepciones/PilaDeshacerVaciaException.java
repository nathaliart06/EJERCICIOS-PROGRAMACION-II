package Semana8.ProyectoFinal.excepciones;

/**
 * Se lanza cuando no hay operaciones para deshacer o para rehacer.
 */
public class PilaDeshacerVaciaException extends Exception {

    public PilaDeshacerVaciaException(String mensaje) {
        super(mensaje);
    }
}
