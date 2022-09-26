
import Metodos.MetodosJugador;
import Persistencia.Conexion;
import Dominio.Jugador;
import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println( "1.Iniciar Partido" );
        System.out.println("2.Editar Equipos" );
        System.out.println("Seleccione una opción");

        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        switch (i){
            case 1:
                //iniciarPartido();
                System.out.println("Iniciar partido");
                break;
            case 2:
                //Editar Equipos();
                System.out.println("Editar Equipos");
                break;

        }
    }


}
