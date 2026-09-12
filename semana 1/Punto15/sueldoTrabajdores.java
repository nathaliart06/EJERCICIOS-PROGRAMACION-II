package Punto15;

public class sueldoTrabajdores {
    public static void main(String[] args) {
     
        String[] nombres = {"Nathalia","Andrea", "Jeison", "Jesus", "Cristian"};
        double[] sueldos = {1300000.0, 2000000.0, 2700000.0, 2000000.0, 2000000.0};

        int totalPersonas = nombres.length;

        //  Sumar todos los sueldos
        double suma = 0;
        for (int i = 0; i < totalPersonas; i++) {
            suma = suma + sueldos[i];
        }

        //  Se calcula el promedio 
        double promedio = suma / Math.pow(totalPersonas, 1);

        //  Se guardan los nombres y se empiesa a contar 
        String listaNombres = "";
        int cuantos = 0;

        for (int i = 0; i < totalPersonas; i++) {
            if (sueldos[i] == promedio) {
                if (cuantos == 0) {
                    listaNombres = nombres[i];
                } else {
                    listaNombres = listaNombres + ", " + nombres[i];
                }
                cuantos = cuantos + 1;
            }
        }

      
        System.out.println("Sueldo promedio: "+ promedio );
        System.out.println("Personas con sueldo promedio son: " + listaNombres);
        System.out.println("personas con sueldo promedio: " + cuantos);
    }
}