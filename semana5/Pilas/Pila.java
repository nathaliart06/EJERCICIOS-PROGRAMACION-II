package semana5.Pilas;

import java.util.*;

public class Pila {

    public static void main(String [] args){ 

        Stack<Integer> pila = new Stack<>();

        //agregar elementos de la pila 

        pila.push(5);
        pila.push(8);
        pila.push(10);
        pila.push(2);
        pila.push(20);
        pila.push(15);
        pila.push(1);

        System.out.println( " Tope de la pila "  + pila.peek());
        System.out.println( " Elementos de la pila "  + pila);

        
    }
    
}
