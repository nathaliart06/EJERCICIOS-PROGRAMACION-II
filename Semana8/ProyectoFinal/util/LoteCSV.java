package Semana8.ProyectoFinal.util;

import Semana8.ProyectoFinal.estructuras.Historial;
import Semana8.ProyectoFinal.estructuras.Operacion;
import Semana8.ProyectoFinal.estructuras.OperacionLote;
import Semana8.ProyectoFinal.excepciones.ArchivoInvalidoException;
import Semana8.ProyectoFinal.excepciones.CupoLlenoException;
import Semana8.ProyectoFinal.modelo.Estudiante;
import Semana8.ProyectoFinal.modelo.Materia;

import java.io.*;
import java.util.*;

/**
 * Procesamiento por lotes: lee el archivo CSV, encola cada linea (idEstudiante,codigoMateria)
 * y las atiende en orden (FIFO). Si la materia esta llena el estudiante pasa a la cola de espera
 * (cuenta como fallida, con el motivo). Todo el lote queda como UNA operacion para deshacer.
 */
public class LoteCSV {

    /** Metodo que lee el archivo y encola las solicitudes en el mismo orden del archivo */
    public static Queue<String> leer(String ruta) throws ArchivoInvalidoException {
        if (ruta == null || !ruta.trim().toLowerCase().endsWith(".csv")) {
            throw new ArchivoInvalidoException("El archivo debe ser .csv: " + ruta);
        }
        File archivo = new File(ruta.trim());
        if (!archivo.isFile()) {
            throw new ArchivoInvalidoException("No existe el archivo: " + archivo.getAbsolutePath());
        }
        Queue<String> cola = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                // se saltan lineas vacias, comentarios y el encabezado
                if (!linea.isEmpty() && !linea.startsWith("#") && !linea.toLowerCase().startsWith("idestudiante")) {
                    cola.add(linea);
                }
            }
        } catch (IOException e) {
            throw new ArchivoInvalidoException("No se pudo leer el archivo: " + e.getMessage());
        }
        if (cola.isEmpty()) {
            throw new ArchivoInvalidoException("El archivo no tiene solicitudes: " + ruta);
        }
        return cola;
    }

    /** Metodo que procesa toda la cola y muestra el resumen. Retorna {exitosas, fallidas} */
    public static int[] procesar(Queue<String> cola, Map<String, Materia> materias, Map<String, Estudiante> indice,
                                 boolean mostrarCadaLinea) {
        LinkedList<Operacion> cambios = new LinkedList<>();
        LinkedList<String> fallidas = new LinkedList<>();
        int exitosas = 0;
        int total = cola.size();
        int numero = 0;

        while (!cola.isEmpty()) {
            String linea = cola.poll();
            numero++;
            String[] partes = linea.split(",");
            String motivo = null;

            if (partes.length != 2 || partes[0].isBlank() || partes[1].isBlank()) {
                motivo = "linea mal formada, se esperaba idEstudiante,codigoMateria";
            } else {
                String id = partes[0].trim();
                String codigo = partes[1].trim().toUpperCase();
                try {
                    cambios.add(Materia.inscribirSinHistorial(materias, indice, id, codigo));
                    exitosas++;
                } catch (CupoLlenoException e) {
                    // materia llena: pasa a la cola de espera
                    try {
                        cambios.add(Materia.agregarAEsperaSinHistorial(materias, indice, id, codigo));
                        motivo = "cupo lleno, agregado(a) a la cola de espera (posicion "
                                + materias.get(codigo).posicionEnEspera(id) + ")";
                    } catch (Exception e2) {
                        motivo = e2.getMessage();
                    }
                } catch (IllegalArgumentException e) {
                    motivo = e.getMessage();
                } catch (Exception e) {
                    motivo = e.getClass().getSimpleName() + ": " + e.getMessage();
                }
            }

            if (motivo == null) {
                if (mostrarCadaLinea) {
                    System.out.println("[" + numero + "/" + total + "] " + linea.replace(",", " -> ") + " -> Exitosa");
                }
            } else {
                fallidas.add("solicitud " + numero + " (" + linea + "): " + motivo);
                if (mostrarCadaLinea) {
                    System.out.println("[" + numero + "/" + total + "] " + linea.replace(",", " -> ") + " -> Fallida");
                }
            }
        }

        if (!cambios.isEmpty()) {
            Historial.registrar(new OperacionLote("Procesamiento por lotes de " + total + " solicitudes", cambios));
        }

        System.out.println("\n=== RESUMEN ===");
        System.out.println("Exitosas: " + exitosas);
        System.out.println("Fallidas: " + fallidas.size());
        if (!fallidas.isEmpty()) {
            System.out.println("\nDetalle de las fallidas:");
            for (String f : fallidas) {
                System.out.println("  - " + f);
            }
        }
        return new int[] { exitosas, fallidas.size() };
    }
}
