package application;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletProducts extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("Aqui e o  metodo get");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        writer.println("Aqui e o  metodo post");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        writer.println("Aqui e o  metodo delete");

    }

    protected void doPut (HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        writer.println("Aqui e o  metodo put");

    }



}

/*

LER TODOS | GET    | /"SUA_COLECAO_NO_PLURAL"
INSERIR   | POST   | /"SUA_COLECAO_NO_PLURAL"

LER 1     | GET    | /"SUA_COLECAO_NO_PLURAL"/{:id/hash}
EXCLUIR   | DELETE | /"SUA_COLECAO_NO_PLURAL"/{:id/hash}
ATUALIZAR | PUT    | /"SUA_COLECAO_NO_PLURAL"/{:id/hash}

 */