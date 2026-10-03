package semana4;

public class Main {
    
    public static void main (String [] args) {


        //creacion de un objeto trabajador

        Trabajador objT1 = new Trabajador (1, "Nathalia", "ramos", 25, 1000);
        Trabajador objT2 = new Trabajador (2," Michel", "Anacona", 31, 1000 );
        Trabajador objT3 = new Trabajador (3," Jeison", "ñañez", 33, 1000 );

        System.out.println(objT1);
        System.out.println(objT2);
        System.out.println(objT1.getNombre());
       
     //Arreglo de objetos
        Trabajador[] t = new Trabajador[3];
        t[0] = objT1;
        t[1] = objT2;
        t[2] = objT3;
        
        //Sumar los salarios de los trabajadores
        double sumaSalario = 0;
        int sumaEdades = 0;
        for(int i = 0; i < t.length; i++){
            sumaSalario += t[i].getSalario();
            sumaEdades += t[i].getEdad();
        }
        System.out.println("Suma de los salarios es: " + sumaSalario);
        System.out.println("suma de edades: " + sumaEdades);
    }
}
