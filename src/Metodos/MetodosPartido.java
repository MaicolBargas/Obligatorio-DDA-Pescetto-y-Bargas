package Metodos;

import Dominio.Tecnico;
import Dominio.Jugador;
import Dominio.Jugador;
import Dominio.Partido;
import Dominio.Arbitro;
import Dominio.Equipo;
import Persistencia.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MetodosPartido {

    public static void IniciarPartido(Partido pPartido){
        int i = -1;
        while(i != 0){
            System.out.println("------------------------");
            System.out.println( "0. Finalizar Encuentro" );
            System.out.println( "1. Listar Equipo Local" );
            System.out.println("2. Listar Equipo Visitante" );
            System.out.println("3. Listar Terna Arbitral" );
            System.out.println("4. Anotar un gol");
            System.out.println("5. Realizar un cambio" );

            System.out.println("Seleccione una opción");
            System.out.println("------------------------");

            Scanner scanner = new Scanner(System.in);

            i = scanner.nextInt();
            switch (i){
                case 0:
                    FinalizarPartido(pPartido);
                case 1:
                    ListarEquipoLocal(pPartido);
                    break;
                case 2:
                    ListarEquipoVisitante(pPartido);
                    break;
                case 3:
                    MetodosArbitro.MostrarArbitroPorPartido(pPartido.getId());
                    break;
                case 4:
                    AnotarGol(pPartido.getId());
                    break;
                case 5:
                    RealizarCambio(pPartido.getId());
                    break;
            }
        }
    }

    public static Partido CrearPartido(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese numero de partido");
        int id = scanner.nextInt();
        System.out.println("Seleccione el equipo local");
        int idLocal = MetodosEquipo.SeleccionarEquipo();
        Equipo local = MetodosEquipo.BuscarEquipo(idLocal);
        System.out.println("Seleccione el equipo visitante");
        int idVisitante = MetodosEquipo.SeleccionarEquipo();
        Equipo visitante = MetodosEquipo.BuscarEquipo(idVisitante);

        System.out.println("Ingrese nombre del estadio");
        String estadio = scanner.nextLine();
        System.out.println("Ingrese la hora");
        String hora = scanner.nextLine();
        System.out.println("Ingrese la fecha");
        String fecha = scanner.nextLine();
        System.out.println("Ingrese el clima");
        String clima = scanner.nextLine();

        Partido unPartido = new Partido(id,local,visitante,estadio,fecha,hora,clima);
        return unPartido;
    }

    public static void SeleccionarArbitrosPartido(int idPartido)
    {
        System.out.println("Seleccione al arbitro principal");
        Arbitro principal = MetodosArbitro.BuscarArbitro(MetodosArbitro.SeleccionarArbitroPorPuesto("principal"));
        AltaArbitrosPartido(principal, idPartido);

        System.out.println("Seleccione al primer juez de linea");
        Arbitro linea1 = MetodosArbitro.BuscarArbitro(MetodosArbitro.SeleccionarArbitroPorPuesto("linea"));
        AltaArbitrosPartido(linea1, idPartido);

        System.out.println("Seleccione al segundo juez de linea");
        Arbitro linea2 = MetodosArbitro.BuscarArbitro(MetodosArbitro.SeleccionarArbitroPorPuesto("linea"));
        AltaArbitrosPartido(linea2, idPartido);
    }

    public static void AltaPartido(){
        Partido unPartido = CrearPartido();
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO partido (id,local,visitante,estadio,fecha,hora,clima) " +
                    "VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1,unPartido.getId());
            ps.setInt(2,unPartido.getLocal().getId());
            ps.setInt(3,unPartido.getVisitante().getId());
            ps.setString(4,unPartido.getEstadio());
            ps.setString(5,unPartido.getFecha());
            ps.setString(6,unPartido.getHora());
            ps.setString(7,unPartido.getClima());
            ps.executeUpdate();

            System.out.println("Partido registrado!!");
            SeleccionarArbitrosPartido(unPartido.getId());
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }

        int id = unPartido.getId();
        int idLocal = unPartido.getLocal().getId();
        int idVisitante = unPartido.getVisitante().getId();

        System.out.println("Ingrese el 11 del equipo local");
        List<Jugador> titularesL = MetodosEquipo.SeleccionarTitulares(idLocal,id);
        MetodosEquipo.AltaAlineacion(titularesL,id,idLocal);

        System.out.println("Ingrese el 11 del equipo visitante");
        List<Jugador> titularesV = MetodosEquipo.SeleccionarTitulares(idVisitante,id);
        MetodosEquipo.AltaAlineacion(titularesV,id,idVisitante);

        //Enviarnos a un menú
        IniciarPartido(unPartido);
    }

    public static void AltaArbitrosPartido(Arbitro arbitro, int idPartido){

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO arbitroPartido (idPartido, ciArbitro) " +
                    "VALUES (?,?)");
            ps.setInt(1,idPartido);
            ps.setInt(2,arbitro.getCi());
            ps.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }

    }

    public static void MostrarPartido(){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM partido");
            ResultSet rs = ps.executeQuery();

            Partido unPartido = new Partido();

            while (rs.next()){
                unPartido.setId(rs.getInt(1));
                unPartido.setLocal(MetodosEquipo.BuscarEquipo(rs.getInt(2)));
                unPartido.setVisitante(MetodosEquipo.BuscarEquipo(rs.getInt(3)));
                unPartido.setEstadio(rs.getString(4));
                unPartido.setFecha(rs.getString(5));
                unPartido.setHora(rs.getString(6));
                unPartido.setClima(rs.getString(7));
                System.out.println(unPartido.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public static void ListarEquipoLocal(Partido pPartido){
        int idPartido = pPartido.getId();
        int idLocal = pPartido.getLocal().getId();
        Dominio.Equipo eq = MetodosEquipo.BuscarEquipo(idLocal);
        Tecnico t =MetodosTecnico.BuscarTecnico(eq.getDt().getCi());

        System.out.println(pPartido.getLocal().getNombre());
        System.out.println("11 INICIAL:");
        MetodosEquipo.MostrarAlineacionTitular(idPartido, idLocal );
        System.out.println("DT: "+t.toString());

        System.out.println("SUPLENTES:");
        MetodosEquipo.MostrarAlineacionSuplente(idPartido, idLocal);
    }

    public static void ListarEquipoVisitante(Partido pPartido){
        int idPartido = pPartido.getId();
        int idVisitante = pPartido.getVisitante().getId();
        Equipo eq = MetodosEquipo.BuscarEquipo(idVisitante);
        Tecnico t =MetodosTecnico.BuscarTecnico(eq.getDt().getCi());

        System.out.println(pPartido.getVisitante().getNombre());
        System.out.println("11 INICIAL:");
        MetodosEquipo.MostrarAlineacionTitular(idPartido, idVisitante);
        System.out.println("DT: "+t.toString());
        System.out.println("SUPLENTES:");
        MetodosEquipo.MostrarAlineacionSuplente(idPartido, idVisitante);

    }

    public static Partido BuscarPartido(int pId){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM partido where id = ?");
            ps.setInt(1,pId);
            ResultSet rs = ps.executeQuery();

            Partido unPartido = new Partido();

            while (rs.next()){
                unPartido.setId(rs.getInt(1));
                unPartido.setLocal(MetodosEquipo.BuscarEquipo(rs.getInt(2)));
                unPartido.setVisitante(MetodosEquipo.BuscarEquipo(rs.getInt(3)));
                unPartido.setEstadio(rs.getString(4));
                unPartido.setFecha(rs.getString(5));
                unPartido.setHora(rs.getString(6));
                unPartido.setClima(rs.getString(7));
            }
            return unPartido;
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        return null;
    }

    public static void AnotarGol(int idPartido){
        Scanner scanner = new Scanner(System.in);

        System.out.println("SELECCIONE EQUIPO QUE MARCO EL GOL");
        MetodosEquipo.MostrarEquipoPorPartido(idPartido);
        int idEquipo = scanner.nextInt();

        System.out.println("SELECCIONE AL AUTOR DEL GOL");
        MetodosEquipo.MostrarAlineacionTitular(idPartido,idEquipo);
        MostrarCambiosParaAnotarGoles(idPartido,idEquipo);
        int dorsal = scanner.nextInt();
        Jugador autor = MetodosJugador.BuscarJugadorPorDorsal(dorsal,idEquipo);

        System.out.println("SELECCIONE EL MINUTO");
        int minuto = scanner.nextInt();

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO gol (idPartido,ciJugador,idEquipo,minuto) VALUES (?,?,?,?)");
            ps.setInt(1,idPartido);
            ps.setInt(2,autor.getCi());
            ps.setInt(3,idEquipo);
            ps.setInt(4,minuto);
            ps.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }

    }

    public static void MostrarGoles(int idPartido, int idEquipo){
        Equipo equipo = MetodosEquipo.BuscarEquipo(idEquipo);
        System.out.println("GOLES DE " +equipo.getNombre());

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(
                    "select * from gol where idPartido = ? and idEquipo = ?");
            ps.setInt(1,idPartido);
            ps.setInt(2,idEquipo);
            ResultSet rs = ps.executeQuery();

            Jugador unJugador = new Jugador();

            while (rs.next()){
                unJugador = MetodosJugador.BuscarJugador(rs.getInt(3));
                int minuto = rs.getInt(4);
                System.out.println("'"+minuto+ " : "+unJugador.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }

    }

    public static int ObtenerGolesPorEquipo(int idPartido, int idEquipo){
        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM gol WHERE idPartido = ? and idEquipo = ?");
            ps.setInt(1,idPartido);
            ps.setInt(2,idEquipo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }

        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
        return 0;
    }

    public static void ObtenerResultado(int idPartido,int idLocal, int idVisitante){
        Equipo local = MetodosEquipo.BuscarEquipo(idLocal);
        Equipo visitante = MetodosEquipo.BuscarEquipo(idVisitante);
        int golesLocal = ObtenerGolesPorEquipo(idPartido,idLocal);
        int golesVisitante = ObtenerGolesPorEquipo(idPartido,idVisitante);
        System.out.println("RESULTADO FINAL");
        System.out.println(local.getNombre()+" "+golesLocal+" - "+golesVisitante+" "+visitante.getNombre());
    }
    public static void RealizarCambio(int idPartido){
        Scanner scanner = new Scanner(System.in);

        System.out.println("SELECCIONE EQUIPO QUE REALIZA EL CAMBIO");
        MetodosEquipo.MostrarEquipoPorPartido(idPartido);
        int idEquipo = scanner.nextInt();

        System.out.println("SELECCIONE AL JUGADOR QUE SALE");
        MetodosEquipo.MostrarAlineacionTitular(idPartido,idEquipo);
        int d1 = scanner.nextInt();
        Jugador sale = MetodosJugador.BuscarJugadorPorDorsal(d1,idEquipo);

        System.out.println("SELECCIONE AL JUGADOR QUE ENTRA");
        MetodosEquipo.MostrarAlineacionSuplente(idPartido,idEquipo);
        int d2 = scanner.nextInt();
        Jugador entra = MetodosJugador.BuscarJugadorPorDorsal(d2,idEquipo);

        System.out.println("SELECCIONE EL MINUTO");
        int minuto = scanner.nextInt();

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement("INSERT INTO cambio (idPartido,idEquipo,ciEntra,ciSale,minuto) VALUES (?,?,?,?,?)");
            ps.setInt(1,idPartido);
            ps.setInt(2,idEquipo);
            ps.setInt(3,entra.getCi());
            ps.setInt(4,sale.getCi());
            ps.setInt(5,minuto);
            ps.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }

    }
    public static void MostrarCambios(int idPartido, int idEquipo){
        Equipo equipo = MetodosEquipo.BuscarEquipo(idEquipo);
        System.out.println("CAMBIOS DE " +equipo.getNombre());

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(
                    "select * from cambio where idPartido = ? and idEquipo = ?");
            ps.setInt(1,idPartido);
            ps.setInt(2,idEquipo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Jugador entra = MetodosJugador.BuscarJugador(rs.getInt(3));
                Jugador sale = MetodosJugador.BuscarJugador(rs.getInt(4));
                int minuto = rs.getInt(5);
                System.out.println("'"+minuto+ ": SALE "+sale.toString()+" ⬄ ENTRA "+entra.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public static void MostrarCambiosParaAnotarGoles(int idPartido, int idEquipo){
        Equipo equipo = MetodosEquipo.BuscarEquipo(idEquipo);
        System.out.println("INGRESADOS DEL BANQUILLO");

        try {
            Connection con = Conexion.getConexion();
            PreparedStatement ps = con.prepareStatement(
                    "select * from cambio where idPartido = ? and idEquipo = ?");
            ps.setInt(1,idPartido);
            ps.setInt(2,idEquipo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                Jugador entra = MetodosJugador.BuscarJugador(rs.getInt(3));
                Jugador sale = MetodosJugador.BuscarJugador(rs.getInt(4));
                int minuto = rs.getInt(5);
                System.out.println("'"+minuto+" "+entra.toString());
            }
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
        }
    }

    public static void FinalizarPartido(Partido pPartido)
    {
        int id = pPartido.getId();
        int idLocal = pPartido.getLocal().getId();
        int idVisitante = pPartido.getVisitante().getId();

        System.out.println("------------------------");
        System.out.println(pPartido.toString());
        System.out.println("------------------------");
        MetodosArbitro.MostrarArbitroPorPartido(id);
        System.out.println("------------------------");
        ListarEquipoLocal(pPartido);
        System.out.println("------------------------");
        MostrarCambios(id,idLocal);
        System.out.println("------------------------");
        MostrarGoles(id,idLocal);
        System.out.println("------------------------");
        ListarEquipoVisitante(pPartido);
        System.out.println("------------------------");
        MostrarCambios(id,idVisitante);
        System.out.println("------------------------");
        MostrarGoles(id,idVisitante);
        System.out.println("------------------------");
        ObtenerResultado(id,idLocal,idVisitante);
    }
}
