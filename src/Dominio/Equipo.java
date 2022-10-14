package Dominio;

import java.util.List;
import Dominio.Tecnico;

public class Equipo {
    private int id;
    private String nombre;
    private Tecnico dt;
    private List<Jugador> titulares;
    private List<Jugador> suplentes;

    public List<Jugador> getTitulares() {
        return titulares;
    }

    public void setTitulares(List<Jugador> titulares) {
        this.titulares = titulares;
    }

    public List<Jugador> getSuplentes() {
        return suplentes;
    }

    public void setSuplentes(List<Jugador> suplentes) {
        this.suplentes = suplentes;
    }



    public String toString1() {
        return  id + " - " +nombre+
                " DT=" + dt ;
    }

    @Override
    public String toString() {
        return  nombre ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tecnico getDt() {
        return dt;
    }

    public void setDt(Tecnico dt) {
        this.dt = dt;
    }

    public Equipo()
    {}

    public Equipo(int id,String nombre, Tecnico dt) {
        this.id = id;
        this.nombre = nombre;
        this.dt = dt;
    }

    public Equipo(String nombre, Tecnico dt) {
        this.nombre = nombre;
        this.dt = dt;
    }
}

