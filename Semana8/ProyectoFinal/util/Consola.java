package Semana8.ProyectoFinal.util;

import java.util.*;

/**
 * Lectura de datos por teclado. Si el usuario escribe algo mal se le vuelve a preguntar,
 * asi el menu nunca se rompe por un error de digitacion.
 */
public class Consola {
    private static Scanner sc = new Scanner(System.in);

    /** Metodo que lee un texto obligatorio */
    public static String leerTexto(String mensaje) {
        while (true) {
            String texto = leerLinea(mensaje);
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("  Este dato es obligatorio");
        }
    }

    /** Metodo que lee un texto que puede quedar vacio */
    public static String leerOpcional(String mensaje) {
        return leerLinea(mensaje);
    }

    /** Metodo que lee un entero entre min y max */
    public static int leerEntero(String mensaje, int min, int max) {
        while (true) {
            String texto = leerLinea(mensaje);
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("  Debe ser un numero entre " + min + " y " + max);
            } catch (NumberFormatException e) {
                System.out.println("  '" + texto + "' no es un numero entero");
            }
        }
    }

    /** Metodo que lee un decimal entre min y max (acepta punto o coma) */
    public static double leerDecimal(String mensaje, double min, double max) {
        while (true) {
            String texto = leerLinea(mensaje).replace(',', '.');
            try {
                double valor = Double.parseDouble(texto);
                if (valor >= min && valor <= max) {
                    return valor;
                }
                System.out.println("  Debe ser un valor entre " + min + " y " + max);
            } catch (NumberFormatException e) {
                System.out.println("  '" + texto + "' no es un numero");
            }
        }
    }

    /** Metodo que pregunta s/n */
    public static boolean confirmar(String mensaje) {
        while (true) {
            String texto = leerLinea(mensaje).toLowerCase();
            if (texto.equals("s") || texto.equals("si")) {
                return true;
            }
            if (texto.equals("n") || texto.equals("no")) {
                return false;
            }
            System.out.println("  Responda s o n");
        }
    }

    public static void pausar() {
        leerLinea("\nPresione Enter para continuar...");
    }

    private static String leerLinea(String mensaje) {
        System.out.print(mensaje);
        if (!sc.hasNextLine()) {
            System.out.println("\nNo hay mas datos de entrada. Saliendo...");
            System.exit(0);
        }
        return sc.nextLine().trim();
    }
}
