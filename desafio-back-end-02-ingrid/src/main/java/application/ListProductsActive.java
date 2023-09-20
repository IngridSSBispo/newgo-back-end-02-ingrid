package application;

import com.google.gson.Gson;
import domain.Produto;
import infrastructure.ProdutoDAO;
import infrastructure.Status;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


public class ListProductsActive extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        ProdutoDAO dao = new ProdutoDAO();

        ArrayList<Produto> allProducts = dao.getProducts(Status.ATIVOS);
        String result = new Gson().toJson(allProducts);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        writer.print(result);
        writer.flush();


    }
}
