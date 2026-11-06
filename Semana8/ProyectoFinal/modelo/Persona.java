package Semana8.ProyectoFinal.modelo;

/**
 * Clase abstracta Persona: tiene los datos comunes de estudiantes y profesores.
 * No se pueden crear objetos Persona, solo de sus hijas.
 */
public abstract class Persona {
    private String id;
    private String nombre;
    private String email;

    // Constructor
    public Persona(String id, String nombre, String email) {
        if (id == null || id.isBlank() || nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El ID y el nombre son obligatorios");
        }
        if (!emailValido(email)) {
            throw new IllegalArgumentException("Email con formato invalido: " + email);
        }
        this.id = id.trim();
        this.nombre = nombre.trim();
        this.email = email.trim();
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    /** Metodo abstracto: cada clase hija dice su rol (Estudiante o Profesor). */
    public abstract String getRol();

    /** Metodo que muestra los datos basicos. Las clases hijas lo sobrescriben y agregan los suyos. */
    public String mostrarInformacion() {
        return "  ID: " + id + "\n"
                + "  Nombre: " + nombre + "\n"
                + "  Email: " + email;
    }

    /** Metodo que valida el formato del email: algo@dominio.ext */
    public static boolean emailValido(String email) {
        if (email == null) {
            return false;
        }
        String e = email.trim();
        int arroba = e.indexOf('@');
        return arroba > 0 && e.indexOf('.', arroba) > arroba + 1 && !e.endsWith(".");
    }

    @Override
    public String toString() {
        return getRol() + "{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + '}';
    }
}
