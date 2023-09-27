package application.old;
import com.google.gson.Gson;
import infrastructure.ProdutoDAO;
import domain.Produto;
import infrastructure.Status;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ListProducts extends HttpServlet {

    @Override
    protected void service( HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        ProdutoDAO dao = new ProdutoDAO();
        ArrayList<Produto> allProducts = dao.getProducts(Status.TODOS, null,-1);
        String result = new Gson().toJson(allProducts);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        writer.print(result);
        writer.flush();

    }


}
