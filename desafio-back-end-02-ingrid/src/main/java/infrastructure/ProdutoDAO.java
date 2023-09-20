package infrastructure;

import application.dto.ChangeStatusDTO;
import infrastructure.conn.PostgreSQLJDBC;
import domain.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;


public class ProdutoDAO {
    public ArrayList<Produto> getProducts(Status status, UUID hash) {
        ArrayList<Produto> result = new ArrayList<>();
        try {
            String filtro = "";
            if( status == Status.TODOS){
                filtro = "";
            } else if(status == Status.ATIVOS){
                filtro = " and lativo = true ";
            } else if (status == Status.INATIVOS){
                filtro = " and lativo = false ";
            }
            if(hash != null){
                filtro = filtro + " and hash = '" + hash + "' ";
            }


            String sql = "select * from produtos where 1=1 " + filtro + " order by dtcreate desc;" ;
            System.out.println(sql);

            Connection conn = PostgreSQLJDBC.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("ean13"),
                        rs.getDouble("preco"),
                        rs.getInt("quantidade"),
                        rs.getInt("estoque_min")
                );

                produto.setId(rs.getInt("id_produto"));
                produto.setLativo(rs.getBoolean("lativo"));
                result.add(produto);

            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean checkIfExists(String key, String value) {
        int quantidade = 0;
        try {
            String sql = "select count(*) as total from produtos where " + key + " = '" + value + "'; ";

            Connection conn = PostgreSQLJDBC.getConnection();
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                quantidade = rs.getInt("total");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (quantidade > 0)
            return true;
        else
            return false;
    }

    public boolean IsProductAtivo(UUID hash) {
        boolean result = false;

        String sql = "select lativo from produtos where hash = '" + hash + "';";

        try {

            Connection conn = PostgreSQLJDBC.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                result = rs.getBoolean("lativo");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            throw new RuntimeException("Erro IsProductAtivo: " + e);

        }


        return result;
    }

    public boolean createProduct(Produto produto) {
        boolean success = false;

        try {
            Connection conn = PostgreSQLJDBC.getConnection();


            String sql = "INSERT INTO produtos( " +
                    "hash, nome, descricao, ean13, preco, quantidade, estoque_min, lativo) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?);  ";

            PreparedStatement stmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            stmt.setObject(1, produto.getHash());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescricao());
            stmt.setString(4, produto.getEan13());
            stmt.setDouble(5, produto.getPreco());
            stmt.setInt(6, produto.getQuantidade());
            stmt.setInt(7, produto.getEstoque_min());
            stmt.setBoolean(8, false);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();

                if (generatedKeys.next()) {
                    produto.setId(generatedKeys.getInt("id_produto"));

                }
            }


            success = true;

        } catch (Exception e) {
            throw new RuntimeException("Create Product: " + e);
        }

        return success;
    }

    public boolean deleteProduct(UUID hash) {

        boolean result = false;
        String sql = "DELETE FROM produtos " +
                "WHERE hash = ? ;";

        int affectedrows = 0;

        try {
            Connection conn = PostgreSQLJDBC.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setObject(1, hash);

            affectedrows = pstmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (affectedrows > 0)
            return true;
        else
            return false;

    }

    public boolean changeStatus(ChangeStatusDTO changeStatusDTO) {
        String sql = "UPDATE produtos " +
                "SET lativo = ? " +
                "WHERE hash = '" + changeStatusDTO.getHash() + "' ;";

        int affectedrows = 0;

        try {
            Connection conn = PostgreSQLJDBC.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setBoolean(1, changeStatusDTO.getLativo());
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
                "WHERE hash = '" + hash + "' ;";

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
                "SET " + key + " = ?, dtupdate = ? " +
                "WHERE hash = '" + hash + "' ;";

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
