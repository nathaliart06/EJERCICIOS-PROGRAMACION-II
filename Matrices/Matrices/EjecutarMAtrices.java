
public class EjecutarMAtrices {

    public static void main(String [] args) {

        //creacion del objeto para llamar los metodos de la clase
        //operacionesMatrices.java

        OperacionesMAtrices objM = new OperacionesMAtrices();
    
    //Llenar la matriz 

    int[] [] matriz = objM.llenarMAtriz(6, 6, 1, 100) ; 

    // mostrar la matriz 

    System.out.print(objM.imprimirMatriz(matriz));
    
    }
}

//ejecutar

