package integration;

import org.junit.Assert;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class TestSimpleServlet {

    private static final String GET_URL = "http://localhost:8080/testWebServer/servlet";
    private static final String GET_POST = "http://localhost:8080/testWebServer/servlet";
    private static final String GET_PUT = "http://localhost:8080/testWebServer/servlet";
    private static final String GET_DELETE = "http://localhost:8080/testWebServer/servlet";

    @Test
    public void testGet() throws Exception {
        URL url = new URL(GET_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        Assert.assertTrue(HttpUtils.getStringResponseContent(connection).startsWith("http GET with parameters {} "));
    }

    @Test
    public void testGetWithParameters() throws Exception {
        URL url = new URL(GET_URL + "?parameter1=value1&parameter2=value2");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        Assert.assertTrue(HttpUtils.getStringResponseContent(connection).startsWith("http GET with parameters {parameter2=value2, parameter1=value1} "));
    }

    @Test
    public void testGetWithParametersAndError() throws Exception {
        URL url = new URL(GET_URL + "?error=" + URLEncoder.encode("my custom error", StandardCharsets.UTF_8.toString()));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        Assert.assertEquals(HttpURLConnection.HTTP_INTERNAL_ERROR, responseCode);
        Assert.assertEquals("my custom error", HttpUtils.getErrorStringResponseContent(connection));
    }

    @Test
    public void testPost() throws Exception {
        URL url = new URL(GET_POST);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        int responseCode = connection.getResponseCode();
        Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        Assert.assertTrue(HttpUtils.getStringResponseContent(connection).startsWith("http POST with parameters {} "));
    }

    @Test
    public void testPut() throws Exception {
        URL url = new URL(GET_PUT);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        int responseCode = connection.getResponseCode();
        Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        Assert.assertTrue(HttpUtils.getStringResponseContent(connection).startsWith("http PUT with parameters {} "));
    }

    @Test
    public void testDelete() throws Exception {
        URL url = new URL(GET_DELETE);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");
        int responseCode = connection.getResponseCode();
        Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
        Assert.assertTrue(HttpUtils.getStringResponseContent(connection).startsWith("http DELETE with parameters {} "));
    }


}
