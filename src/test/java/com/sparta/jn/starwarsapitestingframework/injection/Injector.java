package com.sparta.jn.starwarsapitestingframework.injection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.jn.starwarsapitestingframework.connection.ConnectionManager;
import com.sparta.jn.starwarsapitestingframework.dto.PeopleDTO;

import java.io.IOException;
import java.net.URL;
import java.net.http.HttpResponse;

public class Injector {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static HttpResponse<String> httpResponse;
    public static PeopleDTO injectPeopleDTO(String resource, String id) {
        PeopleDTO dto = new PeopleDTO();
        httpResponse = ConnectionManager.getResponse(resource, id);
        try {
            dto = mapper.readValue(httpResponse.body(), PeopleDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }
    public static PeopleDTO injectPeopleDTO(String resource) {
        PeopleDTO dto = new PeopleDTO();
        httpResponse = ConnectionManager.getResponse(resource);
        try {
            dto = mapper.readValue(httpResponse.body(), PeopleDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }

}
