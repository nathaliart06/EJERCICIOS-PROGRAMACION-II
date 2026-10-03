package semana4;

public class Trabajador {

    //Atibutos

    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private double salario;

    //constructor

    public Trabajador (int id, String nombre, String apellido, int edad, double salario){

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.salario = salario;
        
    }
       public int getId (){
        return id;
       }
       public String getNombre (){
        return nombre;
       }
       public String getApellido (){
        return apellido;
       }
       public int getEdad (){
        return edad;
       }
       public double getSalario (){
        return salario;
       }



     //Método toString

    @Override
    public String toString() {
        return "Trabajador{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", salario=" + salario + '}';
    }    

    //Metodo que permite calcular el total de los salarios de todos los trabajadores

    public double calcularsalarios (Trabajador[] t) {

      double sumaSalario = 0.0;
      for(int i = 0; i < t.length; i++){
            sumaSalario += t[i].getSalario();
          
    }


    return sumaSalario; 



  
   }
    public double promedioedades (Trabajador[] t) {

      double promedioedades = 0.0; 
      double sumaEdades = 0; 
      for(int i = 0; i < t.length; i++){
            sumaEdades += t[i].getEdad();
            
        }
        promedioedades = sumaEdades / t.length; 


    return promedioedades; 



  
   }
    

}