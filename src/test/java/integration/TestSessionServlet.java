package integration;

import org.junit.Assert;
import org.junit.Test;

import java.net.*;
import java.util.List;

public class TestSessionServlet extends AbstractTest {

    private static final String JSESSIONID = "JSESSIONID";
    private static final String STATUS_URL = URL + "/testWebServer/session?status";
    private static final String CREATE_URL = URL + "/testWebServer/session?create";
    private static final String DELETE_URL = URL + "/testWebServer/session?delete";

    @Test
    public void testCRUD() throws Exception {

        String sessionID;

        // READ, expect none
        {
            URL url = new URL(STATUS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
            Assert.assertEquals("no session found", HttpUtils.getStringResponseContent(connection));
        }

        // CREATE
        {
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);

            URL url = new URL(CREATE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
            Assert.assertTrue(HttpUtils.getStringResponseContent(connection).startsWith("session created"));

            List<HttpCookie> cookies = cookieManager.getCookieStore().getCookies();
            Assert.assertEquals(1, cookies.size());
            Assert.assertEquals(JSESSIONID, cookies.get(0).getName());
            sessionID = cookies.get(0).getValue();
        }

        // READ with previously provided ID
        {
            URL url = new URL(STATUS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", JSESSIONID + "=" + sessionID);
            int responseCode = connection.getResponseCode();
            Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
            Assert.assertEquals("session active with id " + sessionID, HttpUtils.getStringResponseContent(connection));
        }

        // DELETE
        {
            URL url = new URL(DELETE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", JSESSIONID + "=" + sessionID);
            int responseCode = connection.getResponseCode();
            Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
            Assert.assertEquals("session deleted with id " + sessionID, HttpUtils.getStringResponseContent(connection));
        }

        // READ with previously provided ID
        {
            URL url = new URL(STATUS_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Cookie", JSESSIONID + "=" + sessionID);
            int responseCode = connection.getResponseCode();
            Assert.assertEquals(HttpURLConnection.HTTP_OK, responseCode);
            Assert.assertEquals("no session found", HttpUtils.getStringResponseContent(connection));
        }

    }


}
