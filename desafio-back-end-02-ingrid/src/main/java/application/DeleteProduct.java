package br.com.desafiobackend;
import com.google.gson.Gson;
import infrastructure.ProdutoDAO;
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
        String msg = "";
        if (success){
            msg = "Produto excluido com sucesso";
        }else{
            msg = "Produto nao existe!";
        }

        String result = new Gson().toJson(msg);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        writer.print(result);
        writer.flush();

    }

}
