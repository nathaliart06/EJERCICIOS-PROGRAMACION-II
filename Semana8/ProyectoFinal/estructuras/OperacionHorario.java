package Semana8.ProyectoFinal.estructuras;

import Semana8.ProyectoFinal.modelo.Aula;
import Semana8.ProyectoFinal.modelo.Horario;
import Semana8.ProyectoFinal.modelo.Materia;

import java.util.*;

/**
 * Reserva, liberacion o cambio de horario. Guarda una copia de la matriz boolean[7][24]
 * de cada aula afectada y el horario de cada materia, antes y despues del cambio.
 */
public class OperacionHorario extends Operacion {
    private LinkedList<Aula> aulas;
    private LinkedList<Materia> materias;
    private LinkedList<boolean[][]> ocupadoAntes = new LinkedList<>();
    private LinkedList<String[][]> reservasAntes = new LinkedList<>();
    private LinkedList<boolean[][]> ocupadoDespues = new LinkedList<>();
    private LinkedList<String[][]> reservasDespues = new LinkedList<>();
    private LinkedList<Horario> horariosAntes = new LinkedList<>();
    private LinkedList<Horario> horariosDespues = new LinkedList<>();

    // Constructor: toma la foto de antes
    public OperacionHorario(String descripcion, LinkedList<Aula> aulas, LinkedList<Materia> materias) {
        super(descripcion);
        this.aulas = new LinkedList<>(aulas);
        this.materias = new LinkedList<>(materias);
        for (Aula a : aulas) {
            ocupadoAntes.add(a.fotoOcupado());
            reservasAntes.add(a.fotoReservas());
        }
        for (Materia m : materias) {
            horariosAntes.add(m.getHorario());
        }
    }

    /** Metodo que toma la foto de despues */
    public void guardarDespues() {
        ocupadoDespues.clear();
        reservasDespues.clear();
        horariosDespues.clear();
        for (Aula a : aulas) {
            ocupadoDespues.add(a.fotoOcupado());
            reservasDespues.add(a.fotoReservas());
        }
        for (Materia m : materias) {
            horariosDespues.add(m.getHorario());
        }
    }

    // Metodo que pone las fotos en las aulas y los horarios en las materias
    private void restaurar(LinkedList<boolean[][]> ocupado, LinkedList<String[][]> reservas, LinkedList<Horario> horarios) {
        for (int i = 0; i < aulas.size(); i++) {
            aulas.get(i).restaurar(ocupado.get(i), reservas.get(i));
        }
        for (int i = 0; i < materias.size(); i++) {
            materias.get(i).setHorario(horarios.get(i));
        }
    }

    @Override
    public String deshacer() {
        restaurar(ocupadoAntes, reservasAntes, horariosAntes);
        return "Operacion deshecha: " + getDescripcion();
    }

    @Override
    public String rehacer() {
        restaurar(ocupadoDespues, reservasDespues, horariosDespues);
        return "Operacion rehecha: " + getDescripcion();
    }
}
