package br.com.desafiobackend;
import dao.ProdutoDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DeleteProduct extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();


        int id_produto = Integer.parseInt(request.getParameter("id_produto"));

        ProdutoDAO produtodao = new ProdutoDAO();
        boolean success = produtodao.deleteProduct(id_produto);

        if (success){
            writer.println("Produto excluido com sucesso");
        }else{
            writer.println("Produto nao existe!");
        }


    }

}
