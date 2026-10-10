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


       
       
        System.out.println( " Tope de la pila: "  + pila.peek());

        // imprime la pila     
        System.out.println( " Elementos de la pila: "  + pila);

        // Tamaño de la pila inicial
        System.out.println( " Tamaño de la pila: "  + pila.size());
     
        // para saber en que posicion esta el numero que estoy buscando 
        System.out.println( " post: " + pila.search(2));
       
        // Eliminar dos elementos de la pila 

        pila.pop(); // 1
        pila.pop(); // 15

        //imprime la pila con los elementos eliminados - de abajo hacia arriba 
        System.out.println( " Elementos de la pila: "  + pila);

        // Tamaño de la pila final 
        System.out.println( " Tamaño de la pila: "  + pila.size());
     

        
    }
    
}
