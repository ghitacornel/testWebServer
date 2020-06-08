package servlets;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        urlPatterns = "/db",
        loadOnStartup = 1
)
public class JDBCServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<String> result = new ArrayList<>();
        try (Connection connection = getConnection()) {
            System.out.println("client info : " + connection.getClientInfo());
            System.out.println("catalog : " + connection.getCatalog());
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS mydummytable (id serial primary key, name varchar(20))");
            statement.executeUpdate("DELETE FROM mydummytable");
            statement.executeUpdate("INSERT INTO mydummytable (name) VALUES ('ion')");
            statement.executeUpdate("INSERT INTO mydummytable (name) VALUES ('gheorghe')");
            ResultSet resultSet = statement.executeQuery("SELECT name FROM mydummytable");
            while (resultSet.next()) {
                result.add(resultSet.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("error when using connection", e);
        }
        resp.getWriter().write(String.valueOf(result));
    }

    public static Connection getConnection() {
        Context context;
        try {
            context = new InitialContext();
        } catch (NamingException e) {
            throw new RuntimeException("cannot obtain JNDI context", e);
        }
        System.out.println("JNDI context : " + context);
        DataSource dataSource;
        try {
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/TestDB");
        } catch (NamingException e) {
            throw new RuntimeException("cannot obtain DataSource", e);
        }
        System.out.println("DataSource : " + dataSource);
        Connection connection;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("cannot obtain connection", e);
        }
        System.out.println("JDBC connection : " + connection);
        return connection;
    }
}
