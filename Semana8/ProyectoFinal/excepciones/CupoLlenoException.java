package Semana8.ProyectoFinal.excepciones;

/**
 * Se lanza cuando la materia no tiene cupos. Quien la atrapa manda al estudiante a la cola de espera.
 */
public class CupoLlenoException extends Exception {

    public CupoLlenoException(String mensaje) {
        super(mensaje);
    }
}
