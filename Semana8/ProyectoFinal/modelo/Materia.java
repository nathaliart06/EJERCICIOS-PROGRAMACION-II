package Semana8.ProyectoFinal.modelo;

import Semana8.ProyectoFinal.estructuras.Historial;
import Semana8.ProyectoFinal.estructuras.OperacionInscripcion;
import Semana8.ProyectoFinal.excepciones.*;

import java.util.*;

/**
 * Materia con cupos, pre-requisitos (lista enlazada), estudiantes inscritos
 * (lista enlazada) y cola de espera (Queue).
 */
public class Materia {
    private String codigo;
    private String nombre;
    private int cupos;
    private int creditos;
    private Facultad facultad;
    private Profesor profesor;
    private Horario horario;
    private LinkedList<String> prerequisitos = new LinkedList<>();
    private LinkedList<String> inscritos = new LinkedList<>();
    private Queue<String> colaEspera = new LinkedList<>();

    // Constructor
    public Materia(String codigo, String nombre, int cupos, int creditos, Facultad facultad) {
        if (cupos <= 0 || creditos <= 0) {
            throw new IllegalArgumentException("Los cupos y los creditos deben ser mayores que 0");
        }
        this.codigo = codigo.trim().toUpperCase();
        this.nombre = nombre.trim();
        this.cupos = cupos;
        this.creditos = creditos;
        this.facultad = facultad;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCupos() {
        return cupos;
    }

    public int getCreditos() {
        return creditos;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public LinkedList<String> getPrerequisitos() {
        return new LinkedList<>(prerequisitos);
    }

    public LinkedList<String> getInscritos() {
        return new LinkedList<>(inscritos);
    }

    public Queue<String> getColaEspera() {
        return new LinkedList<>(colaEspera);
    }

    public int getCantidadInscritos() {
        return inscritos.size();
    }

    public int getCantidadEnEspera() {
        return colaEspera.size();
    }

    public boolean tieneCupo() {
        return inscritos.size() < cupos;
    }

    public int cuposDisponibles() {
        return cupos - inscritos.size();
    }

    public boolean estaInscrito(String id) {
        return inscritos.contains(id);
    }

    public boolean estaEnEspera(String id) {
        return colaEspera.contains(id);
    }

    /** Metodo que dice la posicion del estudiante en la cola (1 = primero, 0 = no esta) */
    public int posicionEnEspera(String id) {
        int posicion = 1;
        for (String e : colaEspera) {
            if (e.equals(id)) {
                return posicion;
            }
            posicion++;
        }
        return 0;
    }

    public void agregarInscrito(String id) {
        inscritos.add(id);
    }

    public boolean retirar(String id) {
        return inscritos.remove(id);
    }

    public void entrarAEspera(String id) {
        colaEspera.add(id);
    }

    public boolean salirDeEspera(String id) {
        return colaEspera.remove(id);
    }

    /** Metodo que asigna el cupo libre al primero de la cola (FIFO). Retorna su ID o null */
    public String asignarSiguienteDeEspera() {
        if (!tieneCupo() || colaEspera.isEmpty()) {
            return null;
        }
        String id = colaEspera.poll();
        inscritos.add(id);
        return id;
    }

    // Fotos del estado para deshacer: copias de la lista de inscritos y de la cola
    public LinkedList<String> fotoInscritos() {
        return new LinkedList<>(inscritos);
    }

    public Queue<String> fotoCola() {
        return new LinkedList<>(colaEspera);
    }

    /** Metodo que vuelve la materia a una foto anterior (lo usa deshacer/rehacer) */
    public void restaurar(LinkedList<String> fotoInscritos, Queue<String> fotoCola) {
        inscritos = new LinkedList<>(fotoInscritos);
        colaEspera = new LinkedList<>(fotoCola);
    }

    @Override
    public String toString() {
        return "Materia{" + "codigo=" + codigo + ", nombre=" + nombre + ", inscritos=" + inscritos.size() + "/" + cupos
                + ", creditos=" + creditos + '}';
    }

    // ---------------- MAPA DE MATERIAS ----------------

    /** Metodo para crear la materia en el mapa (TreeMap: quedan ordenadas por codigo) */
    public static void crear(Map<String, Materia> materias, Materia nueva) {
        if (materias.containsKey(nueva.getCodigo())) {
            throw new IllegalArgumentException("Ya existe la materia " + nueva.getCodigo());
        }
        materias.put(nueva.getCodigo(), nueva);
    }

    /** Metodo para buscar una materia por codigo */
    public static Materia buscar(Map<String, Materia> materias, String codigo) {
        Materia m = materias.get(codigo.trim().toUpperCase());
        if (m == null) {
            throw new IllegalArgumentException("No existe la materia " + codigo.trim().toUpperCase());
        }
        return m;
    }

    /** Metodo para listar las materias con sus datos */
    public static void listar(Map<String, Materia> materias) {
        if (materias.isEmpty()) {
            System.out.println("No hay materias registradas");
            return;
        }
        for (Materia m : materias.values()) {
            System.out.printf("%-8s %-24s %d cr | %d/%d inscritos | %d en espera | %s%n", m.codigo, m.nombre,
                    m.creditos, m.inscritos.size(), m.cupos, m.colaEspera.size(),
                    m.horario == null ? "sin horario" : m.horario);
            System.out.println("         Facultad: " + m.facultad.getNombre()
                    + " | Profesor: " + (m.profesor == null ? "sin asignar" : m.profesor.getNombre())
                    + " | Pre-requisitos: " + m.prerequisitos);
        }
    }

    // ---------------- PRE-REQUISITOS: LISTA ENLAZADA ----------------

    /** Metodo para agregar un pre-requisito. No deja repetidos ni ciclos (A pide B y B pide A) */
    public static void agregarPrerequisito(Map<String, Materia> materias, String codigo, String codigoPrerequisito) {
        Materia m = buscar(materias, codigo);
        Materia pre = buscar(materias, codigoPrerequisito);
        if (m == pre) {
            throw new IllegalArgumentException("Una materia no puede ser pre-requisito de si misma");
        }
        if (m.prerequisitos.contains(pre.codigo)) {
            throw new IllegalArgumentException(pre.codigo + " ya es pre-requisito de " + m.codigo);
        }
        if (exige(materias, pre, m.codigo)) {
            throw new IllegalArgumentException("Se formaria un ciclo: " + pre.codigo + " ya exige " + m.codigo);
        }
        m.prerequisitos.add(pre.codigo);
    }

    // Metodo que revisa con una pila si "desde" exige (directa o indirectamente) la materia buscada
    private static boolean exige(Map<String, Materia> materias, Materia desde, String buscada) {
        Stack<Materia> pendientes = new Stack<>();
        LinkedList<String> visitadas = new LinkedList<>();
        pendientes.push(desde);
        while (!pendientes.empty()) {
            Materia actual = pendientes.pop();
            if (visitadas.contains(actual.codigo)) {
                continue;
            }
            visitadas.add(actual.codigo);
            for (String pre : actual.prerequisitos) {
                if (pre.equals(buscada)) {
                    return true;
                }
                if (materias.containsKey(pre)) {
                    pendientes.push(materias.get(pre));
                }
            }
        }
        return false;
    }

    /** Metodo para mostrar los pre-requisitos recorriendo la lista enlazada */
    public static void mostrarPrerequisitos(Map<String, Materia> materias, String codigo) {
        Materia m = buscar(materias, codigo);
        System.out.println("Materia: " + m.codigo + " - " + m.nombre);
        if (m.prerequisitos.isEmpty()) {
            System.out.println("  No tiene pre-requisitos");
            return;
        }
        int i = 1;
        for (String pre : m.prerequisitos) {
            System.out.println("  " + i + ". " + pre + " - " + materias.get(pre).getNombre());
            i++;
        }
    }

    // ---------------- INSCRIPCIONES Y COLA DE ESPERA ----------------

    /**
     * Metodo para inscribir. Valida pre-requisitos y cruces de horario. Si no hay cupo
     * lanza CupoLlenoException SIN cambiar nada; el que lo llama decide mandarlo a la cola.
     * Retorna los cupos que quedan.
     */
    public static int inscribir(Map<String, Materia> materias, Map<String, Estudiante> indice, String id, String codigo) throws EstudianteNoEncontradoException, PreRequisitoNoAprobadoException, HorarioConflictivoException, CupoLlenoException {
        OperacionInscripcion op = inscribirSinHistorial(materias, indice, id, codigo);
        Historial.registrar(op);
        return buscar(materias, codigo).cuposDisponibles();
    }

    /** Metodo que hace la inscripcion y retorna la operacion sin guardarla (lo usa el procesamiento por lotes) */
    public static OperacionInscripcion inscribirSinHistorial(Map<String, Materia> materias, Map<String, Estudiante> indice,
                                                             String id, String codigo) throws EstudianteNoEncontradoException, PreRequisitoNoAprobadoException, HorarioConflictivoException, CupoLlenoException {
        Estudiante e = Estudiante.buscar(indice, id);
        Materia m = buscar(materias, codigo);
        validarInscripcion(materias, e, m);
        if (!m.tieneCupo()) {
            throw new CupoLlenoException("La materia " + m.codigo + " no tiene cupos (" + m.inscritos.size() + "/" + m.cupos + ")");
        }
        OperacionInscripcion op = new OperacionInscripcion("Inscribir a " + e.getNombre() + " en " + m.codigo, m);
        m.agregarInscrito(e.getId());
        op.guardarDespues();
        op.setMensajes(e.getNombre() + " ya NO esta inscrito(a) en " + m.codigo,
                e.getNombre() + " inscrito(a) en " + m.codigo);
        return op;
    }

    // Metodo con las reglas de la inscripcion: no repetido, pre-requisitos aprobados y sin cruce de horario
    private static void validarInscripcion(Map<String, Materia> materias, Estudiante e, Materia m) throws PreRequisitoNoAprobadoException, HorarioConflictivoException {
        if (m.estaInscrito(e.getId())) {
            throw new IllegalArgumentException(e.getNombre() + " ya esta inscrito(a) en " + m.codigo);
        }
        if (m.estaEnEspera(e.getId())) {
            throw new IllegalArgumentException(e.getNombre() + " ya esta en la cola de espera de " + m.codigo
                    + " (posicion " + m.posicionEnEspera(e.getId()) + ")");
        }
        String faltantes = "";
        for (String pre : m.prerequisitos) {
            if (!e.haAprobado(pre)) {
                faltantes += faltantes.isEmpty() ? pre : ", " + pre;
            }
        }
        if (!faltantes.isEmpty()) {
            throw new PreRequisitoNoAprobadoException(e.getNombre() + " no ha aprobado " + faltantes
                    + " (pre-requisito de " + m.codigo + ")");
        }
        if (m.horario != null) {
            for (Materia otra : materias.values()) {
                if (otra != m && otra.horario != null && otra.estaInscrito(e.getId()) && otra.horario.seCruzaCon(m.horario)) {
                    throw new HorarioConflictivoException(m.codigo + " (" + m.horario + ") se cruza con "
                            + otra.codigo + " (" + otra.horario + ")");
                }
            }
        }
    }

    /** Metodo para mandar al estudiante a la cola de espera de una materia llena. Retorna su posicion */
    public static int agregarAEspera(Map<String, Materia> materias, Map<String, Estudiante> indice, String id, String codigo) throws EstudianteNoEncontradoException {
        Historial.registrar(agregarAEsperaSinHistorial(materias, indice, id, codigo));
        return buscar(materias, codigo).posicionEnEspera(id.trim());
    }

    /** Metodo que agrega a la cola y retorna la operacion sin guardarla (lo usa el procesamiento por lotes) */
    public static OperacionInscripcion agregarAEsperaSinHistorial(Map<String, Materia> materias, Map<String, Estudiante> indice,
                                                                  String id, String codigo) throws EstudianteNoEncontradoException {
        Estudiante e = Estudiante.buscar(indice, id);
        Materia m = buscar(materias, codigo);
        if (m.tieneCupo()) {
            throw new IllegalArgumentException(m.codigo + " todavia tiene cupos: inscriba directamente");
        }
        if (m.estaInscrito(e.getId()) || m.estaEnEspera(e.getId())) {
            throw new IllegalArgumentException(e.getNombre() + " ya esta inscrito(a) o en espera en " + m.codigo);
        }
        OperacionInscripcion op = new OperacionInscripcion("Cola de espera: " + e.getNombre() + " en " + m.codigo, m);
        m.entrarAEspera(e.getId());
        op.guardarDespues();
        op.setMensajes(e.getNombre() + " ya NO esta en la cola de espera de " + m.codigo,
                e.getNombre() + " vuelve a la cola de espera de " + m.codigo);
        return op;
    }

    /**
     * Metodo para cancelar la inscripcion (o salir de la cola). Si se libera un cupo
     * se le asigna automaticamente al primero de la cola. Retorna el ID de ese estudiante o null.
     */
    public static String cancelar(Map<String, Materia> materias, Map<String, Estudiante> indice, String id, String codigo) throws EstudianteNoEncontradoException {
        Estudiante e = Estudiante.buscar(indice, id);
        Materia m = buscar(materias, codigo);
        boolean inscrito = m.estaInscrito(e.getId());
        if (!inscrito && !m.estaEnEspera(e.getId())) {
            throw new IllegalArgumentException(e.getNombre() + " no esta inscrito(a) ni en espera en " + m.codigo);
        }
        OperacionInscripcion op = new OperacionInscripcion("Cancelar " + e.getNombre() + " en " + m.codigo, m);
        String promovido = null;
        if (inscrito) {
            m.retirar(e.getId());
            promovido = m.asignarSiguienteDeEspera();
        } else {
            m.salirDeEspera(e.getId());
        }
        op.guardarDespues();
        String deshacer = e.getNombre() + (inscrito ? " vuelve a estar inscrito(a) en " : " vuelve a la cola de espera de ") + m.codigo;
        String rehacer = "se cancela de nuevo " + e.getNombre() + " en " + m.codigo;
        if (promovido != null) {
            deshacer += "\n  " + Estudiante.nombreDe(indice, promovido) + " regresa a la cola de espera";
            rehacer += "\n  El cupo se asigna otra vez a " + Estudiante.nombreDe(indice, promovido);
        }
        op.setMensajes(deshacer, rehacer);
        Historial.registrar(op);
        return promovido;
    }

    /** Metodo para mostrar la cola de espera en orden de llegada */
    public static void mostrarColaEspera(Map<String, Materia> materias, Map<String, Estudiante> indice, String codigo) throws ColaDeEsperaVaciaException {
        Materia m = buscar(materias, codigo);
        System.out.println("Materia: " + m.codigo + " - " + m.nombre);
        System.out.println("Cupos totales: " + m.cupos + " | Inscritos: " + m.inscritos.size());
        if (m.colaEspera.isEmpty()) {
            throw new ColaDeEsperaVaciaException("La materia " + m.codigo + " no tiene estudiantes en cola de espera");
        }
        int posicion = 1;
        for (String id : m.colaEspera) {
            System.out.println("Posicion " + posicion + ": " + Estudiante.nombreDe(indice, id) + " (" + id + ")");
            posicion++;
        }
        System.out.println("Total en espera: " + m.colaEspera.size());
    }

    /** Metodo que lista las materias donde esta el estudiante (inscrito o en espera) */
    public static void mostrarMateriasDe(Map<String, Materia> materias, String id) {
        int contador = 0;
        for (Materia m : materias.values()) {
            if (m.estaInscrito(id)) {
                System.out.println("    - " + m.codigo + " " + m.nombre + " (inscrito/a" + (m.horario == null ? "" : ", " + m.horario) + ")");
                contador++;
            } else if (m.estaEnEspera(id)) {
                System.out.println("    - " + m.codigo + " " + m.nombre + " (cola de espera, posicion " + m.posicionEnEspera(id) + ")");
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("    ninguna");
        }
    }
}
