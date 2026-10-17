package TallerPilasColas.ActividadIntegradora;
public class Libro {
    private int codigo;
    private String titulo;
    private String autor;
    private int seccion; // fila de la matriz donde se ubica
    private boolean disponible;

    // Nombres de las secciones (cada una es una fila de la matriz)
    public static final String[] SECCIONES = { "Sistemas", "Literatura", "Ciencias" };

    // Constructor
    public Libro(int codigo, String titulo, String autor, int seccion) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.autor = autor;
        this.seccion = seccion;
        this.disponible = true;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getSeccion() {
        return seccion;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Libro{" + "codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor
                + ", seccion=" + SECCIONES[seccion] + ", " + (disponible ? "Disponible" : "Prestado") + '}';
    }

    // ---------------- ARREGLO DE OBJETOS ----------------

    // Registra el libro en la primera posicion libre del arreglo
    public static boolean registrar(Libro[] libros, Libro nuevo) {
        if (buscarPorCodigo(libros, nuevo.getCodigo()) != null) {
            System.out.println("Ya existe un libro con codigo " + nuevo.getCodigo());
            return false;
        }
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] == null) {
                libros[i] = nuevo;
                return true;
            }
        }
        System.out.println("Catalogo lleno: no se pudo registrar " + nuevo.getTitulo());
        return false;
    }

    // Lista todos los libros del catalogo
    public static void listar(Libro[] libros) {
        int contador = 0;
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null) {
                System.out.println((i + 1) + ". " + libros[i]);
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("No hay libros registrados");
        }
    }

    // Busca un libro por codigo (retorna null si no existe)
    public static Libro buscarPorCodigo(Libro[] libros, int codigo) {
        for (int i = 0; i < libros.length; i++) {
            if (libros[i] != null && libros[i].getCodigo() == codigo) {
                return libros[i];
            }
        }
        return null;
    }

    // ---------------- MATRIZ DE OBJETOS ----------------

    // Ubica el libro en la primera columna libre de la fila de su seccion
    public static boolean ubicarEnSeccion(Libro[][] estante, Libro libro) {
        int fila = libro.getSeccion();
        for (int j = 0; j < estante[fila].length; j++) {
            if (estante[fila][j] == null) {
                estante[fila][j] = libro;
                return true;
            }
        }
        System.out.println("La seccion " + SECCIONES[fila] + " esta llena: no se ubico " + libro.getTitulo());
        return false;
    }

    // Muestra el estante por secciones (fila) y posicion (columna)
    public static void mostrarEstante(Libro[][] estante) {
        for (int i = 0; i < estante.length; i++) {
            System.out.println("Seccion " + SECCIONES[i] + ":");
            for (int j = 0; j < estante[i].length; j++) {
                if (estante[i][j] != null) {
                    System.out.println("  [" + i + "][" + j + "] " + estante[i][j].getTitulo()
                            + " (" + (estante[i][j].isDisponible() ? "Disponible" : "Prestado") + ")");
                } else {
                    System.out.println("  [" + i + "][" + j + "] [vacia]");
                }
            }
        }
    }
}