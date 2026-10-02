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

     //Método toString

    @Override
    public String toString() {
        return "Trabajador{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad + ", salario=" + salario + '}';
    }    



}
