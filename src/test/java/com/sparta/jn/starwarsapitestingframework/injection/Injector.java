package com.sparta.jn.starwarsapitestingframework.injection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.jn.starwarsapitestingframework.connection.ConnectionManager;
import com.sparta.jn.starwarsapitestingframework.dto.PeopleDTO;

import java.io.IOException;
import java.net.URL;

public class Injector {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static PeopleDTO injectPeopleDTO(String resource, String id) {
        PeopleDTO dto = new PeopleDTO();
        String json = ConnectionManager.getResponseBody(resource, id);
        try {
            dto = mapper.readValue(json, PeopleDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }
    public static PeopleDTO injectPeopleDTO(String resource) {
        PeopleDTO dto = new PeopleDTO();
        String json = ConnectionManager.getResponseBody(resource);
        try {
            dto = mapper.readValue(json, PeopleDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }

}
