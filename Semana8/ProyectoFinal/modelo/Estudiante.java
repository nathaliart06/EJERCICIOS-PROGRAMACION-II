package Semana8.ProyectoFinal.modelo;

import Semana8.ProyectoFinal.estructuras.Historial;
import Semana8.ProyectoFinal.estructuras.OperacionInscripcion;
import Semana8.ProyectoFinal.estructuras.OperacionNota;
import Semana8.ProyectoFinal.excepciones.*;

import java.util.*;

/**
 * Estudiante: hereda de Persona y agrega semestre, notas e historial.
 * Las notas van en un arreglo estatico Double[10][20] (10 semestres x 20 materias)
 * y al lado una matriz String[10][20] con el codigo de cada materia, porque el
 * Double solo no dice de que materia es la nota.
 */
public class Estudiante extends Persona {
    private int semestre;
    private Double[][] notas = new Double[SEMESTRES][MATERIAS_POR_SEMESTRE];
    private String[][] codigosMaterias = new String[SEMESTRES][MATERIAS_POR_SEMESTRE];
    private LinkedList<MateriaCursada> historialMaterias = new LinkedList<>();

    // Capacidad del arreglo de estudiantes y tamano de la matriz de notas
    public static final int CAPACIDAD = 100;
    public static final int SEMESTRES = 10;
    public static final int MATERIAS_POR_SEMESTRE = 20;
    public static final double NOTA_APROBATORIA = 3.0;

    // Constructor
    public Estudiante(String id, String nombre, String email, int semestre) {
        super(id, nombre, email);
        if (semestre < 1 || semestre > SEMESTRES) {
            throw new IllegalArgumentException("El semestre debe estar entre 1 y " + SEMESTRES);
        }
        this.semestre = semestre;
    }

    public int getSemestre() {
        return semestre;
    }

    public LinkedList<MateriaCursada> getHistorialMaterias() {
        return new LinkedList<>(historialMaterias); // copia para no dañar la lista original
    }

    @Override
    public String getRol() {
        return "Estudiante";
    }

    // Sobrescritura: agrega el semestre y el promedio a los datos de Persona
    @Override
    public String mostrarInformacion() {
        String promedio = historialMaterias.isEmpty()
                ? "0.0 (sin notas aun)"
                : String.format(Locale.US, "%.2f", promedioAcumulado());
        return super.mostrarInformacion() + "\n"
                + "  Semestre: " + semestre + "\n"
                + "  Promedio acumulado: " + promedio;
    }

    @Override
    public String toString() {
        return "Estudiante{" + "id=" + getId() + ", nombre=" + getNombre() + ", email=" + getEmail()
                + ", semestre=" + semestre + '}';
    }

    // ---------------- NOTAS: ARREGLO Double[10][20] ----------------

    /** Metodo que busca la columna de la materia en ese semestre (si ya tiene nota) o la primera libre. -1 si esta lleno */
    public int buscarColumna(int semestre, String codigo) {
        int fila = semestre - 1;
        int libre = -1;
        for (int j = 0; j < MATERIAS_POR_SEMESTRE; j++) {
            if (codigo.equals(codigosMaterias[fila][j])) {
                return j;
            }
            if (libre == -1 && codigosMaterias[fila][j] == null) {
                libre = j;
            }
        }
        return libre;
    }

    public Double getNota(int semestre, int columna) {
        return notas[semestre - 1][columna];
    }

    public String getCodigoMateria(int semestre, int columna) {
        return codigosMaterias[semestre - 1][columna];
    }

    /** Metodo que pone la nota en la matriz y actualiza el historial. Si la nota es null deja la casilla vacia (lo usa deshacer) */
    public void ponerNota(int semestre, int columna, String codigo, Double nota) {
        int fila = semestre - 1;
        // si ya habia una materia en esa casilla, se quita del historial
        if (codigosMaterias[fila][columna] != null) {
            quitarDelHistorial(codigosMaterias[fila][columna], semestre);
        }
        if (nota == null) {
            notas[fila][columna] = null;
            codigosMaterias[fila][columna] = null;
        } else {
            notas[fila][columna] = nota;
            codigosMaterias[fila][columna] = codigo;
            historialMaterias.add(new MateriaCursada(codigo, semestre, nota));
        }
    }

