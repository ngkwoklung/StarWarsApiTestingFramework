package com.sparta.jn.starwarsapitestingframework.dto;

import com.fasterxml.jackson.databind.JsonNode;
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
    private static final String URL = "https://swapi.dev/api/people/1/";
    private static PeoplePojo peoplePojo;
    private static HttpResponse<String> httpResponse;

    @BeforeAll
    static void init() {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            JsonNode jsonNode = mapper.readTree(new URL("https://swapi.dev/api/people/1/"));
//            System.out.println(jsonNode.get(1));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(URL))
                .build();
        httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(httpResponse.statusCode());
            System.out.println(httpResponse.headers());
            System.out.println(httpResponse.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            peoplePojo = objectMapper.readValue(new URL(URL), PeoplePojo.class);
            peoplePojo = objectMapper.readValue(httpResponse.body(), PeoplePojo.class);
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

        @Test
        @DisplayName("Check gender is **Male**, **Female**, **unknown or **n/a**")
        void checkGenderIsMaleFemaleUnknownOrNA() {
            String gender = peoplePojo.getGender();
            Assertions.assertTrue(gender.equals("Male") || gender.equals("Female")
                    || gender.equals("unknown") || gender.equals("n/a"));
        }
    }

}

