package semana4;

public class Trabajador {

    // Atibutos

    private int id;
    private String nombre;
    private String apellido;
    private int edad;
    private double salarioBase;

    // constructor

    public Trabajador(int id, String nombre, String apellido, int edad, double salarioBase) {

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.salarioBase = salarioBase;

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getEdad() {
        return edad;
    }


    public double getSalarioBase(){
        return salarioBase;
    }

    // Método toString

    @Override
    public String toString() {
        return "Trabajador{" + "id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", edad=" + edad
                + ", salarioBase=" + salarioBase + '}';
    }

    // Metodo que permite calcular el total de los salarios de todos los
    // trabajadores

    public double calcularsalarios(Trabajador[] t) {

        double sumaSalario = 0.0;
        for (int i = 0; i < t.length; i++) {
            sumaSalario += t[i].getSalarioBase();

        }

        return sumaSalario;

    }

    public int sumaEdades(Trabajador[] t) {

        int sumaEdades = 0;
        for (int i = 0; i < t.length; i++) {
            sumaEdades += t[i].getEdad();

        }

        return sumaEdades;

    }

    public double promedioedades(Trabajador[] t) {

        double promedioedades = 0.0;
        double sumaEdades = 0;
        for (int i = 0; i < t.length; i++) {
            sumaEdades += t[i].getEdad();

        }
        promedioedades = sumaEdades / t.length;

        return promedioedades;

    }

}