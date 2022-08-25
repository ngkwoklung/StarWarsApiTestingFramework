package com.sparta.jn.starwarsapitestingframework.connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConnectionManagerTest {

    @Test
    @DisplayName("Check url is correct when passing strings")
    void checkUrlIsCorrectWhenPassingStrings() {
        Assertions.assertEquals(ConnectionManager.getConnectionURL("people","1"),
                "https://swapi.dev/api/people/1");
    }

    @Test
    @DisplayName("Check url is correct when passing string and an int")
    void checkUrlIsCorrectWhenPassingStringAndAnInt() {
        Assertions.assertEquals(ConnectionManager.getConnectionURL("people",1),
                "https://swapi.dev/api/people/1");
    }

    @Test
    @DisplayName("Check that status code is 200")
    void checkThatStatusCodeIs200() {
        Assertions.assertEquals(ConnectionManager.getStatusCode(), 200);
    }

    @Test
    @DisplayName("Check response body")
    void checkResponseBody() {

    }

}
