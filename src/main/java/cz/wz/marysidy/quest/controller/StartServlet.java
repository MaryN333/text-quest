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
        if (session != null && session.getAttribute("playerName") != null) {
            req.setAttribute("playerName", session.getAttribute("playerName"));
        }
        req.getRequestDispatcher("/WEB-INF/views/start.jsp")
                .forward(req, resp);
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
