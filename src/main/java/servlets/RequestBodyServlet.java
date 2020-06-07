package servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(
        urlPatterns = "/body",
        loadOnStartup = 1
)
public class RequestBodyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("http POST with request body " + getBody(req));
    }

    private static String getBody(HttpServletRequest request) throws IOException {
        StringBuilder body = new StringBuilder();
        try (InputStream inputStream = request.getInputStream()) {
            if (inputStream != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    body.append(charBuffer, 0, bytesRead);
                }
            }
        }
        return body.toString();
    }
}
