package infrastructure.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLJDBC {
    public static Connection getConnection () throws SQLException, ClassNotFoundException {
        Connection conection  = null;

        try{
            Class.forName("org.postgresql.Driver");
            conection = DriverManager
                    .getConnection(
                            "jdbc:postgresql://localhost:5432/estoque",
                            "postgres",
                            "g4m3g4m3");

            return conection;

        } catch(Exception e){
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            throw(e);

        }

//        System.out.println("Conexão inicializada com sucesso!");

    }
}
