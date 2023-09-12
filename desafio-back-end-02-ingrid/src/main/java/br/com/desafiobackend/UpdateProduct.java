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
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String ean13 = request.getParameter("ean13");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        int estoque_min = Integer.parseInt(request.getParameter("estoque_min"));

        PrintWriter writer = response.getWriter();

        ProdutoDAO produtodao = new ProdutoDAO();

        boolean existNome = produtodao.checkIfExists("nome", nome);
        boolean existEan13 = produtodao.checkIfExists("ean13", ean13);

        writer.println("Produto existNome: " + existNome);
        writer.println("Produto existEan13: " + existEan13);

        if(existNome || existEan13){
            writer.println("Produto já foi cadastrado anteriormente!");
        } else {
            Produto produto = new Produto(
                    nome,descricao,ean13,preco,quantidade,estoque_min
            );

            boolean success = produtodao.updateProduct(
                    id_produto,
                    produto
            );
        }


        // validação das regras

        //atualização das informações
//

//        if (success){
//            writer.println("Produto alterado com sucesso");
//        }else{
//            writer.println("Produto nao existe!");
//        }

    }
}