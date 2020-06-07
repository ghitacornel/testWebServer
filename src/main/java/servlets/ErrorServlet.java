package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        urlPatterns = "/error",
        loadOnStartup = 1
)
public class ErrorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String errorMessage = "custom error message";
        if (req.getParameterMap().containsKey("error")) {
            errorMessage = req.getParameter("error");
        }
        resp.setStatus(500);
        resp.getWriter().write(errorMessage);
    }

}
