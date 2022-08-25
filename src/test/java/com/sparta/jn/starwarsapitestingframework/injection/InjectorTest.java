package com.sparta.jn.starwarsapitestingframework.injection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.jn.starwarsapitestingframework.dto.PeopleDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class InjectorTest {
    private static final String BASE_URL = "https://swapi.dev/api/";
    private static PeopleDTO dto;
    private static HttpResponse<String> httpResponse;


    public PeopleDTO getPeopleDto(String resource, String id) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(BASE_URL + resource + id))
                .build();
        httpResponse = null;
        try {
            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
//            peoplePojo = objectMapper.readValue(new URL(URL), PeoplePojo.class);
            dto = objectMapper.readValue(httpResponse.body(), PeopleDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }

//    @BeforeAll
//    static void initAll() {
//        HttpClient httpClient = HttpClient.newHttpClient();
//        HttpRequest httpRequest = HttpRequest
//                .newBuilder()
//                .uri(URI.create(BASE_URL))
//                .build();
//        httpResponse = null;
//        try {
//            httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
////            peoplePojo = objectMapper.readValue(new URL(URL), PeoplePojo.class);
//            dto = objectMapper.readValue(httpResponse.body(), PeopleDTO.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    @Test
    @DisplayName("Check if it returns a populated dto")
    void checkIfItReturnsAPopulatedDto() {
        PeopleDTO injectorDTO = Injector.injectPeopleDTO("people", "1");
        Assertions.assertEquals(injectorDTO.toString(), dto.toString());
    }

    @Test
    @DisplayName("Check if single parameter method returns a populated dto")
    void checkIfSingleParameterMethodReturnsAPopulatedDto() {
        PeopleDTO injectorDTO = Injector.injectPeopleDTO("https://swapi.dev/api/people/1/");
        Assertions.assertEquals(injectorDTO.toString(), dto.toString());
    }

}
