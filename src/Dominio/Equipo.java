package Dominio;

import java.util.List;

public class Equipo {
    private int id;
    private List<Jugador> titulares;
    private List<Jugador> suplentes;
    private Tecnico dt;

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", titulares=" + titulares +
                ", suplentes=" + suplentes +
                ", dt=" + dt +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Tecnico getDt() {
        return dt;
    }

    public void setDt(Tecnico dt) {
        this.dt = dt;
    }

    public Equipo(int id, List<Jugador> titulares, List<Jugador> suplentes, Tecnico dt) {
        this.id = id;
        this.titulares = titulares;
        this.suplentes = suplentes;
        this.dt = dt;
    }
}

