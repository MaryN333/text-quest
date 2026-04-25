package cz.wz.marysidy.quest.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        resp.setContentType("text/html;charset=UTF-8");
        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h2>Quest Game</h2>");
        html.append("<p>Welcome to the adventure!</p>");

        if (session != null && session.getAttribute("playerName") != null) {
            String name = (String) session.getAttribute("playerName");
            html.append("<p>Player: ").append(name).append("</p>");
        }

        html.append("<h3>Available quests:</h3>");

        // only one quest for now
        html.append("<form action='start' method='get'>")
                .append("<button type='submit'>Quest 1</button>")
                .append("</form>");

        // future quests
        html.append("<p>Quest 2 (under construction)</p>");
        html.append("<p>Quest 3 (under construction)</p>");
        html.append("</body></html>");

        resp.getWriter().write(html.toString());
    }
}