    // Metodo que quita una materia del historial (lista enlazada)
    private void quitarDelHistorial(String codigo, int semestre) {
        Iterator<MateriaCursada> it = historialMaterias.iterator();
        while (it.hasNext()) {
            MateriaCursada m = it.next();
            if (m.getCodigoMateria().equals(codigo) && m.getSemestre() == semestre) {
                it.remove();
                return;
            }
        }
    }

    /** Metodo que recorre el historial para saber si aprobo una materia */
    public boolean haAprobado(String codigoMateria) {
        for (MateriaCursada m : historialMaterias) {
            if (m.getCodigoMateria().equals(codigoMateria) && m.estaAprobada()) {
                return true;
            }
        }
        return false;
    }

    /** Metodo que cuenta cuantas notas tiene en un semestre */
    public int cantidadNotas(int semestre) {
        int contador = 0;
        for (int j = 0; j < MATERIAS_POR_SEMESTRE; j++) {
            if (notas[semestre - 1][j] != null) {
                contador++;
            }
        }
        return contador;
    }

    /** Metodo para calcular el promedio de un semestre (0.0 si no tiene notas) */
    public double promedioSemestre(int semestre) {
        double suma = 0;
        int contador = 0;
        for (int j = 0; j < MATERIAS_POR_SEMESTRE; j++) {
            if (notas[semestre - 1][j] != null) {
                suma += notas[semestre - 1][j];
                contador++;
            }
        }
        return contador == 0 ? 0.0 : suma / contador;
    }

    /** Metodo para calcular el promedio acumulado recorriendo toda la matriz */
    public double promedioAcumulado() {
        double suma = 0;
        int contador = 0;
        for (int i = 0; i < SEMESTRES; i++) {
            for (int j = 0; j < MATERIAS_POR_SEMESTRE; j++) {
                if (notas[i][j] != null) {
                    suma += notas[i][j];
                    contador++;
                }
            }
        }
        return contador == 0 ? 0.0 : suma / contador;
    }

    /** Metodo que cuenta las materias aprobadas */
    public int contarAprobadas() {
        int contador = 0;
        for (MateriaCursada m : historialMaterias) {
            if (m.estaAprobada()) {
                contador++;
            }
        }
        return contador;
    }

    /** Metodo que cuenta las materias reprobadas */
    public int contarReprobadas() {
        return historialMaterias.size() - contarAprobadas();
    }

    public boolean tieneNotas() {
        return !historialMaterias.isEmpty();
    }

    // ---------------- ARREGLO DE ESTUDIANTES + HASHMAP ----------------

    /** Metodo para registrar el estudiante en la primera posicion libre del arreglo y en el HashMap */
    public static void registrar(Estudiante[] estudiantes, Map<String, Estudiante> indice, Estudiante nuevo) {
        if (indice.containsKey(nuevo.getId())) {
            throw new IllegalArgumentException("Ya existe un estudiante con ID: " + nuevo.getId());
        }
        for (int i = 0; i < estudiantes.length; i++) {
            if (estudiantes[i] == null) {
                estudiantes[i] = nuevo;
                indice.put(nuevo.getId(), nuevo);
                return;
            }
        }
        throw new IllegalArgumentException("El arreglo de estudiantes esta lleno (" + estudiantes.length + ")");
    }

