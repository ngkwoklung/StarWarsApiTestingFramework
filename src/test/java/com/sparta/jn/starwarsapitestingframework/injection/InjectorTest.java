package com.sparta.jn.starwarsapitestingframework.injection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.jn.starwarsapitestingframework.dto.PeopleDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class InjectorTest {
    private static final String BASE_URL = "https://swapi.dev/api/";
    private static PeopleDTO dto;


    private PeopleDTO getDtoWithStringId(String id) {
        HttpResponse<String> httpResponse;
        String url = "https://swapi.dev/api/people/" + id;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .build();
        httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dto = objectMapper.readValue(httpResponse.body(), PeopleDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }

    private PeopleDTO getDtoWithURL(String url) {
        HttpResponse<String> httpResponse;
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .build();
        httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            dto = objectMapper.readValue(httpResponse.body(), PeopleDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }



    @Test
    @DisplayName("Check if it returns a populated dto using two parameters")
    void checkIfItReturnsAPopulatedDto() {
        PeopleDTO injectorDTO = Injector.injectPeopleDTO("people", "1");
        PeopleDTO expectedDTO = getDtoWithStringId("1");
        Assertions.assertEquals(injectorDTO.toString(), expectedDTO.toString());
    }

    @Test
    @DisplayName("Check if single parameter method returns a populated dto")
    void checkIfSingleParameterMethodReturnsAPopulatedDto() {
        PeopleDTO injectorDTO = Injector.injectPeopleDTO("https://swapi.dev/api/people/1/");
        PeopleDTO expectedDTO = getDtoWithURL("https://swapi.dev/api/people/1/");
        Assertions.assertEquals(injectorDTO.toString(), dto.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1","2", "3", "4" , "5", "6", "7", "8", "9"})
    @DisplayName("Check return correct dto with multiple id's")
    void checkReturnCorrectDtoWithMultipleIdS(String id) {
        PeopleDTO injectorDTO = Injector.injectPeopleDTO("people", id);
        PeopleDTO expectedDTO = getDtoWithStringId(id);
        Assertions.assertEquals(injectorDTO.toString(), expectedDTO.toString());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    @DisplayName("Check return correct dto with multiple id's")
    void checkReturnCorrectDtoWithMultipleIdS(int id) {
        PeopleDTO injectorDTO = Injector.injectPeopleDTO("people", id);
        PeopleDTO expectedDTO = getDtoWithStringId(String.valueOf(id));
        Assertions.assertEquals(injectorDTO.toString(), expectedDTO.toString());
    }
}
