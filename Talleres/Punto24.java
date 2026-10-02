package Talleres;

public class Punto24 {
    public static void main(String[] args) {
        OperacionesMatrices objM = new OperacionesMatrices();
        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        // filas: meses (12), columnas: arroz, avena, cebada, trigo (4)
        int[][] produccion = objM.llenarMAtriz(12, 4, 50, 500);
        System.out.println("Produccion (Arroz | Avena | Cebada | Trigo):");
        System.out.println(objM.imprimirMatriz(produccion));

        // total cosechado en cada mes
        int[] totalMes = objM.totalesPorFila(produccion);
        for (int i = 0; i < totalMes.length; i++) {
            System.out.println(meses[i] + ": " + totalMes[i] + " toneladas");
        }

        // a. promedio anual
        double promedio = objM.promedio(totalMes);
        System.out.printf("%na. Promedio anual: %.2f toneladas por mes%n", promedio);

        // b. meses por encima del promedio
        System.out.println("b. Meses con cosecha superior al promedio: " + objM.contarMayores(totalMes, promedio));

        // c. meses por debajo del promedio
        System.out.println("c. Meses con cosecha inferior al promedio: " + objM.contarMenores(totalMes, promedio));

        // d. mes con mayor produccion
        int mesMayor = objM.posicionMayor(totalMes);
        System.out.println("d. Mes con mayor produccion: " + meses[mesMayor] + " con " + totalMes[mesMayor] + " toneladas");
    }
}