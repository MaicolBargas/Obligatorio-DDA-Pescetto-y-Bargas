package Metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import Dominio.Tecnico;
import Persistencia.Conexion;

public class MetodosTecnico {
    public static void MostrarTecnico(){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tecnico");
            ResultSet rs = ps.executeQuery();

            Tecnico unTecnico = new Tecnico();

            while (rs.next()){
                unTecnico.setCi(rs.getInt(1));
                unTecnico.setNombre(rs.getString(2));
                unTecnico.setApellido(rs.getString(3));
                unTecnico.setEdad(rs.getInt(4));

                System.out.println(unTecnico.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public static void AltaTecnico(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ci");
        int ci = scanner.nextInt();
        System.out.println("Nombre");
        String nombre = scanner.next();
        System.out.println("Apellido");
        String apellido = scanner.next();
        System.out.println("Edad");
        int edad = scanner.nextInt();

        Tecnico pTecnico = new Tecnico(ci,nombre,apellido,edad);

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO tecnico (ci,nombre,apellido,edad) " +
                    "VALUES (?,?,?,?)");
            ps.setInt(1,pTecnico.getCi());
            ps.setString(2,pTecnico.getNombre());
            ps.setString(3,pTecnico.getApellido());
            ps.setInt(4,pTecnico.getEdad());
            ps.executeUpdate();

            System.out.println("Tecnico registrado!!");
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }
    public static Tecnico BuscarTecnico(int pCi){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM tecnico where ci = ?");
            ps.setInt(1,pCi);
            ResultSet rs = ps.executeQuery();

            Tecnico unTecnico = new Tecnico();

            while (rs.next()){
                unTecnico.setCi(rs.getInt(1));
                unTecnico.setNombre(rs.getString(2));
                unTecnico.setApellido(rs.getString(3));
                unTecnico.setEdad(rs.getInt(4));
            }
            return unTecnico;
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        return null;
    }
}
