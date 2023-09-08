package dao;

import dao.conn.PostgreSQLJDBC;
import produtos.Produto;

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


    public boolean createProduct(Produto produto){
        boolean success = false;

        try {
            Connection conn = PostgreSQLJDBC.getConnection();


            String sql = "INSERT INTO produtos( " +
                    "hash, nome, descricao, ean13, preco, quantidade, estoque_min, lativo) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?); ";

            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setObject(1, produto.getHash());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, produto.getEan13());
            stmt.setDouble(5, produto.getPreco());
            stmt.setInt(6,produto.getQuantidade());
            stmt.setInt(7, produto.getEstoque_min());
            stmt.setBoolean(8, produto.getAtivo());

            stmt.executeUpdate();
            
            success = true;

        } catch (Exception e){
            e.printStackTrace();
        }

        return success;
    }

}
