//PROYECTO FINAL - PLANIFICACION ACADEMICA
//Sistema de Gestion Academica y Planificacion de Rutas de Aprendizaje
//Debe implementar un sistema de gestion universitaria que permita administrar estudiantes,
//materias, horarios, pre-requisitos, desplazamiento dentro del campus y un sistema de
//deshacer/rehacer operaciones. Uso obligatorio de: matrices, arreglos estaticos, listas enlazadas,
//pilas, colas y mapas (HashMap y TreeMap).
//Funcionalidades:
//Gestion de estudiantes (arreglos + HashMap): registrar, buscar por ID, historial, eliminar (deshacible), listar.
//Gestion de materias (listas enlazadas + colas): crear, pre-requisitos, inscribir si cumple pre-requisitos,
//cola de espera si la materia esta llena, al liberar un cupo se asigna al primero de la cola.
//Horarios con matriz boolean[7][24]: reservar, liberar, consultar disponibilidad (deshacible).
//Rutas entre edificios con matriz de adyacencia y algoritmo de Dijkstra (minimo 5 edificios).
//Reportes con arreglo Double[10][20]: promedio por semestre, promedio acumulado, reprobadas,
//navegacion entre reportes con pila (atras/adelante).
//Deshacer/Rehacer con dos pilas: inscribir, cancelar, cambiar horario, registrar nota, eliminar estudiante.
//Procesamiento por lotes: leer un CSV (idEstudiante,codigoMateria), encolar y procesar en orden.
//Menu interactivo por consola con todas las funcionalidades.

package Semana8.ProyectoFinal;

import Semana8.ProyectoFinal.estructuras.*;
import Semana8.ProyectoFinal.excepciones.*;
import Semana8.ProyectoFinal.modelo.*;
import Semana8.ProyectoFinal.util.*;

import java.io.File;
import java.util.*;

public class PlanificacionAcademica {
    // Estructuras del sistema
    static Estudiante[] estudiantes = new Estudiante[Estudiante.CAPACIDAD]; // arreglo de objetos
    static Map<String, Estudiante> indiceEstudiantes = new HashMap<>();      // busqueda rapida por ID
    static Map<String, Profesor> profesores = new HashMap<>();
    static Map<String, Materia> materias = new TreeMap<>();                  // ordenadas por codigo
    static TreeMap<String, Aula> aulas = Aula.crearAulas();                  // ordenadas por nombre
    static Facultad[] facultades = Facultad.crearFacultades();               // arreglo fijo de 5
    static Campus campus = Campus.crearCampus();                             // matriz de adyacencia

    static final String LINEA = "============================================================";

    public static void main(String[] args) {
        System.out.println(LINEA);
        System.out.println("        PLANIFICACION ACADEMICA - SISTEMA UNIVERSITARIO");
        System.out.println(LINEA);
        if (Consola.confirmar("\nDesea cargar los datos de prueba? (s/n): ")) {
            DatosDemo.cargar(estudiantes, indiceEstudiantes, profesores, materias, aulas, facultades);
            System.out.println("Datos cargados: " + Estudiante.contar(estudiantes) + " estudiantes, " + materias.size()
                    + " materias, " + aulas.size() + " aulas, " + campus.getCantidad() + " edificios");
        }

        int opcion = 0;
        while (opcion != 22) {
            mostrarMenu();
            opcion = Consola.leerEntero("Seleccione una opcion: ", 1, 25);
            System.out.println();
            try {
                ejecutar(opcion);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                // excepciones propias: se muestra el nombre para saber que paso
                System.out.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            }
            if (opcion != 22) {
                Consola.pausar();
            }
        }
        System.out.println("Hasta pronto");
    }

