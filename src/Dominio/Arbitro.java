package Dominio;

public class Arbitro {
    private int ci;
    private String nombre;
    private String apellido;
    private String puesto;

    public Arbitro(int ci, String nombre, String apellido, String puesto) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
    }

    public Arbitro(){};
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

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }


    @Override
    public String toString() {
        return "Arbitro{" +
                "ci=" + ci +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", puesto='" + puesto + '\'' +
                '}';
    }
}

//3 árbitros (nombre, apellido, puesto)