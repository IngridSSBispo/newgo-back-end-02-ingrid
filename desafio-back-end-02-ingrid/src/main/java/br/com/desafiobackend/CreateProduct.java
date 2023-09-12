package br.com.desafiobackend;

import com.google.gson.Gson;
import dao.ProdutoDAO;
import produtos.Produto;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateProduct extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        String requestBody = sb.toString();
        //writer.println(requestBody);

        //depois preciso colocar para post, mas não sei como.

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String ean13 = request.getParameter("ean13");
        String preco = request.getParameter("preco");
        String quantidade = request.getParameter("quantidade");
        String estoque_min = request.getParameter("estoque_min");

//        writer.println(nome);
//        writer.println(descricao);
//        writer.println(ean13);
//        writer.println(preco);
//        writer.println(quantidade);
//        writer.println(estoque_min);




        Produto produto = new Produto(
                nome,
                descricao,
                ean13,
                Double.parseDouble(preco),
                Integer.parseInt(quantidade),
                Integer.parseInt(estoque_min)
        );

        ProdutoDAO produtodao = new ProdutoDAO();
        boolean success = produtodao.createProduct(produto);

        String result = new Gson().toJson(produto);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        writer.print(result);
        writer.flush();

    }


}
