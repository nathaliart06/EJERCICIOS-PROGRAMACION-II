package Semana8.ProyectoFinal.util;

import Semana8.ProyectoFinal.estructuras.*;
import Semana8.ProyectoFinal.excepciones.*;
import Semana8.ProyectoFinal.modelo.*;

import java.io.File;
import java.util.*;

/**
 * Casos de prueba sin menu: reproducen los 7 ejemplos del enunciado y algunos casos extra.
 * Cada verificacion imprime [OK] o [FALLO] y al final el total.
 */
public class Pruebas {
    static int correctas = 0;
    static int fallidas = 0;

    // Estructuras de prueba (se reinician en cada ejemplo)
    static Estudiante[] estudiantes;
    static Map<String, Estudiante> indice;
    static Map<String, Profesor> profesores;
    static Map<String, Materia> materias;
    static TreeMap<String, Aula> aulas;
    static Facultad[] facultades;

    public static void main(String[] args) throws Exception {
        System.out.println("----------------- EJEMPLO 1: REGISTRO Y BUSQUEDA ----------------");
        reiniciar(false);
        Estudiante.registrar(estudiantes, indice, new Estudiante("2024001", "Ana Maria Gomez", "ana.gomez@universidad.edu", 3));
        Estudiante ana = Estudiante.buscar(indice, "2024001");
        System.out.println(ana.mostrarInformacion());
        verificar(ana.getNombre().equals("Ana Maria Gomez") && !ana.tieneNotas(), "Busqueda por ID en el HashMap");
        try {
            Estudiante.buscar(indice, "2024999");
            verificar(false, "Debe lanzar EstudianteNoEncontradoException");
        } catch (EstudianteNoEncontradoException e) {
            System.out.println("Error: EstudianteNoEncontradoException - " + e.getMessage());
            verificar(true, "ID inexistente lanza EstudianteNoEncontradoException");
        }
        try {
            Estudiante.registrar(estudiantes, indice, new Estudiante("2024001", "Otra", "otra@u.edu", 1));
            verificar(false, "ID repetido debe fallar");
        } catch (IllegalArgumentException e) {
            verificar(true, "ID repetido rechazado");
        }

        System.out.println("\n----------------- EJEMPLO 2: COLA DE ESPERA ----------------");
        reiniciar(false);
        Materia.crear(materias, new Materia("CALC101", "Calculo I", 3, 4, facultades[1]));
        String[] nombres = { "Juan Perez", "Maria Lopez", "Carlos Ruiz", "Laura Diaz", "Pablo Ortiz" };
        for (int i = 0; i < nombres.length; i++) {
            Estudiante.registrar(estudiantes, indice, new Estudiante(String.valueOf(i + 1), nombres[i], "e" + i + "@u.edu", 1));
        }
        for (int i = 1; i <= 5; i++) {
            String id = String.valueOf(i);
            try {
                int restantes = Materia.inscribir(materias, indice, id, "CALC101");
                System.out.println(i + ". " + Estudiante.nombreDe(indice, id) + " -> Cupo disponible (cupos restantes: " + restantes + ")");
            } catch (CupoLlenoException e) {
                Materia.agregarAEspera(materias, indice, id, "CALC101");
                System.out.println(i + ". " + Estudiante.nombreDe(indice, id) + " -> Materia llena. Agregada a COLA DE ESPERA");
            }
        }
        Materia calc = Materia.buscar(materias, "CALC101");
        verificar(calc.getCantidadEnEspera() == 2 && calc.posicionEnEspera("4") == 1, "Laura y Pablo en cola, Laura primero");
        String promovido = Materia.cancelar(materias, indice, "1", "CALC101");
        System.out.println("Cancelacion de Juan Perez -> Asignando cupo a " + Estudiante.nombreDe(indice, promovido));
        verificar("4".equals(promovido) && calc.estaInscrito("4"), "Al cancelar, el cupo pasa a Laura Diaz");
        Historial.deshacer();
        verificar(calc.estaInscrito("1") && calc.posicionEnEspera("4") == 1, "Deshacer: Juan vuelve y Laura regresa a la posicion 1");
        try {
            Materia.cancelar(materias, indice, "4", "CALC101"); // sale de la cola
            Materia.cancelar(materias, indice, "5", "CALC101");
            Materia.mostrarColaEspera(materias, indice, "CALC101");
            verificar(false, "Cola vacia debe lanzar excepcion");
        } catch (ColaDeEsperaVaciaException e) {
            verificar(true, "Cola vacia lanza ColaDeEsperaVaciaException");
        }

        System.out.println("\n----------------- EJEMPLO 3: DESHACER / REHACER ----------------");
        reiniciar(true);
        Materia calc2 = Materia.buscar(materias, "CALC102");
        Materia.inscribir(materias, indice, "2024010", "CALC102");
        verificar(calc2.estaInscrito("2024010"), "Inscripcion exitosa");
        System.out.println(Historial.deshacer());
        verificar(!calc2.estaInscrito("2024010"), "Deshacer: ya NO esta inscrito");
        System.out.println(Historial.rehacer());
        verificar(calc2.estaInscrito("2024010"), "Rehacer: inscrito otra vez");
        try {
            Historial.rehacer();
            verificar(false, "Rehacer sin nada debe fallar");
        } catch (PilaDeshacerVaciaException e) {
            System.out.println("Error: PilaDeshacerVaciaException - " + e.getMessage());
            verificar(true, "Rehacer sin nada lanza PilaDeshacerVaciaException");
        }
        Estudiante.registrarNota(indice, materias, "2024002", 1, "CALC101", 4.0);
        Estudiante.registrarNota(indice, materias, "2024002", 1, "CALC101", 2.0);
        Historial.deshacer();
        verificar(Estudiante.buscar(indice, "2024002").haAprobado("CALC101"), "Deshacer nota corregida devuelve el 4.0");

        System.out.println("\n----------------- EJEMPLO 4: RESERVA DE HORARIOS ----------------");
        reiniciar(false);
        Aula a101 = Aula.buscar(aulas, "101");
        Aula.reservarHorario(aulas, materias, "101", 1, 8, 2, "");
        verificar(!a101.consultarDisponibilidad(1, 8) && !a101.consultarDisponibilidad(1, 9), "Lunes 8:00 y 9:00 reservados");
        try {
            Aula.reservarHorario(aulas, materias, "101", 1, 9, 2, "");
            verificar(false, "Reserva conflictiva debe fallar");
        } catch (HorarioConflictivoException e) {
            System.out.println("Error: HorarioConflictivoException - " + e.getMessage());
            verificar(a101.consultarDisponibilidad(1, 10), "La reserva fallida no dejo horas ocupadas");
        }
        Materia.crear(materias, new Materia("ALG", "Algebra", 10, 3, facultades[1]));
        Aula.reservarHorario(aulas, materias, "101", 2, 8, 2, "ALG");
        Aula.reservarHorario(aulas, materias, "201", 3, 14, 2, "ALG"); // cambio de horario
        Materia alg = Materia.buscar(materias, "ALG");
        verificar(a101.consultarDisponibilidad(2, 8) && alg.getHorario().getAula().equals("201"), "Cambiar horario libera el anterior");
        Historial.deshacer();
        verificar(!a101.consultarDisponibilidad(2, 8) && alg.getHorario().getAula().equals("101"), "Deshacer cambio de horario");

        System.out.println("\n----------------- EJEMPLO 5: DIJKSTRA ----------------");
        Campus campus = Campus.crearCampus();
        String ruta = campus.rutaMasCorta(0, 3);
        System.out.println(ruta);
        verificar(ruta.contains("Ingenieria -> Cafeteria (150m) -> Rectoria (180m)") && ruta.contains("330 metros"),
                "Ingenieria -> Rectoria = 330 m por la Cafeteria");

        System.out.println("\n----------------- EJEMPLO 6: REPORTE ACADEMICO ----------------");
        reiniciar(true);
        ana = Estudiante.buscar(indice, "2024001");
        Reporte r1 = Reporte.academico(ana);
        System.out.print(r1.getContenido());
        verificar(Math.abs(ana.promedioSemestre(1) - 4.10) < 0.005 && Math.abs(ana.promedioSemestre(2) - 3.00) < 0.005,
                "Promedios por semestre 4.10 y 3.00");
        verificar(Math.abs(ana.promedioAcumulado() - 3.55) < 0.005, "Promedio acumulado 3.55");
        verificar(ana.contarAprobadas() == 4 && ana.contarReprobadas() == 2, "4 aprobadas y 2 reprobadas");
        Reporte r2 = Reporte.reprobadas(ana);
        NavegadorReportes.visitar(r1);
        NavegadorReportes.visitar(r2);
        NavegadorReportes.irAtras();
        verificar(NavegadorReportes.getActual() == r1, "Navegador: atras");
        NavegadorReportes.irAdelante();
        verificar(NavegadorReportes.getActual() == r2, "Navegador: adelante");

        System.out.println("\n----------------- EJEMPLO 7: PROCESAMIENTO POR LOTES ----------------");
        reiniciar(true);
        String archivo = new File("Semana8/ProyectoFinal/inscripciones.csv").isFile()
                ? "Semana8/ProyectoFinal/inscripciones.csv" : "inscripciones.csv";
        Queue<String> cola = LoteCSV.leer(archivo);
        System.out.println("Se encolaron " + cola.size() + " solicitudes.");
        verificar(cola.size() == 100, "100 solicitudes en la cola");
        int[] resultado = LoteCSV.procesar(cola, materias, indice, false);
        verificar(resultado[0] == 78 && resultado[1] == 22, "78 exitosas y 22 fallidas");
        Historial.deshacer();
        int total = 0;
        for (Materia m : materias.values()) {
            total += m.getCantidadInscritos() + m.getCantidadEnEspera();
        }
        verificar(total == 0, "Un solo deshacer revierte todo el lote");
        try {
            LoteCSV.leer("no_existe.csv");
            verificar(false, "Archivo inexistente debe fallar");
        } catch (ArchivoInvalidoException e) {
            verificar(true, "Archivo inexistente lanza ArchivoInvalidoException");
        }

        System.out.println("\n----------------- CASOS EXTRA ----------------");
        reiniciar(true);
        try {
            Materia.inscribir(materias, indice, "2024008", "PROG201"); // reprobo PROG101
            verificar(false, "Debe pedir PROG101");
        } catch (PreRequisitoNoAprobadoException e) {
            System.out.println("Error: PreRequisitoNoAprobadoException - " + e.getMessage());
            verificar(true, "Pre-requisito no aprobado");
        }
        try {
            Materia.inscribir(materias, indice, "2024007", "CALC101");
            Materia.inscribir(materias, indice, "2024007", "CALC102"); // se cruza el lunes
            verificar(false, "Debe detectar el cruce");
        } catch (HorarioConflictivoException e) {
            verificar(true, "Cruce de horario entre materias del estudiante");
        }
        try {
            Materia.agregarPrerequisito(materias, "CALC101", "CALC102");
            verificar(false, "Ciclo debe fallar");
        } catch (IllegalArgumentException e) {
            verificar(true, "Ciclo de pre-requisitos rechazado");
        }
        Materia.inscribir(materias, indice, "2024003", "CALC101");
        Materia.inscribir(materias, indice, "2024004", "CALC101");
        Materia.agregarAEspera(materias, indice, "2024005", "CALC101");
        calc = Materia.buscar(materias, "CALC101");
        Estudiante.eliminar(estudiantes, indice, materias, "2024003");
        verificar(!indice.containsKey("2024003") && estudiantes[2] == null && calc.estaInscrito("2024005"),
                "Eliminar: sale del arreglo y del mapa, y su cupo pasa al primero de la cola");
        Historial.deshacer();
        verificar(indice.containsKey("2024003") && estudiantes[2] != null && calc.estaInscrito("2024003")
                && calc.posicionEnEspera("2024005") == 1, "Deshacer eliminar: vuelve con su inscripcion y la cola queda igual");

        System.out.println("\n=================================================");
        System.out.println("RESULTADO: " + correctas + " correctas, " + fallidas + " fallidas");
        System.out.println("=================================================");
    }

    // Metodo que crea estructuras nuevas (y si se pide, carga los datos de prueba)
    static void reiniciar(boolean conDatos) {
        estudiantes = new Estudiante[Estudiante.CAPACIDAD];
        indice = new HashMap<>();
        profesores = new HashMap<>();
        materias = new TreeMap<>();
        aulas = Aula.crearAulas();
        facultades = Facultad.crearFacultades();
        Historial.limpiar();
        NavegadorReportes.limpiar();
        if (conDatos) {
            DatosDemo.cargar(estudiantes, indice, profesores, materias, aulas, facultades);
        }
    }

    // Metodo que imprime el resultado de una verificacion
    static void verificar(boolean condicion, String descripcion) {
        if (condicion) {
            correctas++;
            System.out.println("  [OK] " + descripcion);
        } else {
            fallidas++;
            System.out.println("  [FALLO] " + descripcion);
        }
    }
}
