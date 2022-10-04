package Metodos;
import Dominio.Jugador;
import Persistencia.Conexion;
import java.sql.*;
import java.util.*;

public class MetodosJugador {
    public static void MostrarJugador(){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM jugador");
            ResultSet rs = ps.executeQuery();

            List<Jugador> listaJugadores = new ArrayList<Jugador>();
            Jugador unJugador = new Jugador();

            while (rs.next()){
                unJugador.setCi(rs.getInt(1));
                unJugador.setNombre(rs.getString(2));
                unJugador.setApellido(rs.getString(3));
                unJugador.setPuesto(rs.getString(4));
                unJugador.setDorsal(rs.getInt(5));
                unJugador.setEdad(rs.getInt(6));
                listaJugadores.add(unJugador);
                System.out.println(unJugador.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public static void MostrarJugadorPorClub(int pIdClub){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM jugador where idClub = ?");
            ps.setInt(1,pIdClub);
            ResultSet rs = ps.executeQuery();

            List<Jugador> listaJugadores = new ArrayList<Jugador>();

            Jugador unJugador = new Jugador();

            while (rs.next()){
                unJugador.setCi(rs.getInt(1));
                unJugador.setNombre(rs.getString(2));
                unJugador.setApellido(rs.getString(3));
                unJugador.setPuesto(rs.getString(4));
                unJugador.setDorsal(rs.getInt(5));
                unJugador.setEdad(rs.getInt(6));
                listaJugadores.add(unJugador);
                System.out.println(unJugador.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public static void AltaJugador(int equipo){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ci");
        int ci = scanner.nextInt();
        System.out.println("Nombre");
        String nombre = scanner.next();
        System.out.println("Apellido");
        String apellido = scanner.next();
        System.out.println("Puesto");
        String puesto = scanner.next();
        System.out.println("Dorsal");
        int dorsal = scanner.nextInt();
        System.out.println("Edad");
        int edad = scanner.nextInt();
        Jugador pJugador = new Jugador(ci,nombre,apellido,puesto,dorsal,edad,equipo);
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO jugador (ci,nombre,apellido,puesto,dorsal,edad,idEquipo) " +
                                                                         "VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1,pJugador.getCi());
            ps.setString(2,pJugador.getNombre());
            ps.setString(3,pJugador.getApellido());
            ps.setString(4,pJugador.getPuesto());
            ps.setInt(5,pJugador.getDorsal());
            ps.setInt(6,pJugador.getEdad());
            ps.setInt(7,pJugador.getEquipo());
            ps.executeUpdate();

            System.out.println("Jugador registrado!!");
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

}
