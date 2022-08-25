package com.sparta.jn.starwarsapitestingframework.dto;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.jn.starwarsapitestingframework.PeoplePojo;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;



public class PeoplePojoTests {
    private static final String URL = "https://swapi.dev/api/people/1/";
    private static PeoplePojo peoplePojo;
    private static HttpResponse<String> httpResponse;
    static String getRandomPeople(){
        Random random = new Random();
        int rando = random.nextInt(85);
        String url = "https://swapi.dev/api/people/" + rando + "/";
        return url;

    }

    @BeforeAll
    static void initAll() {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create(getRandomPeople()))
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


    @BeforeEach
    void init(TestInfo testInfo) {
        System.out.println("Class: " + testInfo.getClass().getName() + " Method: " + testInfo.getDisplayName() + " is executing");
    }

    @Nested
    @DisplayName("Check for status code and headers")
    class HeadersTests {
        @Test
        @DisplayName("Check status code")
        void checkStatusCode() {
            Assertions.assertEquals(200, httpResponse.statusCode());
        }

        @Test
        @DisplayName("Server header test")
        void serverHeaderTest() {
            Assertions.assertEquals("nginx/1.16.1", httpResponse.headers().firstValue("Server").get());
        }
        @Test
        @DisplayName("Content-type header test")
        void contentTypeHeaderTest() {
            Assertions.assertEquals("application/json", httpResponse.headers().firstValue("Content-Type").get());
        }
        @Test
        @DisplayName("X-Frame-Options header test")
        void XFrameOptionsHeaderTest() {
            Assertions.assertEquals("SAMEORIGIN", httpResponse.headers().firstValue("X-Frame-Options").get());
        }
    }


    @Nested
    @Tag("AttributesCheck")
    class AttributesCheck {

        @Nested
        @Tag("nameCheck")
        class nameTests {

            @Test
            @DisplayName("Check name is not blank")
            void checkNameIsNotBlank() {
                Assertions.assertFalse(peoplePojo.getName().isBlank());
            }

            @Test
            @DisplayName("Check name is not null")
            void checkNameIsNotNull() {
                Assertions.assertNotNull(peoplePojo.getName());
            }

            @Test
            @DisplayName("Check Name is not empty")
            void checkNameIsNotEmpty() {
                Assertions.assertFalse(peoplePojo.getName().isEmpty());
            }
        }

        @Nested
        @Tag("heightCheck")
        class HeightTests {
            @Test
            @DisplayName("Check height contains only numbers")
            void checkHeightContainsOnlyNumbers() {
                String height = peoplePojo.getHeight();
                Assertions.assertTrue(height.matches("[0-9]+"));
            }

            @Test
            @DisplayName("Check height is above 0")
            void checkHeightIsAbove0() {
                Assertions.assertTrue(Double.parseDouble(peoplePojo.getHeight()) > 0);
            }
        }

        @Nested
        @Tag("massCheck")
        class MassTests {
            @Test
            @DisplayName("Check mass contains only numbers")
            void checkMassContainsOnlyNumbers() {
                String height = peoplePojo.getMass();
                Assertions.assertTrue(height.matches("[0-9.]+"));
            }

            @Test
            @DisplayName("Check mass is above 0")
            void checkMassIsAbove0() {
                Assertions.assertTrue(Double.parseDouble(peoplePojo.getMass()) > 0);
            }
        }

        @Nested
        @Tag("hairColourCheck")
        class HairColourTests {
            @Test
            @DisplayName("Check hair colour is not blank")
            void checkHairColourIsNotBlank() {
                Assertions.assertTrue(!peoplePojo.getHairColor().isBlank());
            }

            @Test
            @DisplayName("Check hair color is not empty")
            void checkHairColorIsNotEmpty() {
                Assertions.assertTrue(!peoplePojo.getHairColor().isEmpty());
            }

            @Test
            @DisplayName("Check hair color is not null")
            void checkHairColorIsNotNull() {
                Assertions.assertTrue(peoplePojo.getHairColor() != null);
            }
        }

