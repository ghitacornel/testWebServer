package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@WebServlet(
        urlPatterns = "/body",
        loadOnStartup = 1
)
public class RequestBodyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("http GET with request body " + getBody(req));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("http POST with request body " + getBody(req));
    }

    private static String getBody(HttpServletRequest request) throws IOException {
        StringBuilder body = new StringBuilder();
        try (InputStream inputStream = request.getInputStream()) {
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    body.append(line);
                }
            }
        }
        return body.toString();
    }
}
