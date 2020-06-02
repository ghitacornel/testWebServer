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
        name = "ASimpleServlet",
        urlPatterns = "/servlet",
        loadOnStartup = 1
)
public class SimpleServlet extends HttpServlet {

    @Override
    public void init() {
        System.out.println(this + " initialised");
        {
            Enumeration<String> parameterNames = getServletConfig().getServletContext().getInitParameterNames();
            while (parameterNames.hasMoreElements()) {
                String name = parameterNames.nextElement();
                System.out.println("context init parameter name = " + name + " , value = " + getServletConfig().getServletContext().getInitParameter(name));
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("http GET with parameters " + getParametersAsString(req) + " " + new Date());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("http POST with parameters " + getParametersAsString(req) + " " + new Date());
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("http PUT with parameters " + getParametersAsString(req) + " " + new Date());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("http DELETE with parameters " + getParametersAsString(req) + " " + new Date());
    }

    private String getParametersAsString(HttpServletRequest req) {
        Map<String, String> result = new HashMap<>();
        Enumeration<String> parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String element = parameterNames.nextElement();
            result.put(element, req.getParameter(element));
        }
        return result.toString();
    }
}
