package com.sparta.jn.starwarsapitestingframework.connection;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {
    private static final Logger logger = Logger.getLogger("my logger");
    private static final ConsoleHandler consoleHandler = new ConsoleHandler();
    private static final String BASEURL = "https://swapi.dev/api/";
    {
        logger.setLevel(Level.FINE);
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.INFO);
    }

    public static String getConnection() {
        return BASEURL;
    }

    public static String getConnection(String resource, String endpoint) {
        return BASEURL + resource + "/" + endpoint;
    }

    public static String getConnection(String resource, int endpoint) {
        return BASEURL + resource + "/" + endpoint;
    }

    private static HttpResponse<String> getResponse() {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest
                .newBuilder()
                .uri(URI.create(BASEURL))
                .build();

        HttpResponse<String> response =null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.log(Level.FINE, "Response is: " + response.body());
        return response;

    }

    public static int getStatusCode() {
        return getResponse().statusCode();
    }

    public static String getHeader(String key) {
        return getResponse()
                .headers()
                .firstValue(key)
                .orElse("Key not found");
    }
}
