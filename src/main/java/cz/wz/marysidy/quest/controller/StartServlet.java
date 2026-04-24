package cz.wz.marysidy.quest.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/start")
public class StartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("""
                <html>
                <body>
                    <h2>Welcome to the Quest</h2>
                    <form method='post'>
                        Name: <input type='text' name='playerName'/>
                        <button type='submit'>Start</button>
                    </form>
                </body>
                </html>""");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("playerName");

        HttpSession session = req.getSession();
        session.setAttribute("playerName", name);
        session.setAttribute("currentStepId", "start");

        resp.sendRedirect(req.getContextPath() + "/game");
    }
}
