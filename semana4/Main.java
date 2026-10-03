package semana4;

public class Main {
    
    public static void main (String [] args) {


        //creacion de un objeto trabajador

        Trabajador objT1 = new Trabajador (1, "Nathalia", "ramos", 25, 1000);
        Trabajador objT2 = new Trabajador (2," Michel", "Anacona", 31, 1000 );

        System.out.println(objT1);
        System.out.println(objT2);
        System.out.println(objT1.getNombre());
       


    }
}
