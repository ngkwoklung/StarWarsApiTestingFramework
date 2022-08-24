package com.sparta.jn.starwarsapitestingframework.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.jn.starwarsapitestingframework.PeoplePojo;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PeopleTests {
    private static final String BASE_URL = "https://swapi.dev/api/people/1";
    private static PeoplePojo peoplePojo;
    private static HttpResponse<String> httpResponse;

    @BeforeAll
    static void init() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(BASE_URL))
                .build();
        httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            peoplePojo = objectMapper.readValue(new URL(BASE_URL), PeoplePojo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Nested
    @DisplayName("Check for valid types")
    class CheckForValidTypes {
        @Test
        @DisplayName("Check status code")
        void checkStatusCode() {
            Assertions.assertEquals(200, httpResponse.statusCode());
        }

        @Test
        @DisplayName("Simple header test")
        void simpleHeaderTest() {
            Assertions.assertEquals("nginx/1.16.1", httpResponse.headers().firstValue("Server").get());
        }
    }

}

