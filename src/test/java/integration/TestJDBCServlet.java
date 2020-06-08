package integration;

import org.junit.Assert;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;

public class TestJDBCServlet extends AbstractTest {

    private static final String GET_URL = URL + "/testWebServer/db";

    @Test
    public void testGet() throws Exception {
        URL url = new URL(GET_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        Assert.assertEquals("[ion, gheorghe]", HttpUtils.getStringResponseContent(connection));
    }

}
