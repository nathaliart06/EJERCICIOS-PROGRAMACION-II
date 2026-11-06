package Semana8.ProyectoFinal.modelo;

import Semana8.ProyectoFinal.estructuras.Historial;
import Semana8.ProyectoFinal.estructuras.OperacionHorario;
import Semana8.ProyectoFinal.excepciones.*;

import java.util.*;

/**
 * Aula con su disponibilidad en una matriz boolean[7][24] (fila = dia, columna = hora).
 * true = ocupada. Una matriz String[7][24] al lado guarda quien tiene la reserva.
 */
public class Aula {
    private String codigo;
    private int capacidad;
    private boolean[][] ocupado = new boolean[DIAS][HORAS];
    private String[][] reservadoPor = new String[DIAS][HORAS];

    public static final int DIAS = 7;
    public static final int HORAS = 24;

    // Constructor
    public Aula(String codigo, int capacidad) {
        this.codigo = codigo.trim().toUpperCase();
        this.capacidad = capacidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return "Aula{" + "codigo=" + codigo + ", capacidad=" + capacidad + ", horasReservadas=" + horasOcupadas() + '}';
    }

    // ---------------- MATRIZ boolean[7][24] ----------------

    /** Metodo para reservar sin responsable (reserva general) */
    public void reservar(int dia, int hora, int duracion) throws HorarioConflictivoException {
        reservar(dia, hora, duracion, "Reserva");
    }

    /**
     * Sobrecarga: reserva a nombre de un responsable (el codigo de la materia).
     * Primero revisa todas las horas y solo si todas estan libres las marca.
     */
    public void reservar(int dia, int hora, int duracion, String responsable) throws HorarioConflictivoException {
        Horario.validar(dia, hora, duracion);
        for (int h = hora; h < hora + duracion; h++) {
            if (ocupado[dia][h]) {
                throw new HorarioConflictivoException(Horario.DIAS[dia] + " " + h + ":00 ya esta reservado en el aula "
                        + codigo + " (" + reservadoPor[dia][h] + ")");
            }
        }
        for (int h = hora; h < hora + duracion; h++) {
            ocupado[dia][h] = true;
            reservadoPor[dia][h] = responsable;
        }
    }

    /** Metodo para liberar un bloque de horas */
    public void liberar(int dia, int hora, int duracion) {
        Horario.validar(dia, hora, duracion);
        for (int h = hora; h < hora + duracion; h++) {
            ocupado[dia][h] = false;
            reservadoPor[dia][h] = null;
        }
    }

    /** Metodo que consulta si una hora esta disponible */
    public boolean consultarDisponibilidad(int dia, int hora) {
        Horario.validar(dia, hora, 1);
        return !ocupado[dia][hora];
    }

    public String getReservadoPor(int dia, int hora) {
        return reservadoPor[dia][hora];
    }

    /** Metodo que muestra las horas ocupadas de un dia */
    public void mostrarDia(int dia) {
        int contador = 0;
        for (int h = 0; h < HORAS; h++) {
            if (ocupado[dia][h]) {
                System.out.println("  " + h + ":00 -> OCUPADO (" + reservadoPor[dia][h] + ")");
                contador++;
            }
        }
        if (contador == 0) {
            System.out.println("  Sin reservas ese dia");
        }
    }

    /** Metodo que cuenta las horas reservadas en la semana */
    public int horasOcupadas() {
        int contador = 0;
        for (int d = 0; d < DIAS; d++) {
            for (int h = 0; h < HORAS; h++) {
                if (ocupado[d][h]) {
                    contador++;
                }
            }
        }
        return contador;
    }

    // Fotos de las matrices para deshacer (copia fila por fila)
    public boolean[][] fotoOcupado() {
        boolean[][] copia = new boolean[DIAS][];
        for (int d = 0; d < DIAS; d++) {
            copia[d] = ocupado[d].clone();
        }
        return copia;
    }

    public String[][] fotoReservas() {
        String[][] copia = new String[DIAS][];
        for (int d = 0; d < DIAS; d++) {
            copia[d] = reservadoPor[d].clone();
        }
        return copia;
    }

    /** Metodo que vuelve el aula a una foto anterior (lo usa deshacer/rehacer) */
    public void restaurar(boolean[][] fotoOcupado, String[][] fotoReservas) {
        for (int d = 0; d < DIAS; d++) {
            ocupado[d] = fotoOcupado[d].clone();
            reservadoPor[d] = fotoReservas[d].clone();
        }
    }

    // ---------------- TREEMAP DE AULAS ----------------

    /** Metodo que crea las aulas de la universidad en un TreeMap (ordenadas por nombre) */
    public static TreeMap<String, Aula> crearAulas() {
        TreeMap<String, Aula> aulas = new TreeMap<>();
        aulas.put("101", new Aula("101", 40));
        aulas.put("102", new Aula("102", 30));
        aulas.put("201", new Aula("201", 35));
        aulas.put("LAB-1", new Aula("LAB-1", 25));
        aulas.put("AUD-1", new Aula("AUD-1", 120));
        return aulas;
    }

    /** Metodo para buscar un aula */
    public static Aula buscar(TreeMap<String, Aula> aulas, String codigo) {
        Aula a = aulas.get(codigo.trim().toUpperCase());
        if (a == null) {
            throw new IllegalArgumentException("No existe el aula " + codigo);
        }
        return a;
    }