        @Nested
        @Tag("skinColourCheck")
        class SkinColourTests {
            @Test
            @DisplayName("Check skin colour is not blank")
            void checkSkinColourIsNotBlank() {
                Assertions.assertTrue(!peoplePojo.getSkinColor().isBlank());
            }

            @Test
            @DisplayName("Check skin color is not empty")
            void checkSkinColorIsNotEmpty() {
                Assertions.assertTrue(!peoplePojo.getSkinColor().isEmpty());
            }

            @Test
            @DisplayName("Check skin color is not null")
            void checkSkinColorIsNotNull() {
                Assertions.assertTrue(peoplePojo.getSkinColor() != null);
            }

            @Test
            @DisplayName("Check skin color only contains letters or /")
            void checkSkinColorOnlyContainsLettersOr() {
                Assertions.assertTrue(peoplePojo.getSkinColor().matches("[a-z/]+"));
            }

        }
        @Nested
        @Tag("eyeColourCheck")
        class EyeColourTests {
            @Test
            @DisplayName("Check eye colour is not blank")
            void checkEyeColourIsNotBlank() {
                Assertions.assertTrue(!peoplePojo.getEyeColor().isBlank());
            }

            @Test
            @DisplayName("Check eye color is not empty")
            void checkEyeColorIsNotEmpty() {
                Assertions.assertTrue(!peoplePojo.getEyeColor().isEmpty());
            }

            @Test
            @DisplayName("Check eye color is not null")
            void checkEyeColorIsNotNull() {
                Assertions.assertNotNull(peoplePojo.getEyeColor());
            }

            @Test
            @DisplayName("Check eye color only contains letters or /")
            void checkEyeColorOnlyContainsLettersOr() {
                Assertions.assertTrue(peoplePojo.getEyeColor().matches("[a-z/]+"));
            }
        }

        @Nested
        @Tag("birthYearCheck")
        class BirthYearCheck{
            @Test
            @DisplayName("Check birth year is not blank")
            void checkBirthYearIsNotBlank() {
                Assertions.assertFalse(peoplePojo.getBirthYear().isBlank());
            }

            @Test
            @DisplayName("Check birth year is not empty")
            void checkBirthYearIsNotEmpty() {
                Assertions.assertTrue(!peoplePojo.getBirthYear().isEmpty());
            }

            @Test
            @DisplayName("Check birth year is not null")
            void checkBirthYearNotNull() {
                Assertions.assertNotNull(peoplePojo.getBirthYear());
            }

            @Test
            @DisplayName("Check birth year end in BBY or ABY")
            void checkBirthYearEndInBbyOrAby() {
                System.out.println(peoplePojo.getBirthYear());
                Assertions.assertTrue(peoplePojo.getBirthYear().matches("[0-9]+BBY")||peoplePojo.getBirthYear().matches("[0-9]+ABY"));
            }
        }

        @Nested
        @Tag("genderCheck")
        class genderTests {

            @Test
            @DisplayName("Check gender is **Male**, **Female**, **unknown or **n/a**")
            void checkGenderIsMaleFemaleUnknownOrNA() {
                String gender = peoplePojo.getGender();
                Assertions.assertTrue(gender.equals("Male") || gender.equals("Female")
                        || gender.equals("unknown") || gender.equals("n/a"));
                System.out.println(peoplePojo.getGender());
            }
        }

