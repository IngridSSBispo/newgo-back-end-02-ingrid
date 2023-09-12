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

        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String ean13 = request.getParameter("ean13");
        String preco = request.getParameter("preco");
        String quantidade = request.getParameter("quantidade");
        String estoque_min = request.getParameter("estoque_min");
        int estoque_min_convertido = 0;
        double preco_convertido = 0;
        int quantidade_convertida = 0;

        if (estoque_min != null)
            estoque_min_convertido = Integer.parseInt(estoque_min);

        if (preco != null) {
            preco_convertido = Double.parseDouble(preco);
        }
        if (quantidade != null)
            quantidade_convertida = Integer.parseInt(quantidade);


        Produto produto = new Produto(
                nome,
                descricao,
                ean13,
                preco_convertido,
                quantidade_convertida,
                estoque_min_convertido
        );

        ProdutoDAO produtodao = new ProdutoDAO();

        boolean existNome = produtodao.checkIfExists("nome", nome);
        boolean existEan13 = produtodao.checkIfExists("ean13", ean13);

        if (existNome || existEan13) {
            writer.println("Produto ja foi cadastrado anteriormente!");
        } else {
            boolean success = produtodao.createProduct(produto);
            String result = new Gson().toJson(produto);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            writer.print(result);
            writer.flush();
        }


    }


}
