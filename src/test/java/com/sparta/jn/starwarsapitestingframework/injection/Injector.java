package com.sparta.jn.starwarsapitestingframework.injection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.jn.starwarsapitestingframework.connection.ConnectionManager;
import com.sparta.jn.starwarsapitestingframework.dto.PeopleDTO;

import java.io.IOException;
import java.net.URL;

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
}