package Dominio;

public class Partido {
    private int id;
    private Equipo local;
    private Equipo visitante;
    private String estadio;
    private String fecha;
    private String hora;
    private String clima;

    @Override
    public String toString() {
        return "Partido{" +
                "id=" + id +
                ", local=" + local +
                ", visitante=" + visitante +
                ", estadio='" + estadio + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", clima='" + clima + '\'' +
                '}';
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
    }
}

//Partido, que deberá identificarse por Lugar (estadio), día, hora, clima, -minutos jugados- (al final del partido).