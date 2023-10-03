package application.actions;

import application.dto.UpdateBatchDTO;
import domain.Produto;
import infrastructure.ProdutoDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;


public class Update {

    public void update(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UUID hash = UUID.fromString(request.getParameter("hash"));
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

        if (isProductActive(hash)) {
            updateByKey(hash, "descricao", descricao);
            updateByKey(hash, "preco", precoConvertido);
            updateByKey(hash, "quantidade", quantidadeConvertida);
            updateByKey(hash, "estoque_min", estoque_minConvertido);

            writer.println("Produto alterado com sucesso");

        } else {
            writer.println("Produto esta inativo e nao pode ser atualizado.");
        }
    }

    protected boolean isProductActive(UUID hash) {
        ProdutoDAO produtoDAO = new ProdutoDAO();
        return produtoDAO.isProductActive(hash);
    }

    protected void updateByKey(UUID hash, String key, String value) {
        ProdutoDAO produtodao = new ProdutoDAO();

        if (value != null && !value.isEmpty())
            produtodao.updateByKey(hash, key, value);
        else
            System.out.println("Campo descricao não pode ser vazio!");
    }

    protected void updateByKey(UUID hash, String key, int value) {
        ProdutoDAO produtodao = new ProdutoDAO();

        if (value > 0)
            produtodao.updateByKey(hash, key, value);
    }

    protected void updateByKey(UUID hash, String key, double value) {
        ProdutoDAO produtodao = new ProdutoDAO();

        if (value > 0)
            produtodao.updateByKey(hash, key, value);
    }

   /* protected void updatePriceBatch(HttpServletRequest request, HttpServletResponse response, List<UpdateBatchDTO> updates, List<Produto> produtos) {




        for (UpdateBatchDTO update : updates) {
            for (Produto produto : produtos) {
                if (update.getHash().equals(produto.getHash())) {
                    switch (update.getOperation()) {
                        case AUMENTAR_VALOR_FIXO:
                            produto.setPreco(produto.getPreco() + update.getValor());
                            break;
                        case DIMINUIR_VALOR_FIXO:
                            produto.setPreco(produto.getPreco() - update.getValor());
                            break;
                        case AUMENTAR_PORCENTAGEM:
                            produto.setPreco(produto.getPreco() * (1 + update.getValor() / 100));
                        case DIMINUIR_PORCENTAGEM:
                            produto.setPreco(produto.getPreco() * (1 - update.getValor() / 100));
                            break;
                        default:

                            break;
                    }
                }
            }
        }
    }
}



    */

}