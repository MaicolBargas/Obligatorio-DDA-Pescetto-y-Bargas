package Dominio;

public class Tecnico {
    private int ci;
    private String nombre;
    private String apellido;
    private int edad;

    public Tecnico(int ci, String nombre, String apellido, int edad) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
    }
public Tecnico(){}
    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String toString1() {
        return ci +" - " +nombre +" "+apellido;
    }

    @Override
    public String toString() {
        return  nombre +" "+apellido;
    }

}
