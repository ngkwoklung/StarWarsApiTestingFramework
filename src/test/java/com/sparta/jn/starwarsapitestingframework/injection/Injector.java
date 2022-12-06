package com.sparta.jn.starwarsapitestingframework.injection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.jn.starwarsapitestingframework.connection.ConnectionManager;
import com.sparta.jn.starwarsapitestingframework.dto.PeopleDTO;
import io.restassured.response.Response;

import java.io.IOException;


public class Injector {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static PeopleDTO injectPeopleDTO(String path) {
        PeopleDTO dto = new PeopleDTO();
        try {
            dto = mapper.readValue(ConnectionManager.getResponseBody(path), PeopleDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }
    public static PeopleDTO injectPeopleDTO(String resource, String id) {
        PeopleDTO dto = new PeopleDTO();
        try {
            dto = mapper.readValue(ConnectionManager.getResponseBody(resource, id), PeopleDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public static PeopleDTO injectPeopleDTO(String resource, int id) {
        PeopleDTO dto = new PeopleDTO();
        try {
            dto = mapper.readValue(ConnectionManager.getResponseBody(resource, id), PeopleDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }

    public static PeopleDTO injectPeopleDTO (Response response) {
        PeopleDTO dto = new PeopleDTO();
        try {
            dto = mapper.readValue(response.getBody().asString(), PeopleDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return dto;
    }
}