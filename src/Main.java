
import Metodos.*;
import Persistencia.Conexion;
import Dominio.*;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        IniciarMenu();
    }

    public static void IniciarMenu()
    {

        int i = -1;
        while(i != 0){
            System.out.println("------------------------");
            System.out.println( "0. Salir del menú" );
            System.out.println( "1. Iniciar Partido" );
            System.out.println("2. Ingresar Jugador" );
            System.out.println("3. Ingresar Tecnico" );
            System.out.println("4. Ingresar Equipo" );
            System.out.println("5. Ingresar Arbitro" );

            System.out.println("Seleccione una opción");

            System.out.println("------------------------");

            Scanner scanner = new Scanner(System.in);

            i = scanner.nextInt();
            switch (i){
                case 0:
                    break;
                case 1:
                    MetodosPartido.AltaPartido();
                    break;
                case 2:
                    MetodosJugador.AltaJugador();
                    break;
                case 3:
                    MetodosTecnico.MostrarTecnico();
                    break;
                case 4:
                    MetodosEquipo.AltaEquipo();
                    break;
                case 5:
                    MetodosArbitro.AltaArbitro();
                    break;
            }
        }


    }

}