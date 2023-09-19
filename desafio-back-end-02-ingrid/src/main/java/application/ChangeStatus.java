package application;


import com.google.gson.Gson;
import infrastructure.ProdutoDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.UUID;


public class ChangeStatus extends HttpServlet {

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();
        writer.println("Metodo get foi disparado!");



    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();
        writer.println("Metodo POST foi disparado!");

        String lativo = request.getParameter("lativo");
        String hash = request.getParameter("hash");
        ProdutoDAO produtodao = new ProdutoDAO();




        if(!lativo.isEmpty() && !hash.isEmpty()){
            boolean lativoConvert = true;
            UUID hashConvert  = UUID.fromString("6e5b6b7a-9911-41ed-8e41-46d14c87db49");

            produtodao.changeStatus(hashConvert, lativoConvert);

        } else {
            writer.println("Parametros invalidos");
        }




    }

}
