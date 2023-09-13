package dao;
import dao.conn.PostgreSQLJDBC;
import produtos.Produto;
import java.sql.*;
import java.util.UUID;



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

    public boolean checkIfExists(String key,String value){
        int quantidade = 0;
        try {
            String sql = "select count(*) as total from produtos where " + key + " = '" + value + "'; ";

            Connection conn = PostgreSQLJDBC.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                quantidade = rs.getInt("total");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e){
            e.printStackTrace();
        }

        if(quantidade > 0)
            return true;
        else
            return false;
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
            stmt.setBoolean(8, true);



            stmt.executeUpdate();
            
            success = true;

        } catch (Exception e){
            e.printStackTrace();
        }

        return success;
    }

    public boolean updateProduct(int hash,Produto produto){
        boolean result = false;
        String sql = "UPDATE produtos " +
                "SET  nome=?, descricao=?, ean13=?, preco=?, quantidade=?, estoque_min=?, dtupdate =? " +
                "WHERE hash = " + hash  + ";";

        int affectedrows = 0;

        try {
            Connection conn = PostgreSQLJDBC.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1,produto.getNome());
            pstmt.setString(2,produto.getDescricao());
            pstmt.setString(3,produto.getEan13());
            pstmt.setDouble(4,produto.getPreco());
            pstmt.setInt(5,produto.getQuantidade());
            pstmt.setInt(6,produto.getEstoque_min());
            pstmt.setTimestamp(7, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));

            affectedrows = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (affectedrows > 0)
            return true;
        else
            return false;

    }

    public boolean deleteProduct(UUID hash){

        boolean result = false;
        String sql = "DELETE FROM produtos " +
                "WHERE hash = ? ;";

        int affectedrows = 0;

        try {
            Connection conn = PostgreSQLJDBC.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setObject(1,hash);

            affectedrows = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (affectedrows > 0)
            return true;
        else
            return false;

    }

    public boolean updateByKey(UUID hash, String key, String value) {

        String sql = "UPDATE produtos " +
                "SET " + key + " = ?, dtupdate = ? " +
                "WHERE hash = '" + hash  + "' ;";

        int affectedrows = 0;

        try {
            Connection conn = PostgreSQLJDBC.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, value);
            pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
            affectedrows = pstmt.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (affectedrows > 0)
            return true;
        else
            return false;

    }

    public boolean updateByKey(UUID hash, String key, int value) {

        String sql = "UPDATE produtos " +
                "SET " + key + " = ?, dtupdate = ? " +
                "WHERE hash = '" + hash + "' ;";

        int affectedrows = 0;

        try {
            Connection conn = PostgreSQLJDBC.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, value);
            pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));


            affectedrows = pstmt.executeUpdate();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (affectedrows > 0)
            return true;
        else
            return false;

    }

    public boolean updateByKey(UUID hash, String key, double value) {

        String sql = "UPDATE produtos " +
                "SET " + key + " = ?, dtupdate = ? "+
                "WHERE hash = '" + hash  + "' ;";

        int affectedrows = 0;

        try {
            Connection conn = PostgreSQLJDBC.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setDouble(1, value);
            pstmt.setTimestamp(2, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));


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
