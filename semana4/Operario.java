package semana4;

public class Operario extends Trabajador{
    
    private int horas;
    
    //Constructor
    public Operario(int id, String nombre, String apellido, int edad, double salarioBase, int horas){
        super(id, nombre, apellido, edad, salarioBase);
        this.horas = horas;
    }
    
    public double pagar(){
        return getSalarioBase() * horas;
    }
}