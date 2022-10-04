package Persistencia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection getConexion(){
       String conexionUrl = "jdbc:sqlserver://localhost:1433;"
                            +"databaseName=obligatorioDDA1;"
                            +"userName=sa;"
                            +"password=pass;"
                            +"loginTimeout=30;"
                            +"encrypt=false;";
        try {
            Connection con = DriverManager.getConnection(conexionUrl);
            return con;
        }
        catch (SQLException ex){
            System.out.println(ex.toString());
            return null;
        }
    }
}
