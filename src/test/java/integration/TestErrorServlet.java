package integration;

import integration.setup.AbstractTestDockerSetup;
import integration.utils.HttpUtils;
import org.junit.Assert;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TestErrorServlet extends AbstractTestDockerSetup {

    private static final String GET_URL = URL + "/testWebServer/error";

    @Test
    public void testGet() throws Exception {
        URL url = new URL(GET_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        Assert.assertEquals(HttpURLConnection.HTTP_INTERNAL_ERROR, responseCode);
        Assert.assertEquals("custom error message", HttpUtils.getErrorStringResponseContent(connection));
    }

    @Test
    public void testGetWithParameter() throws Exception {
        URL url = new URL(GET_URL + "?error=" + URLEncoder.encode("my very custom error", StandardCharsets.UTF_8.toString()));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        Assert.assertEquals(HttpURLConnection.HTTP_INTERNAL_ERROR, responseCode);
        Assert.assertEquals("my very custom error", HttpUtils.getErrorStringResponseContent(connection));
    }

}