    // Metodo que muestra el menu principal
    static void mostrarMenu() {
        System.out.println("\n" + LINEA);
        System.out.println("        PLANIFICACION ACADEMICA - SISTEMA UNIVERSITARIO");
        System.out.println(LINEA);
        System.out.println("\n=== GESTION DE ESTUDIANTES ===");
        System.out.println("1.  Registrar estudiante");
        System.out.println("2.  Buscar estudiante por ID");
        System.out.println("3.  Listar todos los estudiantes");
        System.out.println("4.  Eliminar estudiante");
        System.out.println("\n=== GESTION DE MATERIAS ===");
        System.out.println("5.  Crear materia");
        System.out.println("6.  Agregar pre-requisito");
        System.out.println("7.  Mostrar pre-requisitos");
        System.out.println("8.  Inscribir estudiante");
        System.out.println("9.  Cancelar inscripcion");
        System.out.println("10. Mostrar cola de espera");
        System.out.println("\n=== GESTION DE HORARIOS ===");
        System.out.println("11. Reservar horario en aula (o asignar/cambiar horario de una materia)");
        System.out.println("12. Liberar horario");
        System.out.println("13. Consultar disponibilidad");
        System.out.println("\n=== RUTAS ENTRE EDIFICIOS ===");
        System.out.println("14. Agregar conexion entre edificios");
        System.out.println("15. Calcular ruta mas corta");
        System.out.println("\n=== REPORTES ACADEMICOS ===");
        System.out.println("16. Registrar nota");
        System.out.println("17. Ver reporte academico");
        System.out.println("18. Navegador de reportes (atras/adelante)");
        System.out.println("\n=== SISTEMA DESHACER/REHACER ===");
        System.out.println("19. Deshacer ultima operacion");
        System.out.println("20. Rehacer ultima operacion");
        System.out.println("\n=== PROCESAMIENTO POR LOTES ===");
        System.out.println("21. Procesar archivo CSV");
        System.out.println("\n=== CONSULTAS ===");
        System.out.println("23. Listar materias");
        System.out.println("24. Listar aulas");
        System.out.println("25. Ver estudiantes y profesores (polimorfismo)");
        System.out.println("\n=== SALIR ===");
        System.out.println("22. Salir\n");
    }

    // Metodo que ejecuta la opcion elegida
    static void ejecutar(int opcion) throws Exception {
        switch (opcion) {
            case 1 -> registrarEstudiante();
            case 2 -> buscarEstudiante();
            case 3 -> Estudiante.listar(estudiantes);
            case 4 -> eliminarEstudiante();
            case 5 -> crearMateria();
            case 6 -> agregarPrerequisito();
            case 7 -> Materia.mostrarPrerequisitos(materias, Consola.leerTexto("Codigo de la materia: "));
            case 8 -> inscribir();
            case 9 -> cancelar();
            case 10 -> mostrarColaEspera();
            case 11 -> reservarHorario();
            case 12 -> liberarHorario();
            case 13 -> consultarDisponibilidad();
            case 14 -> agregarConexion();
            case 15 -> calcularRuta();
            case 16 -> registrarNota();
            case 17 -> verReporte();
            case 18 -> navegarReportes();
            case 19 -> deshacer();
            case 20 -> rehacer();
            case 21 -> procesarCSV();
            case 22 -> System.out.println("Saliendo del sistema...");
            case 23 -> Materia.listar(materias);
            case 24 -> listarAulas();
            case 25 -> verPersonas();
        }
    }

    // ---------------- ESTUDIANTES ----------------

    static void registrarEstudiante() throws Exception {
        System.out.println("--- REGISTRO DE ESTUDIANTE ---");
        String id = Consola.leerTexto("ID: ");
        if (indiceEstudiantes.containsKey(id)) {
            System.out.println("Ya existe un estudiante con ID " + id + ". Use la opcion 2 para consultarlo");
            return;
        }
        String nombre = Consola.leerTexto("Nombre: ");
        String email = Consola.leerTexto("Email: ");
        while (!Persona.emailValido(email)) {
            System.out.println("  Formato invalido. Ejemplo: nombre@universidad.edu");
            email = Consola.leerTexto("Email: ");
        }
        int semestre = Consola.leerEntero("Semestre actual: ", 1, Estudiante.SEMESTRES);
        Estudiante.registrar(estudiantes, indiceEstudiantes, new Estudiante(id, nombre, email, semestre));
        System.out.println("\nEstudiante registrado exitosamente.");
    }

    static void buscarEstudiante() throws Exception {
        System.out.println("--- BUSCAR ESTUDIANTE ---");
        Estudiante e = Estudiante.buscar(indiceEstudiantes, Consola.leerTexto("ID: "));
        System.out.println("\nResultado encontrado:");
        System.out.println(e.mostrarInformacion());
        System.out.println("  Materias actuales:");
        Materia.mostrarMateriasDe(materias, e.getId());
    }

