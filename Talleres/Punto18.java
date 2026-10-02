package Talleres;

public class Punto18 {
    public static void main(String[] args) {
        OperacionesMatrices objM = new OperacionesMatrices();
        
        int[][] tabla = objM.tablaMultiplicar(10);
        System.out.println(objM.imprimirTablaMultiplicar(tabla));
    }
}