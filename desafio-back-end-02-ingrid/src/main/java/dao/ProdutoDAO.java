package dao;

import dao.conn.PostgreSQLJDBC;

import java.sql.*;

public class ProdutoDAO {


    public String getAllProducts()  {
        String result = "";
       try {
           Connection conn = PostgreSQLJDBC.getConnection();

           conn.setAutoCommit(false);

           Statement stmt = conn.createStatement();

           ResultSet rs = stmt.executeQuery("select nome from produtos;");

            while (rs.next()){
                result = rs.getString("nome");
            }


           rs.close();
           stmt.close();
           conn.close();

       } catch (Exception e){
           e.printStackTrace();
       }

        return result;
    }


}
