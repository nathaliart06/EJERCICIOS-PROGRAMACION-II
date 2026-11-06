package Semana8.ProyectoFinal.excepciones;

/**
 * Se lanza cuando se consulta la cola de espera de una materia y no hay nadie esperando.
 */
public class ColaDeEsperaVaciaException extends Exception {

    public ColaDeEsperaVaciaException(String mensaje) {
        super(mensaje);
    }
}
