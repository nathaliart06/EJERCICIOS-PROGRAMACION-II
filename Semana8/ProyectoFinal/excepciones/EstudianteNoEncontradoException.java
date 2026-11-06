package Semana8.ProyectoFinal.excepciones;

/**
 * Se lanza cuando no existe un estudiante con ese ID en el HashMap.
 */
public class EstudianteNoEncontradoException extends Exception {

    public EstudianteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