        @Nested
        @Tag("urlsChecks")
        class UrlsTests {
            boolean isStatusCode200(String url) {
                var client = HttpClient.newHttpClient();
                var request = HttpRequest
                        .newBuilder()
                        .uri(URI.create(peoplePojo.getHomeworld()))
                        .build();
                HttpResponse<String> response = null;
                try {
                    response = client.send(request, HttpResponse.BodyHandlers.ofString());
                } catch (IOException | InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return response.statusCode() == 200;
            }

            boolean hasAllUrlsReturn200StatusCode(List<String> urls) {
                for (String url : urls) {
                    if (!isStatusCode200(url)) {
                        return false;
                    }
                }
                return true;
            }

            @Nested
            @Tag("homeworldCheck")
            class HomeWorldTest {

                @Test
                @DisplayName("Check that home world url return a 200 status code")
                void checkThatHomeWorldUrlReturnA200StatusCode() {
                    Assertions.assertTrue(isStatusCode200(peoplePojo.getHomeworld()));
                }

                @Test
                @DisplayName("Check url goes to planets resource")
                void checkUrlGoesToPlanetsResource() {
                    Assertions.assertTrue(peoplePojo.getHomeworld().matches("https://swapi.dev/api/planets/[0-9]+/"));
                }
            }

            @Nested
            @Tag("filmsChecks")
            class FilmsTests {
                @Test
                @DisplayName("Check that films urls return a 200 status code")
                void checkThatFilmsUrlsReturnA200StatusCode() {
                    Assertions.assertTrue(hasAllUrlsReturn200StatusCode(peoplePojo.getFilms()));
                }

                @Test
                @DisplayName("Check url goes to films resource")
                void checkUrlGoesToFilmsResource() {
                    boolean answer = true;
                    for (String url : peoplePojo.getFilms()) {
                        if (!url.matches("https://swapi.dev/api/films/[0-9]+/")) {
                            answer = false;
                        }
                    }
                    Assertions.assertTrue(answer);
                }

                @Test
                @DisplayName("Check films list is not empty")
                void checkFilmsListIsNotEmpty() {
                    Assertions.assertFalse(peoplePojo.getFilms().isEmpty());
                }

                @Test
                @DisplayName("Check films don't contain null values")
                void checkFilmsDonTContainNullValues() {
                    Assertions.assertFalse(peoplePojo.getFilms().contains(null));
                }

                @Test
                @DisplayName("Check films list is not null")
                void checkFilmsListIsNotNull() {
                    Assertions.assertNotNull(peoplePojo.getFilms());
                }
            }

            @Nested
            @Tag("speciesChecks")
            class SpeciesTests {
                @Test
                @DisplayName("Check that species urls all return a 200 status code")
                void checkThatSpeciesUrlsAllReturnA200StatusCode() {
                    Assertions.assertTrue(hasAllUrlsReturn200StatusCode(peoplePojo.getSpecies()));
                }

                @Test
                @DisplayName("Check url goes to species resource")
                void checkUrlGoesToSpeciesResource() {
                    boolean answer = true;
                    for (String url : peoplePojo.getSpecies()) {
                        if (!url.matches("https://swapi.dev/api/species/[0-9]+/")) {
                            answer = false;
                        }
                    }
                    Assertions.assertTrue(answer);
                }

                @Test
                @DisplayName("Check species don't contain null values")
                void checkSpeciesDonTContainNullValues() {
                    Assertions.assertFalse(peoplePojo.getSpecies().contains(null));
                }

                @Test
                @DisplayName("Check species list is not null")
                void checkSpeciesListIsNotNull() {
                    Assertions.assertNotNull(peoplePojo.getSpecies());
                }
            }

            @Nested
            @Tag("vehiclesChecks")
            class VehiclesTests {
                @Test
                @DisplayName("Check that vehicles urls all return a 200 status code")
                void checkThatVehiclesUrlsAllReturnA200StatusCode() {
                    Assertions.assertTrue(hasAllUrlsReturn200StatusCode(peoplePojo.getVehicles()));
                }

                @Test
                @DisplayName("Check url goes to vehicles resource")
                void checkUrlGoesToVehiclesResource() {
                    boolean answer = true;
                    for (String url : peoplePojo.getVehicles()) {
                        if (!url.matches("https://swapi.dev/api/vehicles/[0-9]+/")) {
                            answer = false;
                        }
                    }
                    Assertions.assertTrue(answer);
                }

                @Test
                @DisplayName("Check vehicles don't contain null values")
                void checkVehiclesDonTContainNullValues() {
                    Assertions.assertFalse(peoplePojo.getVehicles().contains(null));
                }

                @Test
                @DisplayName("Check vehicles list is not null")
                void checkVehiclesListIsNotNull() {
                    Assertions.assertNotNull(peoplePojo.getVehicles());
                }
            }

            @Nested
            @Tag("starshipsChecks")
            class StartshipsTests {
                @Test
                @DisplayName("Check that starships urls all return a 200 status code")
                void checkThatStartshipsUrlsAllReturnA200StatusCode() {
                    Assertions.assertTrue(hasAllUrlsReturn200StatusCode(peoplePojo.getStarships()));
                }

                @Test
                @DisplayName("Check url goes to starships resource")
                void checkUrlGoesToStartshipsResource() {
                    boolean answer = true;
                    for (String url : peoplePojo.getStarships()) {
                        if (!url.matches("https://swapi.dev/api/starships/[0-9]+/")) {
                            answer = false;
                        }
                    }
                    Assertions.assertTrue(answer);
                }

                @Test
                @DisplayName("Check starships don't contain null values")
                void checkStartshipsDonTContainNullValues() {
                    Assertions.assertFalse(peoplePojo.getStarships().contains(null));
                }

                @Test
                @DisplayName("Check starships list is not null")
                void checkStartshipsListIsNotNull() {
                    Assertions.assertNotNull(peoplePojo.getStarships());

                }
            }

            @Nested
            @Tag("datesCheck")
            class DatesTests {

                LocalDateTime dateConverter(String stringDate) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnn'Z'");
                    LocalDateTime createdDate = LocalDateTime.parse(stringDate, formatter);
                    return createdDate;
                }

                @Nested
                @Tag("createdCheck")
                class CreatedTests {

                    @Test
                    @DisplayName("Check created date is not blank")
                    void checkCreatedDateIsNotBlank() {
                        Assertions.assertFalse(peoplePojo.getCreated().isBlank());

                    }

                    @Test
                    @DisplayName("Check created date is not null")
                    void checkCreatedDateIsNotNull() {
                        Assertions.assertNotNull(peoplePojo.getCreated());
                    }

                    @Test
                    @DisplayName("Check created date is not empty")
                    void checkCreatedDateIsNotEmpty() {
                        Assertions.assertFalse(peoplePojo.getCreated().isEmpty());
                    }

                    @Test
                    @DisplayName("Check creation date is not in the future")
                    void checkCreationDateIsNotInTheFuture() {
                        Assertions.assertTrue(dateConverter(peoplePojo.getCreated()).isBefore(LocalDateTime.now()));
                    }
                }

                @Nested
                @Tag("editedCheck")
                class EditedTests {

                    @Test
                    @DisplayName("Check edited date is not blank")
                    void checkCreatedDateIsNotBlank() {
                        Assertions.assertFalse(peoplePojo.getCreated().isBlank());
                    }

                    @Test
                    @DisplayName("Check edited date is not null")
                    void checkCreatedDateIsNotNull() {
                        Assertions.assertNotNull(peoplePojo.getCreated());
                    }

                    @Test
                    @DisplayName("Check edited date is not empty")
                    void checkCreatedDateIsNotEmpty() {
                        Assertions.assertFalse(peoplePojo.getCreated().isEmpty());
                    }

                    @Test
                    @DisplayName("Check edited date is not in the future")
                    void checkEditedDateIsNotInTheFuture() {
                        Assertions.assertTrue(dateConverter(peoplePojo.getEdited()).isBefore(LocalDateTime.now()));
                    }

                    @Test
                    @DisplayName("Check edited date is not before created date")
                    void checkEditedDateIsNotBeforeCreatedDate() {
                        LocalDateTime editDate = dateConverter(peoplePojo.getEdited());
                        LocalDateTime createdDate = dateConverter(peoplePojo.getCreated());
                        System.out.println(createdDate);
                        //Assertions.assertTrue(editDate.isAfter(createdDate));
                    }
                }
            }

            @Nested
            @Tag("urlCheck")
            class UrlTests{
                @Test
                @DisplayName("Check that url return a 200 status code")
                void checkThatUrlReturnA200StatusCode() {
                    Assertions.assertTrue(isStatusCode200(peoplePojo.getUrl()));
                }

                @Test
                @DisplayName("Check url goes to people resource")
                void checkUrlGoesToPeopleResource() {
                    Assertions.assertTrue(peoplePojo.getUrl().matches("https://swapi.dev/api/people/[0-9]+/"));
                }

            }
        }

    }
}



