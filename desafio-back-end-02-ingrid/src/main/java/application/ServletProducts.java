package application;
import application.actions.Delete;
import application.actions.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletProducts extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //LER TODOS | GET    | /"SUA_COLECAO_NO_PLURAL"
        PrintWriter writer = response.getWriter();
        List ListProducts = new List();

        ListProducts.list(response, writer);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        writer.println("Aqui e o  metodo post");
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        EXCLUIR   | DELETE | /"SUA_COLECAO_NO_PLURAL"/{:id/hash}
        PrintWriter writer = response.getWriter();

        Delete deleteProduct = new Delete();

        deleteProduct.delete(request, response);

    }

    protected void doPut (HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        writer.println("Aqui e o  metodo put");

    }



}

/*

LER TODOS | GET    | /"SUA_COLECAO_NO_PLURAL" ok
INSERIR   | POST   | /"SUA_COLECAO_NO_PLURAL"

LER 1     | GET    | /"SUA_COLECAO_NO_PLURAL"/{:id/hash}

ATUALIZAR | PUT    | /"SUA_COLECAO_NO_PLURAL"/{:id/hash}

 */