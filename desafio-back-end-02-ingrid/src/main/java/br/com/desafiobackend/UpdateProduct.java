package br.com.desafiobackend;

import dao.ProdutoDAO;
import produtos.Produto;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateProduct extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id_produto = Integer.parseInt(request.getParameter("id_produto"));
        String descricao = request.getParameter("descricao");
        String preco = request.getParameter("preco");
        String quantidade = request.getParameter("quantidade");
        String estoque_min = request.getParameter("estoque_min");
        double precoConvertido = 0.0;
        int quantidadeConvertida = 0;
        int estoque_minConvertido = 0;

        PrintWriter writer = response.getWriter();

        if (preco != null) {
            precoConvertido = Double.parseDouble(preco);
        }

        if (quantidade != null) {
            quantidadeConvertida = Integer.parseInt(quantidade);
        }

        if (estoque_min != null) {
            estoque_minConvertido = Integer.parseInt(estoque_min);

        }

        // Não permitir alterar as informações de id, hash, nome, ean13, dtcreate, dtupdate.


        updateByKey(id_produto, "descricao", descricao);
        updateByKey(id_produto, "preco", precoConvertido);
        updateByKey(id_produto, "quantidade", quantidadeConvertida);
        updateByKey(id_produto, "estoque_min", estoque_minConvertido);

        writer.println("Produto alterado com sucesso");


    }

    protected void updateByKey(int id_produto, String key, String value) {
        ProdutoDAO produtodao = new ProdutoDAO();

        if (value != null && !value.isEmpty())
            produtodao.updateByKey(id_produto, key, value);
        else
            System.out.println("Campo descricao não pode ser vazio!");
    }

    protected void updateByKey(int id_produto, String key, int value) {
        ProdutoDAO produtodao = new ProdutoDAO();

        if (value > 0)
            produtodao.updateByKey(id_produto, key, value);
    }

    protected void updateByKey(int id_produto, String key, double value) {
        ProdutoDAO produtodao = new ProdutoDAO();

        if (value > 0)
            produtodao.updateByKey(id_produto, key, value);
    }
}