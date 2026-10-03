package semana4;

public class Vendedor extends Trabajador{
    
    private double comision;
    
    //Constructor
    public Vendedor(int id, String nombre, String apellido, int edad, double salarioBase, double comision){
        super(id, nombre, apellido, edad, salarioBase);
        this.comision = comision;
    }
    
    public double pagar(){
        return getSalarioBase() * ( 1 + (comision / 100)) ;
    }
}

