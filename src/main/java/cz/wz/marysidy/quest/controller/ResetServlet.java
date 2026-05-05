package cz.wz.marysidy.quest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/reset")
public class ResetServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(ResetServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String newName = req.getParameter("playerName");

        log.info("Resetting session. New player: {}", newName);

        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        if (newName != null && !newName.isBlank()) {
            session = req.getSession();
            session.setAttribute("playerName", newName);
        }

        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
