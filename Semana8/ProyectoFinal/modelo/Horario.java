package Semana8.ProyectoFinal.modelo;

/**
 * Horario de una materia: aula, dia (0=Domingo ... 6=Sabado), hora de inicio y duracion.
 */
public class Horario {
    private String aula;
    private int dia;
    private int hora;
    private int duracion;

    public static final String[] DIAS = { "Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado" };

    // Constructor
    public Horario(String aula, int dia, int hora, int duracion) {
        validar(dia, hora, duracion);
        this.aula = aula;
        this.dia = dia;
        this.hora = hora;
        this.duracion = duracion;
    }

    public String getAula() {
        return aula;
    }

    public int getDia() {
        return dia;
    }

    public int getHora() {
        return hora;
    }

    public int getDuracion() {
        return duracion;
    }

    public int getHoraFin() {
        return hora + duracion;
    }

    /** Metodo que dice si dos horarios se cruzan (mismo dia y las horas se solapan) */
    public boolean seCruzaCon(Horario otro) {
        return dia == otro.dia && hora < otro.getHoraFin() && otro.hora < getHoraFin();
    }

    /** Metodo que valida los rangos de la matriz boolean[7][24] */
    public static void validar(int dia, int hora, int duracion) {
        if (dia < 0 || dia > 6) {
            throw new IllegalArgumentException("El dia debe estar entre 0 (Domingo) y 6 (Sabado)");
        }
        if (hora < 0 || hora > 23) {
            throw new IllegalArgumentException("La hora debe estar entre 0 y 23");
        }
        if (duracion < 1 || hora + duracion > 24) {
            throw new IllegalArgumentException("La duracion debe ser minimo 1 hora y terminar antes de las 24:00");
        }
    }

    @Override
    public String toString() {
        return DIAS[dia] + " " + hora + ":00-" + getHoraFin() + ":00 (Aula " + aula + ")";
    }
}
