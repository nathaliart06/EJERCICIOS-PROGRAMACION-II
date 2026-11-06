package Semana8.ProyectoFinal.estructuras;

import Semana8.ProyectoFinal.modelo.Estudiante;

/**
 * Registro de una nota. Guarda la casilla de la matriz Double[10][20] y lo que habia antes en ella.
 */
public class OperacionNota extends Operacion {
    private Estudiante estudiante;
    private int semestre;
    private int columna;
    private String codigoAntes;
    private Double notaAntes;
    private String codigoNuevo;
    private Double notaNueva;

    // Constructor
    public OperacionNota(Estudiante estudiante, int semestre, int columna, String codigoAntes, Double notaAntes,
                         String codigoNuevo, Double notaNueva) {
        super("Nota " + codigoNuevo + " = " + notaNueva + " (semestre " + semestre + ") a " + estudiante.getNombre());
        this.estudiante = estudiante;
        this.semestre = semestre;
        this.columna = columna;
        this.codigoAntes = codigoAntes;
        this.notaAntes = notaAntes;
        this.codigoNuevo = codigoNuevo;
        this.notaNueva = notaNueva;
    }

    @Override
    public String deshacer() {
        estudiante.ponerNota(semestre, columna, codigoAntes, notaAntes);
        return "Operacion deshecha: " + codigoNuevo + " de " + estudiante.getNombre()
                + (notaAntes == null ? " queda sin nota" : " vuelve a " + notaAntes);
    }

    @Override
    public String rehacer() {
        estudiante.ponerNota(semestre, columna, codigoNuevo, notaNueva);
        return "Operacion rehecha: " + codigoNuevo + " de " + estudiante.getNombre() + " = " + notaNueva;
    }
}
