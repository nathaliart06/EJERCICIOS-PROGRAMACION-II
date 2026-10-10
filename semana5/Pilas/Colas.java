package semana5.Pilas;


import java.util.*;

public class Colas {

    public static void main(String[] args) {
        
        //creacion de la cola

        Queue<String> cola = new LinkedList<>();

        // Agregar los elementos de la cola

        cola.add( " Nathalia ");
        cola.add(" Jeison ");
        cola.add(" Michel ");
        cola.add(" juan manuel ");
        cola.add(" sebastian" );

        // Mostrar los elementos de la cola 
        
        System.out.println(cola);

        // Mostrar el primero de la cola

        System.out.println( cola.peek()); // Nathalia
        System.out.println( cola.element()); // Nathalia

        // Tamaño de la cola inicial
        System.out.println( " Tamaño de la cola: "  + cola.size());

        //Eliminar dos elementos de la cola

        cola.remove(); //nathalia   
        cola.poll(); // jeison
        
        // Muestra la cola con los elementos eliminados 

        System.out.println(cola); // michel, juan manuel, sebastian

          // Tamaño de la cola final
        System.out.println( " Tamaño de la cola: "  + cola.size());


        //Validar si la cola esta vacia 

        System.out.println( " La cola esta vacia " + cola.isEmpty()); // false

        //validar si esta dentro de la cola

        System.out.println(  cola.contains( " Michel ")); // true
        System.out.println(  cola.contains( " Nathalia ")); // false










    }
    
}
