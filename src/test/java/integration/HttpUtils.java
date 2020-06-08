package integration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

final class HttpUtils {

    private HttpUtils() {
        // utility class
    }

    public static String getStringResponseContent(HttpURLConnection connection) {
        try {
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException("exception reading String context", e);
        }
    }

    public static String getErrorStringResponseContent(HttpURLConnection connection) {
        try {
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }
            return response.toString();
        } catch (Exception e) {
            throw new RuntimeException("exception reading String context", e);
        }
    }

}
