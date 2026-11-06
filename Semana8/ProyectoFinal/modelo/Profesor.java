package Semana8.ProyectoFinal.modelo;

import java.util.*;

/**
 * Profesor: segunda clase hija de Persona.
 */
public class Profesor extends Persona {
    private String departamento;
    private double salario;

    // Constructor
    public Profesor(String id, String nombre, String email, String departamento, double salario) {
        super(id, nombre, email);
        this.departamento = departamento;
        this.salario = salario;
    }

    public String getDepartamento() {
        return departamento;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String getRol() {
        return "Profesor";
    }

    // Sobrescritura: agrega departamento y salario
    @Override
    public String mostrarInformacion() {
        return super.mostrarInformacion() + "\n"
                + "  Departamento: " + departamento + "\n"
                + "  Salario: $" + String.format(Locale.US, "%,.0f", salario);
    }

    /** Metodo para registrar un profesor en el HashMap */
    public static void registrar(Map<String, Profesor> profesores, Profesor nuevo) {
        if (profesores.containsKey(nuevo.getId())) {
            throw new IllegalArgumentException("Ya existe un profesor con ID: " + nuevo.getId());
        }
        profesores.put(nuevo.getId(), nuevo);
    }

    /** Metodo para buscar un profesor por ID */
    public static Profesor buscar(Map<String, Profesor> profesores, String id) {
        Profesor p = profesores.get(id.trim().toUpperCase());
        if (p == null) {
            throw new IllegalArgumentException("No existe profesor con ID: " + id);
        }
        return p;
    }
}
