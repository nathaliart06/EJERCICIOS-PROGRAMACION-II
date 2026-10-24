package Semana7.Mapas;

import java.util.*;
 

public class Maps {

    public static void main(String[] args) {
        
        Map<Integer, String> estudiantes = new HashMap<>(); 

        estudiantes.put ( 123456, " Nathalia Ramos "); 
        estudiantes.put ( 1234567, " Michel anacona "); 
        estudiantes.put ( 12345678, " Jeison ñañez "); 

        System.err.println(estudiantes.size()); // 3
        System.err.println(estudiantes.isEmpty()); // false
        System.err.println(estudiantes);

        //{123456=Nathalia Ramos, 12345678=Jeison ñañez, 1234567=Michel anacona} 

        System.err.println(estudiantes.containsKey(1234567)); // True
        System.err.println(estudiantes.containsValue( " Michel anacona ")); 
        System.err.println(estudiantes.remove(12345678)); 

        //System.err.println(estudiantes.replace(1234567, "Michel")); 
        System.err.println(estudiantes.replace(1234567, "Michel")); 
        System.err.println(estudiantes);

            
        for (Integer key : estudiantes.keySet()) {
            System.out.println("Clave: " + key + " --> Valor: " + estudiantes.get(key));
        }
            //while(est.hasNext()){

            //Integer key = est.next();
            //System.out.print( " Clave: " + key + " Valor: " + estudiantes.get(key));
            // }  



    }
    
}
