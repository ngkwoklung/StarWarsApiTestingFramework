package com.sparta.jn.starwarsapitestingframework;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

public class SWAPITests {
    private static HttpResponse<String> httpResponse;
    private static PeoplePojo peoplePojo;

    @BeforeAll
    static void initAll() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create("https://swapi.dev/api/people/1/"))
                .build();
        httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            peoplePojo = objectMapper.readValue(httpResponse.body(), PeoplePojo.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Check edited date is not before created date")
    void checkEditedDateIsNotBeforeCreatedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnn'Z'");
        LocalDateTime createdDate = LocalDateTime.parse(peoplePojo.getCreated(), formatter);
        LocalDateTime editedDate  = LocalDateTime.parse(peoplePojo.getEdited(), formatter);
        Assertions.assertTrue(editedDate.isAfter(createdDate) && editedDate.isBefore(LocalDateTime.now()));
    }


}
