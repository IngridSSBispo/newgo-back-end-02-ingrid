package br.com.desafiobackend;
import dao.ProdutoDAO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class DeleteProduct extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();


       UUID hash = UUID.fromString(request.getParameter("hash"));

        ProdutoDAO produtodao = new ProdutoDAO();
        boolean success = produtodao.deleteProduct(hash);

        if (success){
            writer.println("Produto excluido com sucesso");
        }else{
            writer.println("Produto nao existe!");
        }


    }

}
