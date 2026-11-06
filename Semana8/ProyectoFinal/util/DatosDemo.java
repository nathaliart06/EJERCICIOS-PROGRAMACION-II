package Semana8.ProyectoFinal.util;

import Semana8.ProyectoFinal.estructuras.Historial;
import Semana8.ProyectoFinal.modelo.*;

import java.util.*;

/**
 * Datos de prueba: profesores, materias con pre-requisitos y horarios, 40 estudiantes y notas.
 * Sirven para probar todo el menu (y grabar el video) sin tener que digitar nada.
 */
public class DatosDemo {

    /** Metodo que carga los datos y al final limpia el historial para que deshacer no borre la carga */
    public static void cargar(Estudiante[] estudiantes, Map<String, Estudiante> indice, Map<String, Profesor> profesores,
                              Map<String, Materia> materias, TreeMap<String, Aula> aulas, Facultad[] facultades) {
        try {
            Profesor.registrar(profesores, new Profesor("P001", "Carlos Rodriguez", "carlos.rodriguez@universidad.edu", "Matematicas", 6500000));
            Profesor.registrar(profesores, new Profesor("P002", "Laura Mendez", "laura.mendez@universidad.edu", "Fisica", 6200000));
            Profesor.registrar(profesores, new Profesor("P003", "Andres Torres", "andres.torres@universidad.edu", "Sistemas", 7000000));

            // codigo, nombre, cupos, creditos, facultad (0 = Ingenieria, 1 = Ciencias Basicas)
            Materia.crear(materias, new Materia("CALC101", "Calculo I", 3, 4, facultades[1]));
            Materia.crear(materias, new Materia("CALC102", "Calculo II", 30, 4, facultades[1]));
            Materia.crear(materias, new Materia("FIS101", "Fisica I", 25, 3, facultades[1]));
            Materia.crear(materias, new Materia("FIS102", "Fisica II", 25, 3, facultades[1]));
            Materia.crear(materias, new Materia("PROG101", "Programacion I", 30, 3, facultades[0]));
            Materia.crear(materias, new Materia("PROG201", "Programacion II", 2, 3, facultades[0]));
            Materia.crear(materias, new Materia("MATDIS", "Matematicas Discretas", 30, 3, facultades[1]));
            Materia.crear(materias, new Materia("BD101", "Bases de Datos", 20, 3, facultades[0]));

            String[][] asignaciones = { { "CALC101", "P001" }, { "CALC102", "P001" }, { "MATDIS", "P001" },
                    { "FIS101", "P002" }, { "FIS102", "P002" }, { "PROG101", "P003" }, { "PROG201", "P003" }, { "BD101", "P003" } };
            for (int i = 0; i < asignaciones.length; i++) {
                Materia.buscar(materias, asignaciones[i][0]).setProfesor(Profesor.buscar(profesores, asignaciones[i][1]));
            }

            Materia.agregarPrerequisito(materias, "CALC102", "CALC101");
            Materia.agregarPrerequisito(materias, "FIS102", "FIS101");
            Materia.agregarPrerequisito(materias, "FIS102", "CALC101");
            Materia.agregarPrerequisito(materias, "PROG201", "PROG101");
            Materia.agregarPrerequisito(materias, "BD101", "PROG101");

            // aula, dia (1 = Lunes), hora, duracion, materia
            Aula.reservarHorario(aulas, materias, "101", 1, 10, 2, "CALC101");
            Aula.reservarHorario(aulas, materias, "201", 1, 11, 2, "CALC102"); // se cruza con CALC101 a proposito
            Aula.reservarHorario(aulas, materias, "102", 2, 10, 2, "FIS101");
            Aula.reservarHorario(aulas, materias, "102", 4, 10, 2, "FIS102");
            Aula.reservarHorario(aulas, materias, "LAB-1", 3, 14, 2, "PROG101");
            Aula.reservarHorario(aulas, materias, "LAB-1", 5, 8, 3, "PROG201");
            Aula.reservarHorario(aulas, materias, "101", 4, 14, 2, "MATDIS");
            Aula.reservarHorario(aulas, materias, "LAB-1", 2, 16, 2, "BD101");

            Estudiante.registrar(estudiantes, indice, new Estudiante("2024001", "Ana Maria Gomez", "ana.gomez@universidad.edu", 3));
            Estudiante.registrar(estudiantes, indice, new Estudiante("2024002", "Juan Perez", "juan.perez@universidad.edu", 1));
            Estudiante.registrar(estudiantes, indice, new Estudiante("2024003", "Maria Lopez", "maria.lopez@universidad.edu", 1));
            Estudiante.registrar(estudiantes, indice, new Estudiante("2024004", "Carlos Ruiz", "carlos.ruiz@universidad.edu", 2));
            Estudiante.registrar(estudiantes, indice, new Estudiante("2024005", "Laura Diaz", "laura.diaz@universidad.edu", 1));
            Estudiante.registrar(estudiantes, indice, new Estudiante("2024006", "Pablo Ortiz", "pablo.ortiz@universidad.edu", 2));
            Estudiante.registrar(estudiantes, indice, new Estudiante("2024007", "Sofia Herrera", "sofia.herrera@universidad.edu", 4));
            Estudiante.registrar(estudiantes, indice, new Estudiante("2024008", "Diego Ramirez", "diego.ramirez@universidad.edu", 3));
            Estudiante.registrar(estudiantes, indice, new Estudiante("2024009", "Valentina Castro", "valentina.castro@universidad.edu", 2));
            Estudiante.registrar(estudiantes, indice, new Estudiante("2024010", "Mateo Vargas", "mateo.vargas@universidad.edu", 5));

            // 30 estudiantes de primer semestre (2024011 a 2024040) para el procesamiento por lotes
            String[] nombres = { "Andres", "Camila", "Santiago", "Isabella", "Sebastian", "Mariana", "Nicolas", "Gabriela",
                    "Samuel", "Daniela", "Alejandro", "Paula", "Felipe", "Juliana", "David", "Natalia", "Tomas", "Sara",
                    "Emiliano", "Luciana", "Martin", "Antonella", "Julian", "Manuela", "Esteban", "Salome", "Simon",
                    "Valeria", "Jeronimo", "Catalina" };
            String[] apellidos = { "Rojas", "Moreno", "Jimenez", "Suarez", "Torres", "Cardenas", "Rios", "Mejia",
                    "Ospina", "Gutierrez", "Salazar", "Castillo", "Pineda", "Arango", "Rincon", "Quintero", "Bedoya",
                    "Velasquez", "Cortes", "Zapata", "Munoz", "Guerrero", "Ortega", "Pardo", "Duarte", "Restrepo",
                    "Molina", "Acosta", "Mendoza", "Florez" };
            for (int i = 0; i < nombres.length; i++) {
                String email = (nombres[i] + "." + apellidos[i] + "@universidad.edu").toLowerCase();
                Estudiante.registrar(estudiantes, indice,
                        new Estudiante(String.valueOf(2024011 + i), nombres[i] + " " + apellidos[i], email, 1));
            }

            // Notas de Ana: las mismas del Ejemplo 6 del enunciado
            Object[][] notas = {
                    { "2024001", 1, "CALC101", 4.5 }, { "2024001", 1, "FIS101", 2.8 }, { "2024001", 1, "PROG101", 5.0 },
                    { "2024001", 2, "CALC102", 3.5 }, { "2024001", 2, "FIS102", 3.0 }, { "2024001", 2, "MATDIS", 2.5 },
                    { "2024007", 1, "CALC101", 3.8 }, { "2024007", 1, "FIS101", 3.2 }, { "2024007", 1, "PROG101", 4.0 },
                    { "2024007", 2, "MATDIS", 4.4 }, { "2024008", 1, "CALC101", 3.0 }, { "2024008", 1, "PROG101", 2.5 },
                    { "2024009", 1, "CALC101", 2.0 }, { "2024010", 1, "CALC101", 4.0 }, { "2024010", 1, "FIS101", 3.6 },
                    { "2024010", 1, "PROG101", 4.5 } };
            for (int i = 0; i < notas.length; i++) {
                Estudiante.registrarNota(indice, materias, (String) notas[i][0], (int) notas[i][1],
                        (String) notas[i][2], (double) notas[i][3]);
            }
        } catch (Exception e) {
            System.out.println("Error cargando los datos de prueba: " + e.getMessage());
        }
        Historial.limpiar();
    }
}