    /** Metodo para buscar por ID usando el HashMap */
    public static Estudiante buscar(Map<String, Estudiante> indice, String id) throws EstudianteNoEncontradoException {
        Estudiante e = indice.get(id.trim());
        if (e == null) {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + id.trim());
        }
        return e;
    }

    /** Metodo para listar todos los estudiantes del arreglo */
    public static void listar(Estudiante[] estudiantes) {
        int contador = 0;
        System.out.printf("%-4s %-10s %-22s %-36s %-4s %s%n", "#", "ID", "Nombre", "Email", "Sem", "Promedio");
        for (int i = 0; i < estudiantes.length; i++) {
            if (estudiantes[i] != null) {
                Estudiante e = estudiantes[i];
                contador++;
                System.out.printf(Locale.US, "%-4d %-10s %-22s %-36s %-4d %.2f%n", contador, e.getId(),
                        e.getNombre(), e.getEmail(), e.getSemestre(), e.promedioAcumulado());
            }
        }
        if (contador == 0) {
            System.out.println("No hay estudiantes registrados");
        } else {
            System.out.println("Total: " + contador);
        }
    }

    /** Metodo que cuenta los estudiantes registrados */
    public static int contar(Estudiante[] estudiantes) {
        int contador = 0;
        for (int i = 0; i < estudiantes.length; i++) {
            if (estudiantes[i] != null) {
                contador++;
            }
        }
        return contador;
    }

    /** Metodo que devuelve el nombre del estudiante o el ID si ya no existe */
    public static String nombreDe(Map<String, Estudiante> indice, String id) {
        Estudiante e = indice.get(id);
        return e == null ? "(ID " + id + ")" : e.getNombre();
    }

    /**
     * Metodo para eliminar (baja definitiva). Lo saca de las materias donde estaba
     * inscrito o en espera (el cupo pasa al primero de la cola), deja null en su
     * posicion del arreglo y lo quita del HashMap. Se puede deshacer.
     */
    public static void eliminar(Estudiante[] estudiantes, Map<String, Estudiante> indice,
                                Map<String, Materia> materias, String id) throws EstudianteNoEncontradoException {
        Estudiante e = buscar(indice, id);

        // materias donde aparece el estudiante
        LinkedList<Materia> afectadas = new LinkedList<>();
        for (Materia m : materias.values()) {
            if (m.estaInscrito(e.getId()) || m.estaEnEspera(e.getId())) {
                afectadas.add(m);
            }
        }

        int posicion = posicionEnArreglo(estudiantes, e);
        OperacionInscripcion op = new OperacionInscripcion(estudiantes, indice, e, posicion, afectadas);

        for (Materia m : afectadas) {
            boolean estabaInscrito = m.retirar(e.getId());
            m.salirDeEspera(e.getId());
            if (estabaInscrito) {
                m.asignarSiguienteDeEspera();
            }
        }
        quitar(estudiantes, indice, e);
        op.guardarDespues();
        Historial.registrar(op);
    }

    /** Metodo que deja null la posicion del estudiante y lo quita del HashMap */
    public static void quitar(Estudiante[] estudiantes, Map<String, Estudiante> indice, Estudiante e) {
        int posicion = posicionEnArreglo(estudiantes, e);
        if (posicion >= 0) {
            estudiantes[posicion] = null;
        }
        indice.remove(e.getId());
    }

    /** Metodo que vuelve a poner al estudiante en su posicion (o en la primera libre si ya la ocuparon) */
    public static void reinsertar(Estudiante[] estudiantes, Map<String, Estudiante> indice, Estudiante e, int posicion) {
        if (indice.containsKey(e.getId())) {
            return;
        }
        if (posicion >= 0 && estudiantes[posicion] == null) {
            estudiantes[posicion] = e;
        } else {
            for (int i = 0; i < estudiantes.length; i++) {
                if (estudiantes[i] == null) {
                    estudiantes[i] = e;
                    break;
                }
            }
        }
        indice.put(e.getId(), e);
    }

    private static int posicionEnArreglo(Estudiante[] estudiantes, Estudiante e) {
        for (int i = 0; i < estudiantes.length; i++) {
            if (estudiantes[i] == e) {
                return i;
            }
        }
        return -1;
    }

    // ---------------- REGISTRAR NOTA ----------------

    /** Metodo para registrar (o corregir) la nota de una materia en un semestre. Se puede deshacer */
    public static void registrarNota(Map<String, Estudiante> indice, Map<String, Materia> materias, String id,
                                     int semestre, String codigoMateria, double nota) throws EstudianteNoEncontradoException {
        Estudiante e = buscar(indice, id);
        Materia m = Materia.buscar(materias, codigoMateria);
        if (semestre < 1 || semestre > SEMESTRES) {
            throw new IllegalArgumentException("El semestre debe estar entre 1 y " + SEMESTRES);
        }
        if (nota < 0.0 || nota > 5.0) {
            throw new IllegalArgumentException("La nota debe estar entre 0.0 y 5.0");
        }
        int columna = e.buscarColumna(semestre, m.getCodigo());
        if (columna == -1) {
            throw new IllegalArgumentException("El semestre " + semestre + " ya tiene " + MATERIAS_POR_SEMESTRE + " materias");
        }
        OperacionNota op = new OperacionNota(e, semestre, columna, e.getCodigoMateria(semestre, columna),
                e.getNota(semestre, columna), m.getCodigo(), nota);
        e.ponerNota(semestre, columna, m.getCodigo(), nota);
        Historial.registrar(op);
    }
}
