package Semana8.ProyectoFinal.modelo;

/**
 * Materia que el estudiante ya curso. Es el elemento del historial (lista enlazada).
 */
public class MateriaCursada {
    private String codigoMateria;
    private int semestre;
    private double nota;

    // Constructor
    public MateriaCursada(String codigoMateria, int semestre, double nota) {
        this.codigoMateria = codigoMateria;
        this.semestre = semestre;
        this.nota = nota;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public int getSemestre() {
        return semestre;
    }

    public double getNota() {
        return nota;
    }

    /** Metodo que dice si la materia esta aprobada (nota >= 3.0) */
    public boolean estaAprobada() {
        return nota >= Estudiante.NOTA_APROBATORIA;
    }

    @Override
    public String toString() {
        return "MateriaCursada{" + "codigo=" + codigoMateria + ", semestre=" + semestre + ", nota=" + nota
                + ", " + (estaAprobada() ? "Aprobada" : "Reprobada") + '}';
    }
}
