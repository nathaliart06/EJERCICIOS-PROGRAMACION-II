package Semana8.ProyectoFinal.modelo;

/**
 * Facultad de la universidad. Son fijas: se guardan en un arreglo Facultad[5].
 */
public class Facultad {
    private String codigo;
    private String nombre;

    public static final int CANTIDAD = 5;

    // Constructor
    public Facultad(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Facultad{" + "codigo=" + codigo + ", nombre=" + nombre + '}';
    }

    /** Metodo que crea el arreglo fijo de 5 facultades */
    public static Facultad[] crearFacultades() {
        Facultad[] facultades = new Facultad[CANTIDAD];
        facultades[0] = new Facultad("FING", "Ingenieria");
        facultades[1] = new Facultad("FCB", "Ciencias Basicas");
        facultades[2] = new Facultad("FCE", "Ciencias Economicas");
        facultades[3] = new Facultad("FCS", "Ciencias de la Salud");
        facultades[4] = new Facultad("FHU", "Humanidades");
        return facultades;
    }

    /** Metodo para listar las facultades con su posicion en el arreglo */
    public static void listar(Facultad[] facultades) {
        for (int i = 0; i < facultades.length; i++) {
            System.out.println("  " + i + ". " + facultades[i].getNombre());
        }
    }
}
