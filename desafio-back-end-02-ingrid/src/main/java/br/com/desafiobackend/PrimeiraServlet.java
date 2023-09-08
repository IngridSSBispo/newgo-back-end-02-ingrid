package br.com.desafiobackend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.ProdutoDAO;
import produtos.Produto;

public class PrimeiraServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       PrintWriter writer = response.getWriter();
       writer.println("<html>");
       writer.println("<body>");
       writer.println("<h1>Olá mundo da Servlet..</h1>");


        ProdutoDAO dao = new ProdutoDAO();
        String allProducts = dao.getAllProducts();

        System.out.println("-------------->" + allProducts);


        writer.println(allProducts);
        writer.println("</body>");
        writer.println("</html>");
    }
}
