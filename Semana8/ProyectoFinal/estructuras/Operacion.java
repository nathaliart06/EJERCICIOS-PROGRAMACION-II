package Semana8.ProyectoFinal.estructuras;

/**
 * Operacion que se guarda en la pila de deshacer. Es abstracta: cada tipo de operacion
 * (inscripcion, horario, nota, eliminar, lote) sobrescribe deshacer() y rehacer() a su manera.
 */
public abstract class Operacion {
    private static int contador = 0; // genera el id automaticamente
    private int id;
    private String descripcion;

    // Constructor
    public Operacion(String descripcion) {
        contador++;
        this.id = contador;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    /** Metodo que devuelve todo como estaba antes de la operacion. Retorna el mensaje para el usuario */
    public abstract String deshacer();

    /** Metodo que vuelve a aplicar la operacion. Retorna el mensaje para el usuario */
    public abstract String rehacer();

    @Override
    public String toString() {
        return "#" + id + " " + descripcion;
    }
}
