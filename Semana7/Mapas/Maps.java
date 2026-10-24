package Semana7.Mapas;

import java.util.*;
 

public class Maps {

    public static void main(String[] args) {
        
        Map<Integer, String> estudiantes = new HashMap<>(); 

        estudiantes.put ( 123456, " Nathalia Ramos "); 
        estudiantes.put ( 1234567, " Michel anacona "); 
        estudiantes.put ( 12345678, " Jeison ñañez "); 

        System.out.println(estudiantes.size()); // 3
        System.out.println(estudiantes.isEmpty()); // false
        System.out.println(estudiantes);

        //{123456=Nathalia Ramos, 12345678=Jeison ñañez, 1234567=Michel anacona} 

        System.out.println(estudiantes.containsKey(1234567)); // True
        System.out.println(estudiantes.containsValue( " Michel anacona ")); 
        System.out.println(estudiantes.remove(12345678)); 

        //System.out.println(estudiantes.replace(1234567, "Michel")); 
        System.out.println(estudiantes.replace(1234567, "Michel")); 
        System.out.println(estudiantes);

            
        for (Integer key : estudiantes.keySet()) {
            System.out.println("Clave: " + key + " --> Valor: " + estudiantes.get(key));
        }
            //while(est.hasNext()){

            //Integer key = est.next();
            //System.out.print( " Clave: " + key + " Valor: " + estudiantes.get(key));
            // }  



    }
    
}
