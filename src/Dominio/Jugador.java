package Dominio;

 public class Jugador {
    private int ci;
    private String nombre;
    private String apellido;
    private String puesto;
    private int dorsal;
    private int edad;
     private int equipo;

     public int getEquipo() {
         return equipo;
     }

     public void setEquipo(int equipo) {
         this.equipo = equipo;
     }


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

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    public Jugador(){}
    public Jugador(int ci, String nombre, String apellido, String puesto, int dorsal, int edad, int equipo) {
        this.ci = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.puesto = puesto;
        this.dorsal = dorsal;
        this.edad = edad;
        this.equipo = equipo;
    }

     public String toString1() {
         return ci +" - "+ nombre +" " + apellido +
                 " (" + puesto +") " + edad +" años";
     }

    @Override
    public String toString() {
        return dorsal+"- "+nombre+" "+ apellido;
    }
}

//  Equipos (2), donde cada uno deberá poder ingresar: 11 jugadores titulares (nombre, apellido, puesto, numero, edad)
//        5 jugadores suplentes (nombre, apellido, puesto, numero, edad)


