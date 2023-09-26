package application.actions;

import application.dto.EstoqueMinDTO;
import application.dto.StatusDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Produto;
import infrastructure.ProdutoDAO;
import infrastructure.Status;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class List {
    public void list(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) throws IOException {
        ProdutoDAO dao = new ProdutoDAO();

        //pegando os parametros a partir do body request
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder().serializeNulls().create();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        StatusDTO statusDTO = gson.fromJson(sb.toString(), StatusDTO.class);

        ArrayList<Produto> allProducts = dao.getProducts(statusDTO.getStatus(), statusDTO.getHash(), -1);

        String result = new Gson().toJson(allProducts);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        writer.print(result);
        writer.flush();
    }

    /*
     * US006 - Criar recurso para consultar todos os produtos em quantidade de estoque
     * menor que o  estoque mínimo definido.
     */
    public void listStock(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) throws IOException {

        ProdutoDAO dao = new ProdutoDAO();

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder().serializeNulls().create();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        //StatusDTO statusDTO = gson.fromJson(sb.toString(), StatusDTO.class);

        EstoqueMinDTO estoqueMin = gson.fromJson(sb.toString(), EstoqueMinDTO.class);

        ArrayList<Produto> allProducts = dao.getProducts(Status.TODOS, null, estoqueMin.getEstoqueMin());

        String result = new Gson().toJson(allProducts);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        writer.print(result);
        writer.flush();

    }
}
