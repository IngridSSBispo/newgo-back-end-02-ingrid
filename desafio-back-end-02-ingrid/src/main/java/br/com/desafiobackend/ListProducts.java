package br.com.desafiobackend;
import com.google.gson.Gson;
import produtos.Produto;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class ListProducts extends HttpServlet {

    @Override
    protected void service( HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();


        Produto produto = new Produto(
                UUID.randomUUID(),
                "Sabão",
                "sabão liquido",
                "12345678978",
                10.00,
                1,
                10,
                true
        );


        String result = new Gson().toJson(produto);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        writer.print(result);
        writer.flush();

    }


}
