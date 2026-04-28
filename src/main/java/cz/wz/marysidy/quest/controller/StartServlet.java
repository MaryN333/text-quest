package cz.wz.marysidy.quest.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/start")
public class StartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        resp.setContentType("text/html;charset=UTF-8");
        if (session != null && session.getAttribute("playerName") != null) {
            String name = (String) session.getAttribute("playerName");
            resp.getWriter().write("""
                    <html>
                    <body>
                        <h2>Welcome back,  """ + name + """
                        ! Start new game?</h2>
                        <form method='post'>
                            <button type='submit'>Start New Game</button>
                        </form>
                        <a href='home'>Back to menu</a>
                    </body>
                    </html>""");
        } else {
            resp.getWriter().write("""
                    <html>
                    <body>
                        <h2>Welcome to the Quest</h2>
                        <form method='post'>
                            Name: <input type='text' name='playerName'/>
                            <button type='submit'>Start</button>
                        </form>
                        <a href='home'>Back to menu</a>
                    </body>
                    </html>""");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("playerName");

        HttpSession session = req.getSession();
        if (name != null && !name.isBlank()) {
            session.setAttribute("playerName", name);
        }
        session.setAttribute("currentStepId", "start");

        List<String> history = new ArrayList<>(List.of("start"));
        session.setAttribute("history", history);

        Integer games = (Integer) session.getAttribute("gamesPlayed");
        session.setAttribute("gamesPlayed", (games == null ? 1 : games + 1));
        session.setAttribute("gameFinished", false);

        resp.sendRedirect(req.getContextPath() + "/game");
    }
}
