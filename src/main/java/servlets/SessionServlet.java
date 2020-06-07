package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        urlPatterns = "/session",
        loadOnStartup = 1
)
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameterMap().containsKey("create")) {
            HttpSession session = req.getSession(true);
            resp.getWriter().write("session created with id " + session.getId());
            return;
        }
        if (req.getParameterMap().containsKey("delete")) {
            System.out.println("trigger session destruction");
            HttpSession session = req.getSession(false);
            if (session == null) {
                resp.getWriter().write("no session found");
            } else {
                session.invalidate();
                resp.getWriter().write("session deleted with id " + session.getId());
            }
            return;
        }
        if (req.getParameterMap().containsKey("status")) {
            HttpSession session = req.getSession(false);
            if (session == null) {
                resp.getWriter().write("no session found");
            } else {
                resp.getWriter().write("session active with id " + session.getId());
            }
            return;
        }
    }

}