    static void eliminarEstudiante() throws Exception {
        System.out.println("--- ELIMINAR ESTUDIANTE ---");
        Estudiante e = Estudiante.buscar(indiceEstudiantes, Consola.leerTexto("ID: "));
        System.out.println(e.mostrarInformacion());
        if (!Consola.confirmar("Confirma la baja definitiva? (s/n): ")) {
            System.out.println("Operacion cancelada");
            return;
        }
        Estudiante.eliminar(estudiantes, indiceEstudiantes, materias, e.getId());
        System.out.println("Estudiante eliminado. Si liberaba cupos, pasaron al primero de cada cola de espera.");
        System.out.println("(Se puede revertir con la opcion 19 - Deshacer)");
    }

    // ---------------- MATERIAS ----------------

    static void crearMateria() throws Exception {
        System.out.println("--- CREAR MATERIA ---");
        String codigo = Consola.leerTexto("Codigo: ");
        String nombre = Consola.leerTexto("Nombre: ");
        int cupos = Consola.leerEntero("Cupos maximos: ", 1, 500);
        int creditos = Consola.leerEntero("Creditos: ", 1, 10);
        System.out.println("Facultades:");
        Facultad.listar(facultades);
        int f = Consola.leerEntero("Facultad (numero): ", 0, facultades.length - 1);
        Materia m = new Materia(codigo, nombre, cupos, creditos, facultades[f]);
        Materia.crear(materias, m);
        String idProfesor = Consola.leerOpcional("ID del profesor (Enter para dejarlo sin asignar): ");
        if (!idProfesor.isEmpty()) {
            try {
                m.setProfesor(Profesor.buscar(profesores, idProfesor));
            } catch (IllegalArgumentException e) {
                System.out.println("Aviso: " + e.getMessage() + ". La materia queda sin profesor");
            }
        }
        System.out.println("\nMateria creada: " + m);
        System.out.println("Use la opcion 11 para darle horario y la 6 para sus pre-requisitos");
    }

    static void agregarPrerequisito() throws Exception {
        System.out.println("--- AGREGAR PRE-REQUISITO ---");
        String codigo = Consola.leerTexto("Codigo de la materia: ");
        String pre = Consola.leerTexto("Codigo del pre-requisito: ");
        Materia.agregarPrerequisito(materias, codigo, pre);
        System.out.println("Pre-requisito agregado. " + codigo.toUpperCase() + " ahora pide: "
                + Materia.buscar(materias, codigo).getPrerequisitos());
    }

    static void inscribir() throws Exception {
        System.out.println("--- INSCRIBIR ESTUDIANTE ---");
        String id = Consola.leerTexto("ID del estudiante: ");
        String codigo = Consola.leerTexto("Codigo de la materia: ");
        Estudiante e = Estudiante.buscar(indiceEstudiantes, id);
        try {
            int restantes = Materia.inscribir(materias, indiceEstudiantes, id, codigo);
            System.out.println(e.getNombre() + " -> Cupo disponible. Inscripcion exitosa (cupos restantes: " + restantes + ")");
        } catch (CupoLlenoException ex) {
            // materia llena: el estudiante pasa a la cola de espera
            int posicion = Materia.agregarAEspera(materias, indiceEstudiantes, id, codigo);
            System.out.println(e.getNombre() + " -> Materia llena. Agregado(a) a COLA DE ESPERA (posicion " + posicion + ")");
        } catch (PreRequisitoNoAprobadoException ex) {
            System.out.println("Error: PreRequisitoNoAprobadoException - " + ex.getMessage());
            System.out.println("Estado de los pre-requisitos:");
            for (String pre : Materia.buscar(materias, codigo).getPrerequisitos()) {
                System.out.println("  " + pre + ": " + (e.haAprobado(pre) ? "APROBADO" : "PENDIENTE"));
            }
        } catch (HorarioConflictivoException ex) {
            System.out.println("Error: HorarioConflictivoException - " + ex.getMessage());
            System.out.println("Sugerencia: cancele la materia que se cruza (opcion 9) o cambie el horario (opcion 11)");
        }
    }

