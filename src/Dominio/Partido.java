package Dominio;

import java.util.List;

public class Partido {
    private int id;
    private Equipo local;
    private Equipo visitante;
    private String estadio;
    private String fecha;
    private String hora;
    private String clima;
    private List<Arbitro> ternaArbitral;

    public List<Arbitro> getTernaArbitral() {
        return ternaArbitral;
    }

    public void setTernaArbitral(List<Arbitro> ternaArbitral) {
        this.ternaArbitral = ternaArbitral;
    }



    /*public String toString1() {
        return local + " vs " + visitante +
                "\nEstadio: " + estadio + "\nEl dia " +
                fecha + " a las " + hora +" con clima "+  clima;

    }*/

    @Override
    public String toString() {
        return local + " vs " + visitante +
                "\nEstadio: " + estadio + "\nEl dia " +
                fecha + " a las " + hora +" con clima "+  clima;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Equipo getLocal() {
        return local;
    }

    public void setLocal(Equipo local) {
        this.local = local;
    }

    public Equipo getVisitante() {
        return visitante;
    }

    public void setVisitante(Equipo visitante) {
        this.visitante = visitante;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public Partido(int id, Equipo local, Equipo visitante, String estadio, String fecha, String hora, String clima) {
        this.id = id;
        this.local = local;
        this.visitante = visitante;
        this.estadio = estadio;
        this.fecha = fecha;
        this.hora = hora;
        this.clima = clima;
        this.ternaArbitral = ternaArbitral;
    }

    public Partido( Equipo local, Equipo visitante, String estadio, String fecha, String hora, String clima) {
        this.local = local;
        this.visitante = visitante;
        this.estadio = estadio;
        this.fecha = fecha;
        this.hora = hora;
        this.clima = clima;
        this.ternaArbitral = ternaArbitral;
    }
    public Partido(){};
}
