package cz.wz.marysidy.quest.controller;

import cz.wz.marysidy.quest.model.Step;
import cz.wz.marysidy.quest.service.GameService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private GameService gameService;

    @Override
    public void init() {
        this.gameService = new GameService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String stepId = (String) session.getAttribute("currentStepId");
        Step step = gameService.getStepById(stepId);

        resp.setContentType("text/html;charset=UTF-8");

        StringBuilder html = new StringBuilder();
        html.append("<html><body>");
        html.append("<h3>").append(step.getText()).append("</h3>");

        for (int i = 0; i < step.getOptions().size(); i++) {
            html.append("<form method='post'>")
                    .append("<input type='hidden' name='choice' value='").append(i).append("'/>")
                    .append("<button type='submit'>")
                    .append(step.getOptions().get(i).getText())
                    .append("</button>")
                    .append("</form>");
        }

        if (step.getOptions().isEmpty()) {
            html.append("<a href='").append(req.getContextPath()).append("/start'>Restart</a>");
        }

        html.append("</body></html>");
        resp.getWriter().write(html.toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        String stepId = (String) session.getAttribute("currentStepId");
        Step step = gameService.getStepById(stepId);
        int choiceIndex = Integer.parseInt(req.getParameter("choice"));
        String nextStepId = step.getOptions().get(choiceIndex).getNextStepId();
        session.setAttribute("currentStepId", nextStepId);
        resp.sendRedirect(req.getContextPath() + "/game");
    }
}