    static void cancelar() throws Exception {
        System.out.println("--- CANCELAR INSCRIPCION ---");
        String id = Consola.leerTexto("ID del estudiante: ");
        String codigo = Consola.leerTexto("Codigo de la materia: ");
        boolean estabaEnEspera = Materia.buscar(materias, codigo).estaEnEspera(id);
        String promovido = Materia.cancelar(materias, indiceEstudiantes, id, codigo);
        if (estabaEnEspera) {
            System.out.println("El estudiante salio de la cola de espera");
            return;
        }
        System.out.println("Cancelacion exitosa. Cupo liberado.");
        if (promovido != null) {
            System.out.println("Asignando cupo a " + Estudiante.nombreDe(indiceEstudiantes, promovido) + " (primer estudiante en cola)");
        }
    }

    static void mostrarColaEspera() throws Exception {
        System.out.println("--- COLA DE ESPERA ---");
        String codigo = Consola.leerTexto("Codigo de la materia: ");
        try {
            Materia.mostrarColaEspera(materias, indiceEstudiantes, codigo);
        } catch (ColaDeEsperaVaciaException e) {
            Materia m = Materia.buscar(materias, codigo);
            System.out.println(e.getMessage());
            System.out.println(m.tieneCupo()
                    ? "Quedan " + m.cuposDisponibles() + " cupos: puede inscribir directamente (opcion 8)"
                    : "La materia esta llena: el proximo que se inscriba entra a la cola");
        }
    }

    // ---------------- HORARIOS ----------------

    static int leerDia() {
        return Consola.leerEntero("Dia (0=Dom, 1=Lun, 2=Mar, 3=Mie, 4=Jue, 5=Vie, 6=Sab): ", 0, 6);
    }

    static void reservarHorario() throws Exception {
        System.out.println("--- RESERVAR HORARIO EN AULA ---");
        System.out.println("Aulas: " + aulas.keySet());
        Aula aula = Aula.buscar(aulas, Consola.leerTexto("Aula: "));
        int dia = leerDia();
        int hora = Consola.leerEntero("Hora (0-23): ", 0, 23);
        int duracion = Consola.leerEntero("Duracion: ", 1, 24 - hora);
        String materia = Consola.leerOpcional("Materia (Enter = reserva general): ");

        System.out.println("\nVerificando disponibilidad...");
        for (int h = hora; h < hora + duracion; h++) {
            System.out.println("  " + Horario.DIAS[dia] + " " + h + ":00 -> "
                    + (aula.consultarDisponibilidad(dia, h) ? "LIBRE" : "OCUPADO (" + aula.getReservadoPor(dia, h) + ")"));
        }
        try {
            Aula.reservarHorario(aulas, materias, aula.getCodigo(), dia, hora, duracion, materia);
            System.out.println("\nReserva exitosa.");
            if (!materia.isEmpty()) {
                System.out.println("Horario de " + materia.toUpperCase() + ": " + Materia.buscar(materias, materia).getHorario());
            }
        } catch (HorarioConflictivoException e) {
            System.out.println("\nError: HorarioConflictivoException - " + e.getMessage());
            System.out.println("Agenda del aula " + aula.getCodigo() + " el " + Horario.DIAS[dia] + ":");
            aula.mostrarDia(dia);
            System.out.println("Elija un bloque libre e intente de nuevo");
        }
    }

    static void liberarHorario() throws Exception {
        System.out.println("--- LIBERAR HORARIO ---");
        System.out.println("Aulas: " + aulas.keySet());
        String aula = Consola.leerTexto("Aula: ");
        int dia = leerDia();
        int hora = Consola.leerEntero("Hora (0-23): ", 0, 23);
        int duracion = Consola.leerEntero("Duracion: ", 1, 24 - hora);
        LinkedList<String> sinHorario = Aula.liberarHorario(aulas, materias, aula, dia, hora, duracion);
        System.out.println("Horario liberado.");
        if (!sinHorario.isEmpty()) {
            System.out.println("Materias que quedaron sin horario: " + sinHorario);
        }
    }

