package com.sparta.jn.starwarsapitestingframework.connection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ConnectionManagerTest {

    @Test
    @DisplayName("Check url is correct when passing strings")
    void checkUrlIsCorrectWhenPassingStrings() {
        Assertions.assertEquals("https://swapi.dev/api/people/1?format=json", ConnectionManager.getConnectionURL("people","1"));
    }

    @Test
    @DisplayName("Check url is correct when passing string and an int")
    void checkUrlIsCorrectWhenPassingStringAndAnInt() {
        System.out.println(ConnectionManager.getConnectionURL("people",1));
        Assertions.assertEquals("https://swapi.dev/api/people/1?format=json", ConnectionManager.getConnectionURL("people",1));
    }

    @Test
    @DisplayName("Check that status code is 200")
    void checkThatStatusCodeIs200() {
        Assertions.assertEquals(ConnectionManager.getStatusCode(), 200);
    }

    @Test
    @DisplayName("Check response body")
    void checkResponseBody() {
        Assertions.assertEquals("{\"name\":\"Luke Skywalker\",\"height\":\"172\",\"mass\":\"77\",\"hair_color\":\"blond\",\"skin_color\":\"fair\",\"eye_color\":\"blue\",\"birth_year\":\"19BBY\",\"gender\":\"male\",\"homeworld\":\"https://swapi.dev/api/planets/1/\",\"films\":[\"https://swapi.dev/api/films/1/\",\"https://swapi.dev/api/films/2/\",\"https://swapi.dev/api/films/3/\",\"https://swapi.dev/api/films/6/\"],\"species\":[],\"vehicles\":[\"https://swapi.dev/api/vehicles/14/\",\"https://swapi.dev/api/vehicles/30/\"],\"starships\":[\"https://swapi.dev/api/starships/12/\",\"https://swapi.dev/api/starships/22/\"],\"created\":\"2014-12-09T13:50:51.644000Z\",\"edited\":\"2014-12-20T21:17:56.891000Z\",\"url\":\"https://swapi.dev/api/people/1/\"}", ConnectionManager.getResponseBody("people", 1));
    }

    @Test
    @DisplayName("Check server header is correct")
    void checkServerHeaderIsCorrect(){
        Assertions.assertEquals("nginx/1.16.1",ConnectionManager.getHeader("Server"));
    }

    @Test
    @DisplayName("CheckThatContentTypeHeaderIs Application/json")
    void checkThatContentTypeHeaderIsApplicationJson(){
        Assertions.assertEquals("application/json", ConnectionManager.getHeader("Content-Type"));
    }
    
    @Test
    @DisplayName("Check that header Vary returns Accept cookie")
    void checkThatHeaderVaryReturnsAcceptCookie(){
        Assertions.assertEquals("Accept, Cookie", ConnectionManager.getHeader("Vary"));
    }
    
    @Test
    @DisplayName("Check that header X-Frame-Options returns correct value")
    void checkThatHeaderXFrameOptionsReturnsCorrectValue(){
        Assertions.assertEquals("SAMEORIGIN", ConnectionManager.getHeader("X-Frame-Options"));
    }
    
    @Test
    @DisplayName("Check that Allow header has correct value")
    void checkThatAllowHeaderHasCorrectValue(){
        Assertions.assertEquals("GET, HEAD, OPTIONS", ConnectionManager.getHeader("Allow"));
    }
}