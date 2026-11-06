package Semana8.ProyectoFinal.modelo;

import java.util.*;

/**
 * Reporte ya generado. Es lo que se guarda en las pilas del navegador de reportes.
 */
public class Reporte {
    private String titulo;
    private String contenido;

    // Constructor
    public Reporte(String titulo, String contenido) {
        this.titulo = titulo;
        this.contenido = contenido;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getContenido() {
        return contenido;
    }

    @Override
    public String toString() {
        return "Reporte{" + "titulo=" + titulo + '}';
    }

    /** Metodo que genera el reporte completo: notas por semestre, promedios y resumen */
    public static Reporte academico(Estudiante e) {
        String texto = "--- REPORTE ACADEMICO ---\n";
        texto += "Estudiante: " + e.getNombre() + " (ID: " + e.getId() + ")\n";
        for (int s = 1; s <= Estudiante.SEMESTRES; s++) {
            if (e.cantidadNotas(s) == 0) {
                continue;
            }
            texto += "\nSemestre " + s + ":\n";
            for (int j = 0; j < Estudiante.MATERIAS_POR_SEMESTRE; j++) {
                if (e.getNota(s, j) != null) {
                    texto += String.format(Locale.US, "  %-8s %.1f%n", e.getCodigoMateria(s, j) + ":", e.getNota(s, j));
                }
            }
            texto += String.format(Locale.US, "  Promedio: %.2f%n", e.promedioSemestre(s));
        }
        if (!e.tieneNotas()) {
            texto += "\n(Aun no tiene notas registradas)\n";
        }
        texto += "\n=== RESUMEN ===\n";
        texto += String.format(Locale.US, "Promedio acumulado: %.2f%n", e.promedioAcumulado());
        texto += "Materias aprobadas: " + e.contarAprobadas() + "\n";
        texto += "Materias reprobadas: " + e.contarReprobadas() + "\n";
        return new Reporte("Reporte academico - " + e.getNombre(), texto);
    }

    /** Metodo que genera el reporte de materias reprobadas (nota menor a 3.0) */
    public static Reporte reprobadas(Estudiante e) {
        String texto = "--- MATERIAS REPROBADAS (nota < 3.0) ---\n";
        texto += "Estudiante: " + e.getNombre() + " (ID: " + e.getId() + ")\n\n";
        int contador = 0;
        for (MateriaCursada m : e.getHistorialMaterias()) {
            if (!m.estaAprobada()) {
                texto += String.format(Locale.US, "  Semestre %d - %-8s %.1f%n", m.getSemestre(), m.getCodigoMateria(), m.getNota());
                contador++;
            }
        }
        texto += contador == 0 ? "  No tiene materias reprobadas\n" : "\nTotal reprobadas: " + contador + "\n";
        return new Reporte("Reprobadas - " + e.getNombre(), texto);
    }

    /** Metodo que genera el promedio de un semestre especifico */
    public static Reporte promedioSemestre(Estudiante e, int semestre) {
        String texto = "--- PROMEDIO DEL SEMESTRE " + semestre + " ---\n";
        texto += "Estudiante: " + e.getNombre() + " (ID: " + e.getId() + ")\n";
        if (e.cantidadNotas(semestre) == 0) {
            texto += "  Sin notas en ese semestre\n";
        } else {
            texto += "  Materias: " + e.cantidadNotas(semestre) + "\n";
            texto += String.format(Locale.US, "  Promedio: %.2f%n", e.promedioSemestre(semestre));
        }
        return new Reporte("Promedio semestre " + semestre + " - " + e.getNombre(), texto);
    }
}
