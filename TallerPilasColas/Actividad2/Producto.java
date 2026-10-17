public class Producto {
    private int codigo;
    private String nombre;
    private double precio;
    private int cantidad;

    // Constructor
    public Producto(int codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    @Override
    public String toString() {
        return "Producto{" + "codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio
                + ", cantidad=" + cantidad + '}';
    }

    // Metodo para registrar un producto en una posicion especifica de la matriz
    public static boolean registrar(Producto[][] m, int fila, int columna, Producto p) {
        if (fila < 0 || fila >= m.length || columna < 0 || columna >= m[0].length) {
            System.out.println("Posicion [" + fila + "][" + columna + "] fuera de la matriz");
            return false;
        }
        if (m[fila][columna] != null) {
            System.out.println("La posicion [" + fila + "][" + columna + "] ya esta ocupada");
            return false;
        }
        m[fila][columna] = p;
        return true;
    }

    // Metodo para mostrar los productos indicando fila y columna
    public static void mostrar(Producto[][] m) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] != null) {
                    System.out.println("Fila " + i + ", Columna " + j + " -> " + m[i][j]);
                } else {
                    System.out.println("Fila " + i + ", Columna " + j + " -> [vacia]");
                }
            }
        }
    }

    // Metodo para buscar un producto por codigo, imprime su ubicacion
    public static Producto buscarPorCodigo(Producto[][] m, int codigo) {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] != null && m[i][j].getCodigo() == codigo) {
                    System.out.println("Encontrado en fila " + i + ", columna " + j);
                    return m[i][j];
                }
            }
        }
        return null;
    }

    // Metodo que calcula el valor total del inventario (precio * cantidad)
    public static double valorTotal(Producto[][] m) {
        double total = 0.0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] != null) {
                    total += m[i][j].getPrecio() * m[i][j].getCantidad();
                }
            }
        }
        return total;
    }

    // Metodo que identifica el producto con mayor cantidad disponible
    public static Producto mayorCantidad(Producto[][] m) {
        Producto mayor = null;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] != null) {
                    if (mayor == null || m[i][j].getCantidad() > mayor.getCantidad()) {
                        mayor = m[i][j];
                    }
                }
            }
        }
        return mayor;
    }

    // Metodo que cuenta las posiciones vacias de la matriz
    public static int contarVacias(Producto[][] m) {
        int vacias = 0;
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                if (m[i][j] == null) {
                    vacias++;
                }
            }
        }
        return vacias;
    }
}
