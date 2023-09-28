package application.actions;

import application.dto.CreateDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import domain.Produto;
import infrastructure.ProdutoDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

public class Create {

    public void create(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder().serializeNulls().create();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        CreateDTO createDTO = gson.fromJson(sb.toString(), CreateDTO.class);

        if (createDTO.getNome() == null || createDTO.getNome().isEmpty()) {
            writer.println("O campo nome nao pode ser nulo ou vazio");
        } else {
            UUID hash = UUID.randomUUID();
            Produto produto = new Produto(
                    hash,
                    createDTO.getNome(),
                    createDTO.getDescricao(),
                    createDTO.getEan13(),
                    createDTO.getPreco(),
                    createDTO.getQuantidade(),
                    createDTO.getEstoque_min()


            );

            ProdutoDAO produtodao = new ProdutoDAO();

            boolean existNome = produtodao.checkIfExists("nome", createDTO.getNome());
            boolean existEan13 = produtodao.checkIfExists("ean13", createDTO.getEan13());

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


    public void createMultiple(HttpServletRequest request, HttpServletResponse response, PrintWriter writer) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        Gson gson = new GsonBuilder().serializeNulls().create();

        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }

        CreateDTO[] createData = gson.fromJson(sb.toString(), CreateDTO[].class);

        for (int i = 0; i < createData.length; i++) {
            CreateDTO singleData = createData[i];

            if (singleData.getNome() == null || singleData.getNome().isEmpty()) {
                writer.println("O campo nome nao pode ser nulo ou vazio");
            } else {
                UUID hash = UUID.randomUUID();
                Produto produto = new Produto(
                        hash,
                        singleData.getNome(),
                        singleData.getDescricao(),
                        singleData.getEan13(),
                        singleData.getPreco(),
                        singleData.getQuantidade(),
                        singleData.getEstoque_min()

                );

                ProdutoDAO produtodao = new ProdutoDAO();

                boolean existNome = produtodao.checkIfExists("nome", singleData.getNome());
                boolean existEan13 = produtodao.checkIfExists("ean13", singleData.getEan13());

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
    }

}
