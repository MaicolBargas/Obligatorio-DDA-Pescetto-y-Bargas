
import Metodos.MetodosJugador;
import Persistencia.Conexion;
import Dominio.Jugador;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println( "1.Iniciar Partido" );
        System.out.println("2.Ingresar Jugador" );
        System.out.println("3.Ingresar Tecnico" );
        System.out.println("4.Ingresar Equipo" );
        System.out.println("5.Ingresar Arbitro" );
        System.out.println("Seleccione una opción");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        switch (i){
            case 1:
                Metodos.MetodosEquipo.MostrarEquipo();
                break;
            case 2:
                Metodos.MetodosJugador.AltaJugador();
                break;
            case 3:
                //Metodos.MetodosTecnico.AltaTecnico();
                break;
            case 4:
               // Metodos.MetodosEquipo.AltaEquipo();
                break;
            case 5:
                Metodos.MetodosArbitro.AltaArbitro();
                break;
        }

    }


}
