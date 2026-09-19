public class EjecutarMatrices {

    public static void main(String [] args) {

        //creacion del objeto para llamar los metodos de la clase
        //operacionesMatrices.java

        OperacionesMatrices objM = new OperacionesMatrices();
    
    //Llenar la matriz 

    int[] [] matriz = objM.llenarMatriz(6, 6, 1, 100) ; 

    // mostrar la matriz 

    System.out.print(objM.imprimirMatriz(matriz));
    
    }
}