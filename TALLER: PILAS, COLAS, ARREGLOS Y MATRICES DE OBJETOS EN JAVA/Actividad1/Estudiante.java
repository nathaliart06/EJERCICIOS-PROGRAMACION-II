package Actividad1;

public class Estudiante {
    private int codigo;
    private String nombre;
    private String programa;
    private double promedio;

    // Constructor
    public Estudiante(int codigo, String nombre, String programa, double promedio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.programa = programa;
        this.promedio = promedio;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPrograma() {
        return programa;
    }

    public double getPromedio() {
        return promedio;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "codigo=" + codigo + ", nombre=" + nombre + ", programa=" + programa
                + ", promedio=" + promedio + '}';
    }

    // Metodo para registrar un estudiante en la primera posicion libre del arreglo
    // Retorna true si se pudo registrar, false si el arreglo esta lleno
    public static boolean registrar(Estudiante[] e, Estudiante nuevo) {
        for (int i = 0; i < e.length; i++) {
            if (e[i] == null) {
                e[i] = nuevo;
                return true;
            }
        }
        System.out.println("No se pudo registrar a " + nuevo.getNombre() + ": el arreglo esta lleno");
        return false;
    }

    // Metodo para listar todos los estudiantes registrados
    public static void listar(Estudiante[] e) {
        int contador = 0;
        for (int i = 0; i < e.length; i++) {
            if (e[i] != null) {
                System.out.println((i + 1) + ". " + e[i]);
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No hay estudiantes registrados");
        }
    }

    // Metodo para buscar un estudiante por codigo (retorna null si no existe)
    public static Estudiante buscarPorCodigo(Estudiante[] e, int codigo) {
        for (int i = 0; i < e.length; i++) {
            if (e[i] != null && e[i].getCodigo() == codigo) {
                return e[i];
            }
        }
        return null;
    }

    // Metodo para mostrar los estudiantes con promedio >= 4.0
    public static void mostrarDestacados(Estudiante[] e) {
        int contador = 0;
        for (int i = 0; i < e.length; i++) {
            if (e[i] != null && e[i].getPromedio() >= 4.0) {
                System.out.println(e[i]);
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("Ningun estudiante tiene promedio igual o superior a 4.0");
        }
    }

    // Metodo para identificar al estudiante con el promedio mas alto
    public static Estudiante mejorPromedio(Estudiante[] e) {
        Estudiante mejor = null;
        for (int i = 0; i < e.length; i++) {
            if (e[i] != null) {
                if (mejor == null || e[i].getPromedio() > mejor.getPromedio()) {
                    mejor = e[i];
                }
            }
        }
        return mejor;
    }
}
}
