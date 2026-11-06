# Proyecto Final - Planificacion Academica

Sistema de gestion universitaria por consola en Java 17 (Estructuras de Datos 2026-2).

## Ejecutar (desde la raiz del repositorio)

En VS Code: abrir el repositorio y darle *Run* a `Semana8/ProyectoFinal/PlanificacionAcademica.java`.

Por consola:
```
javac -d out $(find Semana8 -name "*.java")
java -cp out Semana8.ProyectoFinal.PlanificacionAcademica
java -cp out Semana8.ProyectoFinal.util.Pruebas
```

## Carpetas

```
Semana8/ProyectoFinal/
├── PlanificacionAcademica.java   main con el menu
├── inscripciones.csv             datos para el procesamiento por lotes
├── modelo/                       clases del sistema (Estudiante, Materia, Aula, Campus...)
├── estructuras/                  pilas de deshacer/rehacer y navegacion de reportes
├── excepciones/                  las 7 excepciones del enunciado
├── util/                         lectura de teclado, CSV, datos de prueba y pruebas
└── documentacion/                README, PDF y los diagramas de clases y de flujo
```

## Documentacion

- `Documentacion_ProyectoFinal.pdf`: como ejecutar, manual de usuario, justificacion de
  estructuras de datos, diagrama de clases y diagrama de flujo.
- `diagrama_clases.png`: diagrama de clases UML.
- `diagrama_flujo.png`: diagrama de flujo del programa, de la inscripcion y de deshacer.
