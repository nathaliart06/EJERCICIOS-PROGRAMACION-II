package Semana8.ProyectoFinal.excepciones;

/**
 * Se lanza cuando la hora ya esta reservada en el aula o cuando dos materias del estudiante se cruzan.
 */
public class HorarioConflictivoException extends Exception {

    public HorarioConflictivoException(String mensaje) {
        super(mensaje);
    }
}
