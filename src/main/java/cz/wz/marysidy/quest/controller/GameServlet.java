package cz.wz.marysidy.quest.controller;

import cz.wz.marysidy.quest.model.Step;
import cz.wz.marysidy.quest.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger log = LoggerFactory.getLogger(GameServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String questId = (String) session.getAttribute("questId");
        GameService gameService = GameService.getInstance(questId);
        String stepId = (String) session.getAttribute("currentStepId");
        Step step = gameService.getStepById(stepId);

        if (step.getOptions().isEmpty()) {
            session.setAttribute("currentStepId", step.getId());
            resp.sendRedirect(req.getContextPath() + "/result");
            return;
        }

        req.setAttribute("step", step);
        req.setAttribute("history", session.getAttribute("history"));
        req.setAttribute("playerName", session.getAttribute("playerName"));
        req.setAttribute("gamesPlayed", session.getAttribute("gamesPlayed"));
        req.setAttribute("wins", session.getAttribute("gamesWon"));
        req.setAttribute("loses", session.getAttribute("gamesLost"));
        req.setAttribute("questId", session.getAttribute("questId"));

        req.getRequestDispatcher("/WEB-INF/views/game.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String questId = (String) session.getAttribute("questId");
        GameService gameService = GameService.getInstance(questId);
        String stepId = (String) session.getAttribute("currentStepId");
        Step step = gameService.getStepById(stepId);
        int choiceIndex = Integer.parseInt(req.getParameter("choice"));

        log.info("Player chose option {} at step {}", choiceIndex, stepId);

        String nextStepId = step.getOptions().get(choiceIndex).getNextStepId();

        log.info("Transition: {} -> {}", stepId, nextStepId);

        session.setAttribute("currentStepId", nextStepId);

        List<String> history = (List<String>) session.getAttribute("history");
        if (history != null) {
            history.add(nextStepId);
        }

        resp.sendRedirect(req.getContextPath() + "/game");
    }
}
