package Semana8.ProyectoFinal.modelo;

import java.util.*;

/**
 * Edificios del campus como un grafo no dirigido con matriz de adyacencia int[N][N].
 * distancias[i][j] = metros entre i y j (0 = no hay conexion directa).
 * La ruta mas corta se calcula con el algoritmo de Dijkstra.
 */
public class Campus {
    private String[] edificios = new String[MAX_EDIFICIOS];
    private int[][] distancias = new int[MAX_EDIFICIOS][MAX_EDIFICIOS];
    private int cantidad;

    public static final int MAX_EDIFICIOS = 15;
    private static final int INFINITO = Integer.MAX_VALUE;

    public int getCantidad() {
        return cantidad;
    }

    public String getEdificio(int i) {
        return edificios[i];
    }

    /** Metodo que crea el campus con los 5 edificios y sus conexiones */
    public static Campus crearCampus() {
        Campus c = new Campus();
        try {
            c.agregarEdificio("Ingenieria");   // 0
            c.agregarEdificio("Biblioteca");   // 1
            c.agregarEdificio("Cafeteria");    // 2
            c.agregarEdificio("Rectoria");     // 3
            c.agregarEdificio("Laboratorios"); // 4
            c.agregarConexion(0, 1, 200);
            c.agregarConexion(0, 2, 150);
            c.agregarConexion(0, 4, 100);
            c.agregarConexion(1, 2, 90);
            c.agregarConexion(1, 3, 250);
            c.agregarConexion(2, 3, 180);
            c.agregarConexion(2, 4, 120);
            c.agregarConexion(3, 4, 300);
        } catch (Exception e) {
            System.out.println("Error creando el campus: " + e.getMessage());
        }
        return c;
    }

    /** Metodo para agregar un edificio en la siguiente posicion del arreglo. Retorna su numero */
    public int agregarEdificio(String nombre) {
        for (int i = 0; i < cantidad; i++) {
            if (edificios[i].equalsIgnoreCase(nombre.trim())) {
                throw new IllegalArgumentException("Ya existe el edificio " + nombre);
            }
        }
        if (cantidad == MAX_EDIFICIOS) {
            throw new IllegalArgumentException("Maximo " + MAX_EDIFICIOS + " edificios");
        }
        edificios[cantidad] = nombre.trim();
        cantidad++;
        return cantidad - 1;
    }

    /** Metodo para agregar la conexion en los dos sentidos (grafo no dirigido) */
    public void agregarConexion(int origen, int destino, int metros) {
        validar(origen);
        validar(destino);
        if (origen == destino || metros <= 0) {
            throw new IllegalArgumentException("Conexion invalida: edificios distintos y distancia mayor que 0");
        }
        distancias[origen][destino] = metros;
        distancias[destino][origen] = metros;
    }

    /**
     * Metodo con el algoritmo de Dijkstra.
     * 1. dist[] empieza en infinito, menos el origen que vale 0.
     * 2. En cada vuelta se toma el edificio no visitado con menor distancia.
     * 3. Se revisan sus vecinos: si pasar por el es mas corto, se actualiza dist[] y previo[].
     * 4. El camino se arma desde el destino hacia atras con previo[] y una pila lo pone en orden.
     * Retorna el texto con la ruta y la distancia total.
     */
    public String rutaMasCorta(int origen, int destino) {
        validar(origen);
        validar(destino);
        int[] dist = new int[cantidad];
        int[] previo = new int[cantidad];
        boolean[] visitado = new boolean[cantidad];
        for (int i = 0; i < cantidad; i++) {
            dist[i] = INFINITO;
            previo[i] = -1;
        }
        dist[origen] = 0;

        for (int vuelta = 0; vuelta < cantidad; vuelta++) {
            int u = -1;
            for (int i = 0; i < cantidad; i++) {
                if (!visitado[i] && dist[i] != INFINITO && (u == -1 || dist[i] < dist[u])) {
                    u = i;
                }
            }
            if (u == -1) {
                break;
            }
            visitado[u] = true;
            for (int v = 0; v < cantidad; v++) {
                if (!visitado[v] && distancias[u][v] > 0 && dist[u] + distancias[u][v] < dist[v]) {
                    dist[v] = dist[u] + distancias[u][v];
                    previo[v] = u;
                }
            }
        }

        if (dist[destino] == INFINITO) {
            throw new IllegalArgumentException("No existe ruta entre " + edificios[origen] + " y " + edificios[destino]);
        }

        // se arma el camino al reves con una pila
        Stack<Integer> camino = new Stack<>();
        for (int v = destino; v != -1; v = previo[v]) {
            camino.push(v);
        }
        int anterior = camino.pop();
        String ruta = edificios[anterior];
        while (!camino.empty()) {
            int actual = camino.pop();
            ruta += " -> " + edificios[actual] + " (" + distancias[anterior][actual] + "m)";
            anterior = actual;
        }
        return "Ruta mas corta:\n  " + ruta + "\nDistancia TOTAL: " + dist[destino] + " metros";
    }

    /** Metodo que muestra los edificios con su numero */
    public void listarEdificios() {
        System.out.println("Edificios registrados:");
        for (int i = 0; i < cantidad; i++) {
            System.out.println("  " + i + ": " + edificios[i]);
        }
    }

    /** Metodo que muestra la matriz de adyacencia */
    public void mostrarMatriz() {
        System.out.printf("%-15s", "");
        for (int j = 0; j < cantidad; j++) {
            System.out.printf("%6d", j);
        }
        System.out.println();
        for (int i = 0; i < cantidad; i++) {
            String nombre = edificios[i].length() > 11 ? edificios[i].substring(0, 11) : edificios[i];
            System.out.printf("%2d %-12s", i, nombre);
            for (int j = 0; j < cantidad; j++) {
                System.out.printf("%6s", distancias[i][j] == 0 ? "-" : String.valueOf(distancias[i][j]));
            }
            System.out.println();
        }
    }

    private void validar(int i) {
        if (i < 0 || i >= cantidad) {
            throw new IllegalArgumentException("No existe el edificio numero " + i);
        }
    }
}
