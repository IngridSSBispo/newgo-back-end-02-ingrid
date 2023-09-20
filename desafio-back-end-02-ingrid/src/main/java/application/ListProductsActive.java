package application;

import application.dto.HashDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Produto;
import infrastructure.ProdutoDAO;
import infrastructure.Status;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;


public class ListProductsActive extends HttpServlet {

    Gson gson = new GsonBuilder().serializeNulls().create();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        ProdutoDAO dao = new ProdutoDAO();


        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }


        HashDTO hashDTO = gson.fromJson(sb.toString(), HashDTO.class);

        // para deixar hash opcional na list products active , se for null ele mostra todos os ativos, se tiver um hash ele traz o produto com o hash.

        UUID hash = null;
        if (hashDTO.getHash() != null) {
            hash = hashDTO.getHash();
        }

        ArrayList<Produto> allProducts = dao.getProducts(Status.ATIVOS, hash);
        String result = new Gson().toJson(allProducts);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        writer.print(result);
        writer.flush();


    }
}
