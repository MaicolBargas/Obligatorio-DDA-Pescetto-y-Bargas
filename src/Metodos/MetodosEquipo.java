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
import Persistencia.Conexion;
import Metodos.MetodosJugador;

public class MetodosEquipo {
    public static void MostrarEquipo(){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM equipo");
            ResultSet rs = ps.executeQuery();

            Equipo unEquipo = new Equipo();

            while (rs.next()){
                unEquipo.setId(rs.getInt(1));
                unEquipo.setNombre(rs.getString(2));
                //int ci = Integer.getInteger();
                Tecnico dt = MetodosTecnico.BuscarTecnico(rs.getInt(3));
                unEquipo.setDt(dt);
                System.out.println(unEquipo.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public static void SeleccionarEquipo(){
        Scanner scanner = new Scanner(System.in);
        MostrarEquipo();
        System.out.println("Escriba el numero del equipo");
        int equipo = scanner.nextInt();
        Metodos.MetodosJugador.AltaJugador(equipo);
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