    static void consultarDisponibilidad() throws Exception {
        System.out.println("--- CONSULTAR DISPONIBILIDAD ---");
        System.out.println("Aulas: " + aulas.keySet());
        Aula aula = Aula.buscar(aulas, Consola.leerTexto("Aula: "));
        int dia = leerDia();
        int hora = Consola.leerEntero("Hora (0-23): ", 0, 23);
        System.out.println("\nAula " + aula.getCodigo() + ", " + Horario.DIAS[dia] + " " + hora + ":00 -> "
                + (aula.consultarDisponibilidad(dia, hora) ? "LIBRE" : "OCUPADO (" + aula.getReservadoPor(dia, hora) + ")"));
        System.out.println("Agenda del " + Horario.DIAS[dia] + ":");
        aula.mostrarDia(dia);
    }

    // ---------------- RUTAS ----------------

    static void agregarConexion() throws Exception {
        System.out.println("--- AGREGAR CONEXION ENTRE EDIFICIOS ---");
        if (Consola.confirmar("Desea registrar primero un edificio nuevo? (s/n): ")) {
            int numero = campus.agregarEdificio(Consola.leerTexto("Nombre del edificio: "));
            System.out.println("Edificio registrado con el numero " + numero);
        }
        campus.listarEdificios();
        int origen = Consola.leerEntero("Edificio origen: ", 0, campus.getCantidad() - 1);
        int destino = Consola.leerEntero("Edificio destino: ", 0, campus.getCantidad() - 1);
        int metros = Consola.leerEntero("Distancia en metros: ", 1, 100000);
        campus.agregarConexion(origen, destino, metros);
        System.out.println("\nConexion registrada: " + campus.getEdificio(origen) + " <-> " + campus.getEdificio(destino)
                + " (" + metros + " m)");
        System.out.println("\nMatriz de adyacencia (metros):");
        campus.mostrarMatriz();
    }

    static void calcularRuta() throws Exception {
        System.out.println("--- CALCULAR RUTA MAS CORTA ---\n");
        campus.listarEdificios();
        int origen = Consola.leerEntero("\nOrigen: ", 0, campus.getCantidad() - 1);
        int destino = Consola.leerEntero("Destino: ", 0, campus.getCantidad() - 1);
        String ruta = campus.rutaMasCorta(origen, destino);
        System.out.println("\nOrigen: " + campus.getEdificio(origen));
        System.out.println("Destino: " + campus.getEdificio(destino));
        System.out.println("\n--- RESULTADO ---");
        System.out.println(ruta);
    }

    // ---------------- REPORTES ----------------

    static void registrarNota() throws Exception {
        System.out.println("--- REGISTRAR NOTA ---");
        Estudiante e = Estudiante.buscar(indiceEstudiantes, Consola.leerTexto("ID del estudiante: "));
        int semestre = Consola.leerEntero("Semestre (1-10): ", 1, Estudiante.SEMESTRES);
        String codigo = Consola.leerTexto("Codigo de la materia: ");
        double nota = Consola.leerDecimal("Nota (0.0 - 5.0): ", 0.0, 5.0);
        Estudiante.registrarNota(indiceEstudiantes, materias, e.getId(), semestre, codigo, nota);
        System.out.println("Nota registrada: " + e.getNombre() + ", " + codigo.toUpperCase() + " = " + nota
                + " (semestre " + semestre + ") -> " + (nota >= Estudiante.NOTA_APROBATORIA ? "APROBADA" : "REPROBADA"));
    }

    static void verReporte() throws Exception {
        System.out.println("--- VER REPORTE ACADEMICO ---");
        Estudiante e = Estudiante.buscar(indiceEstudiantes, Consola.leerTexto("ID del estudiante: "));
        System.out.println("1. Reporte completo (notas por semestre y promedios)");
        System.out.println("2. Materias reprobadas");
        System.out.println("3. Promedio de un semestre");
        int tipo = Consola.leerEntero("Tipo de reporte: ", 1, 3);
        Reporte r;
        if (tipo == 1) {
            r = Reporte.academico(e);
        } else if (tipo == 2) {
            r = Reporte.reprobadas(e);
        } else {
            r = Reporte.promedioSemestre(e, Consola.leerEntero("Semestre (1-10): ", 1, Estudiante.SEMESTRES));
        }
        NavegadorReportes.visitar(r); // queda guardado para la navegacion
        System.out.println();
        System.out.print(r.getContenido());
        System.out.println("\n(Reporte guardado en el navegador, opcion 18)");
    }

