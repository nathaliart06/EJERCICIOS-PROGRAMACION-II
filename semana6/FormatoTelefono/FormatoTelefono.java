package semana6.FormatoTelefono; 
import java.util.*;


public class FormatoTelefono {
    public static void main(String[] args) {
        System.out.println("----------------- CASO 1 ----------------");
        Telefono.mostrar(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 0)));
        System.out.println("----------------- CASO 2 ----------------");
        Telefono.mostrar(new ArrayList<>(Arrays.asList(3, 0, 0, 1, 2, 3, 4, 5, 6, 7)));
        System.out.println("----------------- CASO INVALIDO ----------------");
        Telefono.mostrar(new ArrayList<>(Arrays.asList(3, 0, 0, 1, 2)));
    }
}