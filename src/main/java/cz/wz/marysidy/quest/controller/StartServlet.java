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
        String questId = req.getParameter("questId");

        req.setAttribute("playerName", session.getAttribute("playerName"));
        req.setAttribute("questId", questId);

        req.getRequestDispatcher("/WEB-INF/views/start.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        startNewGame(session, req);
        resp.sendRedirect(req.getContextPath() + "/game");
    }

    private void startNewGame(HttpSession session, HttpServletRequest req) {
        String questId = req.getParameter("questId");

        session.setAttribute("questId", questId);
        session.setAttribute("currentStepId", "start");

        List<String> history = new ArrayList<>(List.of("start"));
        session.setAttribute("history", history);

        Integer games = (Integer) session.getAttribute("gamesPlayed");
        session.setAttribute("gamesPlayed", (games == null ? 1 : games + 1));
        session.setAttribute("gameFinished", false);
    }
}
