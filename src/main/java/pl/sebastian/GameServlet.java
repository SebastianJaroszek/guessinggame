package pl.sebastian;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    private static final int MAX_NUMBER = 1000;

    private int number;
    private Random random = new Random();

    //tutaj jest pierwsze wejście (albo po odgadnięciu)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        number = random.nextInt(MAX_NUMBER);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.println("Wylosowałem lizbę. Spróbuj ją odgadnąć<br>");
        printForm(out);
    }

    private void printForm(PrintWriter out) {
        out.println("<form action=\"game\" method=\"post\">");
        out.println("Podaj liczbę ");
        out.println("<input type=\"text\" name=\"guess\">");
        out.println("<input type=\"submit\">");
        out.println("</form>");
    }

    //tutaj są kolejne wejścia
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.println("<h1>Welcome!</h1>");
    }

}
