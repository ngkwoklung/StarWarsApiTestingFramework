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
    private static String BASEURL = "https://swapi.dev/api/";
    {
        logger.setLevel(Level.FINE);
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.INFO);
    }

    public static String getConnectionURL() {
        return BASEURL;
    }

    public static String getConnectionURL(String resource, String id) {
        return BASEURL + resource + "/" + id + "?format=json";
    }

    public static String getConnectionURL(String resource, int id) {
        return BASEURL + resource + "/" + id + "?format=json";
    }
    public static String getConnectionURL(String url) {
        return BASEURL = url;
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
    private static HttpResponse<String> getResponse(String resource, String id) {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest
                .newBuilder()
                .uri(URI.create(BASEURL + resource + "/" + id))
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

    public static HttpResponse<String> getResponse(String resource) {
        var client = HttpClient.newHttpClient();
        var request = HttpRequest
                .newBuilder()
                .uri(URI.create(resource))
                .build();

        HttpResponse<String> response =null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        logger.log(Level.FINE, "Response is: " + response.body());
        return response;
    }

    public static String getResponseBody(String resource, String id) {
        return getResponse(resource, id).body();
    }

    public static String getResponseBody(String resource, int id) {
        return getResponse(resource, String.valueOf(id)).body();
    }

    public static int getStatusCode() {
        return getResponse().statusCode();
    }

    public static int getStatusCode(String url) {
        return getResponse(url).statusCode();
    }

    public static String getHeader(String key) {
        return getResponse()
                .headers()
                .firstValue(key)
                .orElse("Key not found");
    }

    public static void main(String[] args) {
        System.out.println(getConnectionURL("people", "1"));
    }
}
