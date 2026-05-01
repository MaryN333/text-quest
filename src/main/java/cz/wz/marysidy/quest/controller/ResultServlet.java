package cz.wz.marysidy.quest.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        if (session == null) {
            resp.sendRedirect(req.getContextPath() + "/home");
            return;
        }

        String stepId = (String) session.getAttribute("currentStepId");

        Integer wins = (Integer) session.getAttribute("gamesWon");
        Integer loses = (Integer) session.getAttribute("gamesLost");
        Boolean finished = (Boolean) session.getAttribute("gameFinished");

        if (finished == null || !finished) {
            if ("win".equals(stepId)) {
                session.setAttribute("gamesWon", wins == null ? 1 : wins + 1);
            } else if ("lose".equals(stepId)) {
                session.setAttribute("gamesLost", loses == null ? 1 : loses + 1);
            }
            session.setAttribute("gameFinished", true);
        }

        req.setAttribute("playerName", session.getAttribute("playerName"));
        req.setAttribute("gamesPlayed", session.getAttribute("gamesPlayed"));
        req.setAttribute("wins", session.getAttribute("gamesWon"));
        req.setAttribute("loses", session.getAttribute("gamesLost"));
        req.setAttribute("questId", session.getAttribute("questId"));

        req.getRequestDispatcher("/WEB-INF/views/result.jsp")
                .forward(req, resp);
    }
}
