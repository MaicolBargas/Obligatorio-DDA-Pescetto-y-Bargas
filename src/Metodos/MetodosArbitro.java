package Metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Dominio.Arbitro;
import Persistencia.Conexion;

public class MetodosArbitro {

    public static void MostrarArbitroPorPuesto(String pPuesto){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM arbitro where puesto = ?");
            ps.setString(1,pPuesto);
            ResultSet rs = ps.executeQuery();

            Arbitro unArbitro = new Arbitro();

            while (rs.next()){
                unArbitro.setCi(rs.getInt(1));
                unArbitro.setNombre(rs.getString(2));
                unArbitro.setApellido(rs.getString(3));
                unArbitro.setPuesto(rs.getString(4));
                System.out.println(unArbitro.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }
    public static void MostrarArbitroPorPartido(int pPartido){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM arbitro inner join arbitroPartido on ci = ciArbitro where idPartido = ?");
            ps.setInt(1,pPartido);
            ResultSet rs = ps.executeQuery();

            Arbitro unArbitro = new Arbitro();

            while (rs.next()){
                unArbitro.setCi(rs.getInt(1));
                unArbitro.setNombre(rs.getString(2));
                unArbitro.setApellido(rs.getString(3));
                unArbitro.setPuesto(rs.getString(4));
                System.out.println(unArbitro.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }
    public static void AltaArbitro(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ci");
        int ci = scanner.nextInt();
        System.out.println("Nombre");
        String nombre = scanner.next();
        System.out.println("Apellido");
        String apellido = scanner.next();
        System.out.println("Puesto");
        String puesto = scanner.next();

        Arbitro pArbitro = new Arbitro(ci,nombre,apellido,puesto);
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO arbitro (ci,nombre,apellido,puesto) " +
                    "VALUES (?,?,?,?)");
            ps.setInt(1,pArbitro.getCi());
            ps.setString(2,pArbitro.getNombre());
            ps.setString(3,pArbitro.getApellido());
            ps.setString(4,pArbitro.getPuesto());
            ps.executeUpdate();

            System.out.println("Arbitro registrado!!");
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }
}
