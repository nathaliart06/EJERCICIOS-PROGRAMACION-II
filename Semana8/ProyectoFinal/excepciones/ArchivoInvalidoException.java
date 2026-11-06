package Semana8.ProyectoFinal.excepciones;

/**
 * Se lanza cuando el archivo CSV no existe, no es .csv, no se puede leer o esta vacio.
 */
public class ArchivoInvalidoException extends Exception {

    public ArchivoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
