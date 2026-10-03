package semana4;

public class Main {
    
    public static void main (String [] args) {


        //creacion de un objeto trabajador

        Trabajador objT1 = new Trabajador (1, " Nathalia ", " Ramos ", 25, 1000);
        Trabajador objT2 = new Trabajador (2," Michel ", " Anacona ", 31, 1000 );
        Trabajador objT3 = new Trabajador (3," Jeison ", " Ñañez ", 33, 1000 );

        System.out.println(objT1);
        System.out.println(objT2);
        System.out.println(objT3);

        System.out.println(objT1.getNombre());
       
     //Arreglo de objetos
        Trabajador[] t = new Trabajador[3];
        t[0] = objT1;
        t[1] = objT2;
        t[2] = objT3;
        
        //Sumar los salarios de los trabajadores y edades
        double totalSalarios = objT1.calcularsalarios(t);
        int sumaEdades = objT1.sumaEdades(t);
        double promedioEdades = objT1.promedioedades(t);

      
        System.out.println("Suma de los salarios es: " + totalSalarios);
        System.out.println("suma de edades: " + sumaEdades);
        System.out.println("promedio de edades de los trabajadores es:" + promedioEdades); 

        // creacion de los objetos operarios y vendedor

        Trabajador objOperario1 = new Operario (101, " Nathalia ", " Ramos ", 25, 1000, 20 );
        Trabajador objVendedor1 = new Vendedor(256," Michel ", " Anacona ", 31, 1000, 20);
        Trabajador objVendedor2 = new Vendedor (306," Jeison ", " Ñañez ", 33, 1000, 15 );

        //System.out.println("pago total: " + objOperario1.pagar());
        //System.out.println("pago total: " + objVendedor1.pagar());
        
        //Creación de un nuevo arreglo de trabajadores
        Trabajador[] e = new Trabajador[3];
        e[0] = objOperario1;
        e[1] = objVendedor1;
        e[2] = objVendedor2;
        
        for(int i = 0; i < e.length; i++){
            System.out.println("Salario mes: " + e[i].pagar());
        }
    }
}