    /** Metodo que lista las aulas ordenadas por nombre (el TreeMap ya las tiene en orden) */
    public static void listarPorNombre(TreeMap<String, Aula> aulas) {
        for (Aula a : aulas.values()) {
            System.out.println("  " + a);
        }
    }

    /** Metodo que lista las aulas de mayor a menor capacidad (ordenamiento por insercion) */
    public static void listarPorCapacidad(TreeMap<String, Aula> aulas) {
        Aula[] arreglo = aulas.values().toArray(new Aula[0]);
        for (int i = 1; i < arreglo.length; i++) {
            Aula actual = arreglo[i];
            int j = i - 1;
            while (j >= 0 && arreglo[j].getCapacidad() < actual.getCapacidad()) {
                arreglo[j + 1] = arreglo[j];
                j--;
            }
            arreglo[j + 1] = actual;
        }
        for (Aula a : arreglo) {
            System.out.println("  " + a);
        }
    }

    // ---------------- RESERVAR / LIBERAR HORARIOS ----------------

    /**
     * Metodo para reservar un bloque. Si se indica una materia, ese bloque queda como su horario;
     * si ya tenia horario, se libera el anterior (cambio de horario). Si hay conflicto no cambia nada.
     * Se puede deshacer.
     */
    public static void reservarHorario(TreeMap<String, Aula> aulas, Map<String, Materia> materias, String codigoAula,
                                       int dia, int hora, int duracion, String codigoMateria) throws HorarioConflictivoException {
        Horario.validar(dia, hora, duracion);
        Aula aula = buscar(aulas, codigoAula);
        Materia materia = (codigoMateria == null || codigoMateria.isBlank()) ? null : Materia.buscar(materias, codigoMateria);

        LinkedList<Aula> aulasAfectadas = new LinkedList<>();
        aulasAfectadas.add(aula);
        LinkedList<Materia> materiasAfectadas = new LinkedList<>();
        Horario anterior = null;
        Aula aulaAnterior = null;
        if (materia != null) {
            materiasAfectadas.add(materia);
            anterior = materia.getHorario();
            if (anterior != null) {
                aulaAnterior = buscar(aulas, anterior.getAula());
                if (aulaAnterior != aula) {
                    aulasAfectadas.add(aulaAnterior);
                }
            }
        }

        Horario nuevo = new Horario(aula.getCodigo(), dia, hora, duracion);
        String descripcion = materia == null ? "Reserva en aula " + aula.getCodigo() + ": " + nuevo
                : (anterior == null ? "Asignar horario a " : "Cambiar horario de ") + materia.getCodigo() + " -> " + nuevo;
        OperacionHorario op = new OperacionHorario(descripcion, aulasAfectadas, materiasAfectadas);

        try {
            if (anterior != null) {
                aulaAnterior.liberar(anterior.getDia(), anterior.getHora(), anterior.getDuracion());
            }
            if (materia == null) {
                aula.reservar(dia, hora, duracion);
            } else {
                aula.reservar(dia, hora, duracion, materia.getCodigo());
            }
        } catch (HorarioConflictivoException e) {
            op.deshacer(); // si falla, se devuelve el horario anterior
            throw e;
        }
        if (materia != null) {
            materia.setHorario(nuevo);
        }
        op.guardarDespues();
        Historial.registrar(op);
    }

    /**
     * Metodo para liberar un bloque. Las materias que tenian clase ahi quedan sin horario.
     * Retorna los codigos de esas materias. Se puede deshacer.
     */
    public static LinkedList<String> liberarHorario(TreeMap<String, Aula> aulas, Map<String, Materia> materias,
                                                   String codigoAula, int dia, int hora, int duracion) {
        Horario.validar(dia, hora, duracion);
        Aula aula = buscar(aulas, codigoAula);
        boolean habiaReserva = false;
        for (int h = hora; h < hora + duracion; h++) {
            if (!aula.consultarDisponibilidad(dia, h)) {
                habiaReserva = true;
            }
        }
        if (!habiaReserva) {
            throw new IllegalArgumentException("No hay reservas en el aula " + aula.getCodigo() + " el "
                    + Horario.DIAS[dia] + " entre " + hora + ":00 y " + (hora + duracion) + ":00");
        }

        Horario bloque = new Horario(aula.getCodigo(), dia, hora, duracion);
        LinkedList<Materia> afectadas = new LinkedList<>();
        for (Materia m : materias.values()) {
            if (m.getHorario() != null && m.getHorario().getAula().equals(aula.getCodigo()) && m.getHorario().seCruzaCon(bloque)) {
                afectadas.add(m);
            }
        }
        LinkedList<Aula> aulasAfectadas = new LinkedList<>();
        aulasAfectadas.add(aula);
        OperacionHorario op = new OperacionHorario("Liberar aula " + aula.getCodigo() + ": " + bloque, aulasAfectadas, afectadas);

        aula.liberar(dia, hora, duracion);
        LinkedList<String> codigos = new LinkedList<>();
        for (Materia m : afectadas) {
            Horario h = m.getHorario();
            aula.liberar(h.getDia(), h.getHora(), h.getDuracion()); // se libera todo el bloque de la materia
            m.setHorario(null);
            codigos.add(m.getCodigo());
        }
        op.guardarDespues();
        Historial.registrar(op);
        return codigos;
    }
}