    static void navegarReportes() {
        if (NavegadorReportes.getActual() == null) {
            System.out.println("Todavia no ha visto reportes. Genere uno con la opcion 17");
            return;
        }
        String opcion = "";
        while (!opcion.equals("S")) {
            System.out.println("\n=== NAVEGADOR DE REPORTES ===  [atras: " + NavegadorReportes.cantidadAtras()
                    + " | adelante: " + NavegadorReportes.cantidadAdelante() + "]");
            System.out.println("Viendo: " + NavegadorReportes.getActual().getTitulo() + "\n");
            System.out.print(NavegadorReportes.getActual().getContenido());
            opcion = Consola.leerTexto("\n[A] Atras   [D] Adelante   [S] Salir: ").toUpperCase();
            if (opcion.equals("A")) {
                NavegadorReportes.irAtras();
            } else if (opcion.equals("D")) {
                NavegadorReportes.irAdelante();
            }
        }
    }

    // ---------------- DESHACER / REHACER ----------------

    static void deshacer() throws Exception {
        System.out.println("--- DESHACER ---");
        System.out.println(Historial.deshacer());
        System.out.println("\nPila de deshacer (tope -> base):");
        Historial.mostrar();
    }

    static void rehacer() throws Exception {
        System.out.println("--- REHACER ---");
        System.out.println(Historial.rehacer());
    }

    // ---------------- PROCESAMIENTO POR LOTES ----------------

    static void procesarCSV() {
        System.out.println("--- PROCESAMIENTO MASIVO (BATCH) ---");
        // ruta por defecto: funciona ejecutando desde la raiz del repositorio o desde la carpeta del proyecto
        String porDefecto = new File("Semana8/ProyectoFinal/inscripciones.csv").isFile()
                ? "Semana8/ProyectoFinal/inscripciones.csv" : "inscripciones.csv";
        Queue<String> cola = null;
        String ruta = "";
        while (cola == null) {
            ruta = Consola.leerOpcional("Archivo (Enter = " + porDefecto + "): ");
            if (ruta.isEmpty()) {
                ruta = porDefecto;
            }
            try {
                cola = LoteCSV.leer(ruta);
            } catch (ArchivoInvalidoException e) {
                System.out.println("Error: ArchivoInvalidoException - " + e.getMessage());
                if (!Consola.confirmar("Desea intentar con otro archivo? (s/n): ")) {
                    return;
                }
            }
        }
        System.out.println("Archivo: " + ruta);
        System.out.println("\nSe encolaron " + cola.size() + " solicitudes.\n");
        System.out.println("Procesando cola...");
        LoteCSV.procesar(cola, materias, indiceEstudiantes, true);
        System.out.println("\n(Todo el lote se puede revertir con la opcion 19 - Deshacer)");
    }

    // ---------------- CONSULTAS ----------------

    static void listarAulas() {
        System.out.println("--- AULAS ---");
        System.out.println("1. Ordenadas por nombre (TreeMap)");
        System.out.println("2. Ordenadas por capacidad");
        if (Consola.leerEntero("Orden: ", 1, 2) == 1) {
            Aula.listarPorNombre(aulas);
        } else {
            Aula.listarPorCapacidad(aulas);
        }
    }

    // Polimorfismo: cada objeto ejecuta su propia version de mostrarInformacion()
    static void verPersonas() {
        System.out.println("--- ESTUDIANTES Y PROFESORES ---");
        Persona[] personas = new Persona[profesores.size() + Estudiante.contar(estudiantes)];
        int i = 0;
        for (Profesor p : profesores.values()) {
            personas[i++] = p;
        }
        for (int j = 0; j < estudiantes.length; j++) {
            if (estudiantes[j] != null) {
                personas[i++] = estudiantes[j];
            }
        }
        for (int k = 0; k < personas.length; k++) {
            System.out.println("[" + personas[k].getRol() + "]");
            System.out.println(personas[k].mostrarInformacion());
            System.out.println();
        }
    }
}
