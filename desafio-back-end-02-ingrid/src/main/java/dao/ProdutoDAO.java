package dao;

import dao.conn.PostgreSQLJDBC;
import produtos.Produto;

import java.sql.*;

public class ProdutoDAO {


    public String getAllProducts()  {
        String result = "";
       try {
           String sql = "select * from produtos;";
           Connection conn = PostgreSQLJDBC.getConnection();
           conn.setAutoCommit(false);
           Statement stmt = conn.createStatement();
           ResultSet rs = stmt.executeQuery(sql);

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

    public boolean updateProduct(Produto produto){
        boolean result = false;
        String sql = "UPDATE public.produtos " +
                "SET hash=?, nome=?, descricao=?, ean13=?, preco=?, quantidade=?, estoque_min=?, dtcreate=?, dtupdate=?, lativo=? " +
                "WHERE id_produto = " + produto.getId() + ";";
        //https://www.postgresqltutorial.com/postgresql-jdbc/update/



        return result;
    }

    public boolean deleteProduct(int id_produto){

        boolean result = false;
        String sql = "DELETE FROM produtos " +
                "WHERE id_produto = ? ;";

        int affectedrows = 0;

        try {
            Connection conn = PostgreSQLJDBC.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id_produto);

            affectedrows = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (affectedrows > 0)
            return true;
        else
            return false;

    }

}
