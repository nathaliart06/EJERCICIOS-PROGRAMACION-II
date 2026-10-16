public class Ejecutar {
    public static void main(String[] args) {
        
        //Creación del objeto para llamar los métodos de la clase
        //OperacionesMatrices.java
        OperacionesMAtrices objM = new OperacionesMAtrices();

        //llenar la matriz
        int[][] matriz = objM.llenarMAtriz(3, 3, 1, 9);
        
        //mostrar la matriz
        System.out.println(objM.imprimirMatriz(matriz));
        
        //suma filas y columnas
        System.out.println(objM.SumaFilasColumnas(matriz));
        
    }
    
}