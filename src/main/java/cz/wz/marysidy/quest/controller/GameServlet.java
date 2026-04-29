package cz.wz.marysidy.quest.controller;

import cz.wz.marysidy.quest.model.Step;
import cz.wz.marysidy.quest.service.GameService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String questId = (String) session.getAttribute("questId");
        GameService gameService = new GameService(questId);
        String stepId = (String) session.getAttribute("currentStepId");
        Step step = gameService.getStepById(stepId);
        if (stepId == null || step == null) {
            resp.sendRedirect(req.getContextPath() + "/start");
            return;
        }

        Integer wins = (Integer) session.getAttribute("gamesWon");
        Integer loses = (Integer) session.getAttribute("gamesLost");
        Boolean finished = (Boolean) session.getAttribute("gameFinished");

        if (step.getOptions().isEmpty() && (finished == null || !finished)) {
            if ("win".equals(step.getId())) {
                session.setAttribute("gamesWon", wins == null ? 1 : wins + 1);
            } else if ("lose".equals(step.getId())) {
                session.setAttribute("gamesLost", loses == null ? 1 : loses + 1);
            }
            session.setAttribute("gameFinished", true);
        }

        req.setAttribute("step", step);
        req.setAttribute("history", session.getAttribute("history"));
        req.setAttribute("playerName", session.getAttribute("playerName"));
        req.setAttribute("gamesPlayed", session.getAttribute("gamesPlayed"));
        req.setAttribute("wins", session.getAttribute("gamesWon"));
        req.setAttribute("loses", session.getAttribute("gamesLost"));

        req.getRequestDispatcher("/WEB-INF/views/game.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String questId = (String) session.getAttribute("questId");
        GameService gameService = new GameService(questId);
        String stepId = (String) session.getAttribute("currentStepId");
        Step step = gameService.getStepById(stepId);
        if (stepId == null || step == null) {
            resp.sendRedirect(req.getContextPath() + "/start");
            return;
        }

        int choiceIndex = Integer.parseInt(req.getParameter("choice"));
        String nextStepId = step.getOptions().get(choiceIndex).getNextStepId();

        session.setAttribute("currentStepId", nextStepId);

        List<String> history = (List<String>) session.getAttribute("history");
        if (history != null) {
            history.add(nextStepId);
        }

        resp.sendRedirect(req.getContextPath() + "/game");
    }
}
