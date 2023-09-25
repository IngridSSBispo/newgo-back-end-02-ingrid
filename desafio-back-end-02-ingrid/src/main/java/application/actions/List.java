package application.actions;

import com.google.gson.Gson;
import domain.Produto;
import infrastructure.ProdutoDAO;
import infrastructure.Status;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;

public class List {
    public void list(HttpServletResponse response, PrintWriter writer) {
        ProdutoDAO dao = new ProdutoDAO();
        ArrayList<Produto> allProducts = dao.getProducts(Status.TODOS, null);
        String result = new Gson().toJson(allProducts);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        writer.print(result);
        writer.flush();
    }
}
