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
    private int count;

    //tutaj jest pierwsze wejście (albo po odgadnięciu)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        number = random.nextInt(MAX_NUMBER);
        count = 0;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        out.println("Wylosowałem liczbę. Spróbuj ją odgadnąć.<br>");
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
        Integer guess = null;

        try {
            guess = Integer.valueOf(req.getParameter("guess"));
        } catch (NumberFormatException e){
            /*e.printStackTrace();*/
        }

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        if (guess == null){
            out.println("Podana dana musi być liczbą.");
            out.println("<a href=\"game\">Naciśnij, aby zagrać jeszcze raz</a>");
        } else {
            if (guess > number) {
                out.println("Podana liczba jest za duża.");
                printForm(out);
            } else if (guess < number){
                out.println("Podana liczba jest za mała.");
                printForm(out);
            } else {
                out.println("Brawo! Liczba została odgadnięta.");
                out.println("<a href=\"game\">Naciśnij, aby zagrać jeszcze raz</a>");
            }
            count++;
        }
    }

}
