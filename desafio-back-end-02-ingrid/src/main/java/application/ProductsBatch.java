package application;

import application.actions.Create;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ProductsBatch  extends HttpServlet {

    // criar em lote -
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();

        Create createProductBatch = new Create();

        createProductBatch.createMultiple(request, response, writer);
    }



    // Criar recurso para atualizar o preço de produtos em lote, por valor fixo, aumentar ou diminuir  um valor e aumentar ou diminuir percentualmente.
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {


    }

}
