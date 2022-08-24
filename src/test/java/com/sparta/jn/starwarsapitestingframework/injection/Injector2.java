package com.sparta.jn.starwarsapitestingframework.injection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.jn.starwarsapitestingframework.dto.PeopleDTO2;

import java.io.IOException;
import java.net.URL;

public class Injector2 {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static PeopleDTO2 injectActivityDTO(String path) {
        PeopleDTO2 dto = new PeopleDTO2();

        try {
            dto = mapper.readValue(new URL(path), PeopleDTO2.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dto;
    }
}
