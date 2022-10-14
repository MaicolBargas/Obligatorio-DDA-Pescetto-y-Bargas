package Metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Dominio.Equipo;
import Dominio.Tecnico;
import Dominio.Jugador;
import Persistencia.Conexion;
import Metodos.MetodosJugador;

public class MetodosEquipo{

    public static void MostrarEquipo(){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM equipo");
            ResultSet rs = ps.executeQuery();

            Equipo unEquipo = new Equipo();

            while (rs.next()){
                unEquipo.setId(rs.getInt(1));
                unEquipo.setNombre(rs.getString(2));
                Tecnico dt = MetodosTecnico.BuscarTecnico(rs.getInt(3));
                unEquipo.setDt(dt);
                System.out.println(unEquipo.toString1());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public static void MostrarEquipoPorPartido(int pIdPartido){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT distinct e.id FROM equipo e , partido p " +
                    " where e.id = p.local or e.id = p.visitante " +
                    " and p.id = ?");
            ps.setInt(1,pIdPartido);
            ResultSet rs = ps.executeQuery();

            Equipo unEquipo = new Equipo();

            while (rs.next()){
                unEquipo = BuscarEquipo(rs.getInt(1));
                System.out.println(unEquipo.toString1());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }
    public static int SeleccionarEquipo(){
        Scanner scanner = new Scanner(System.in);
        MostrarEquipo();
        System.out.println("Escriba el numero del equipo");
        int equipo = scanner.nextInt();
        return equipo;
    }

    public static List<Jugador> SeleccionarTitulares(int pIdEquipo, int pIdPartido){
        Scanner scanner = new Scanner(System.in);
        List<Jugador> lista = new ArrayList<Jugador>();

        System.out.println("SELECCIONE SU PORTERO TITULAR");
        MetodosJugador.MostrarJugadorPorPosicion(pIdEquipo,"portero");
        int p = scanner.nextInt();
        lista.add(MetodosJugador.BuscarJugadorPorDorsal(p,pIdEquipo));

        for (int i = 0; i < 4 ; i++) {
            System.out.println("SELECCIONE SU DEFENSA TITULAR");
            MetodosJugador.MostrarJugadorPorPosicion(pIdEquipo,"defensa");
            int d = scanner.nextInt();
            lista.add(MetodosJugador.BuscarJugadorPorDorsal(d,pIdEquipo));
        }

        for (int i = 0; i < 4 ; i++) {
            System.out.println("SELECCIONE SU MEDIOCAMPO TITULAR");
            MetodosJugador.MostrarJugadorPorPosicion(pIdEquipo,"mediocampista");
            int d = scanner.nextInt();
            lista.add(MetodosJugador.BuscarJugadorPorDorsal(d,pIdEquipo));
        }

        for (int i = 0; i < 2 ; i++) {
            System.out.println("SELECCIONE SU DELANTERA TITULAR");
            MetodosJugador.MostrarJugadorPorPosicion(pIdEquipo,"delantero");
            int d = scanner.nextInt();
            lista.add(MetodosJugador.BuscarJugadorPorDorsal(d,pIdEquipo));
        }
        return lista;
    }

    public static Equipo BuscarEquipo( int pIdEquipo){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM equipo where id = ?");
            ps.setInt(1,pIdEquipo);
            ResultSet rs = ps.executeQuery();

            Equipo unEquipo = new Equipo();

            while (rs.next()){
                unEquipo.setId(rs.getInt(1));
                unEquipo.setNombre(rs.getString(2));
                Tecnico dt = MetodosTecnico.BuscarTecnico(rs.getInt(3));
                unEquipo.setDt(dt);
            }
            return unEquipo;
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        return null;
    }

    public static void AltaAlineacion(List<Jugador> titulares, int idPartido, int idEquipo){
        for (Jugador jugador: titulares) {
            try {
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement("INSERT INTO titulares (idPartido,ciJugador,idEquipo) VALUES (?,?,?)");
                ps.setInt(1,idPartido);
                ps.setInt(2,jugador.getCi());
                ps.setInt(3,idEquipo);
                ps.executeUpdate();
            }
            catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
        MetodosEquipo.AltaSuplentes(idPartido,idEquipo);

    }

    public static void AltaSuplentes(int idPartido, int idEquipo)
    {
        List<Jugador> jugadores = MetodosJugador.MostrarJugadorPorClub(idEquipo);

        for (Jugador unJugador: jugadores) {
            try {
                Connection con = Conexion.getConexion();
                PreparedStatement ps = con.prepareStatement(" exec AltaSuplentes ?,?,?");
                ps.setInt(1,idPartido);
                ps.setInt(2,idEquipo);
                ps.setInt(3,unJugador.getCi());
                ps.executeUpdate();
            }
            catch (SQLException ex){
                System.out.println(ex.toString());
            }
        }
    }

    public static void MostrarAlineacionTitular(int idPartido, int idEquipo)
    {
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(
            "select ciJugador from titulares where idPartido = ? and idEquipo = ?");
            ps.setInt(1,idPartido);
            ps.setInt(2,idEquipo);
            ResultSet rs = ps.executeQuery();

            Jugador unJugador = new Jugador();

            while (rs.next()){
                unJugador = MetodosJugador.BuscarJugador(rs.getInt(1));
                System.out.println(unJugador.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public static void MostrarAlineacionSuplente(int idPartido, int idEquipo)
    {
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(
                    "select ciJugador from suplentes where idPartido = ? and idEquipo = ?");
            ps.setInt(1,idPartido);
            ps.setInt(2,idEquipo);

            ResultSet rs = ps.executeQuery();

            Jugador unJugador = new Jugador();

            while (rs.next()){
                unJugador = MetodosJugador.BuscarJugador(rs.getInt(1));
                System.out.println(unJugador.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public static void AltaEquipo(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nombre Equipo");
        String nombre = scanner.next();
        Metodos.MetodosTecnico.MostrarTecnico();
        System.out.println("Director Tecnico(Ingrece c.i.)");
        int ci = scanner.nextInt();
        Tecnico dt = MetodosTecnico.BuscarTecnico(ci);
        Equipo pEquipo = new Equipo(nombre,dt);
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO equipo (nombre,tecnico) " +
                    "VALUES (?,?)");
            ps.setString(1,pEquipo.getNombre());
            Tecnico dt1 = pEquipo.getDt();
            ps.setInt(2,dt1.getCi());
            ps.executeUpdate();

            System.out.println("Equipo registrado!!");
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

}