package com.sparta.jn.starwarsapitestingframework;
import com.sparta.jn.starwarsapitestingframework.dto.PeopleDTO;
import com.sparta.jn.starwarsapitestingframework.injection.Injector;
import org.junit.jupiter.api.*;
import java.util.Random;

public class SWAPIFrameworkTests {
        private static PeopleDTO peopleDTO;
        static int getRandomNumber(){
            Random random = new Random();
            return random.nextInt(83)+1;
        }

        @BeforeAll
        static void initAll() {
            peopleDTO = Injector.injectPeopleDTO("people",getRandomNumber());
        }

        @BeforeEach
        void init(TestInfo testInfo) {
            System.out.println("Class: " + testInfo.getClass().getName() + " Method: " + testInfo.getDisplayName() + " is executing");
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
                    Assertions.assertTrue(peopleDTO.hasAttributeNotBlank(peopleDTO.getName()));
                }

                @Test
                @DisplayName("Check name is not null")
                void checkNameIsNotNull() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotNull(peopleDTO.getName()));
                }

                @Test
                @DisplayName("Check Name is not empty")
                void checkNameIsNotEmpty() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotEmpty(peopleDTO.getName()));
                }
            }

            @Nested
            @Tag("heightCheck")
            class HeightTests {
                @Test
                @DisplayName("Check height contains only numbers")
                void checkHeightContainsOnlyNumbers() {
                    Assertions.assertTrue(peopleDTO.hasMeasurementContainingNumbers(peopleDTO.getHeight()));
                }

                @Test
                @DisplayName("Check height is above 0")
                void checkHeightIsAbove0() {
                    Assertions.assertTrue(peopleDTO.hasMeasurementAboveZero(peopleDTO.getHeight()));
                }
            }

            @Nested
            @Tag("massCheck")
            class MassTests {
                @Test
                @DisplayName("Check mass contains only numbers")
                void checkMassContainsOnlyNumbers() {
                    Assertions.assertTrue(peopleDTO.hasMeasurementContainingNumbers(peopleDTO.getMass()));
                }

                @Test
                @DisplayName("Check mass is above 0")
                void checkMassIsAbove0() {
                    Assertions.assertTrue(peopleDTO.hasMeasurementAboveZero(peopleDTO.getMass()));
                }
            }

            @Nested
            @Tag("hairColourCheck")
            class HairColourTests {
                @Test
                @DisplayName("Check hair colour is not blank")
                void checkHairColourIsNotBlank() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotBlank(peopleDTO.getHairColor()));
                }

                @Test
                @DisplayName("Check hair color is not empty")
                void checkHairColorIsNotEmpty() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotEmpty(peopleDTO.getHairColor()));
                }

                @Test
                @DisplayName("Check hair color is not null")
                void checkHairColorIsNotNull() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotNull(peopleDTO.getHairColor()));
                }
            }

            @Nested
            @Tag("skinColourCheck")
            class SkinColourTests {
                @Test
                @DisplayName("Check skin colour is not blank")
                void checkSkinColourIsNotBlank() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotBlank(peopleDTO.getSkinColor()));
                }

                @Test
                @DisplayName("Check skin color is not empty")
                void checkSkinColorIsNotEmpty() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotEmpty(peopleDTO.getSkinColor()));
                }

                @Test
                @DisplayName("Check skin color is not null")
                void checkSkinColorIsNotNull() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotNull(peopleDTO.getSkinColor()));
                }

                @Test
                @DisplayName("Check skin color only contains letters or /")
                void checkSkinColorOnlyContainsLettersOr() {
                    System.out.println(peopleDTO.getSkinColor());
                    Assertions.assertTrue(peopleDTO.hasOnlyLetters(peopleDTO.getSkinColor()));
                }

            }
            @Nested
            @Tag("eyeColourCheck")
            class EyeColourTests {
                @Test
                @DisplayName("Check eye colour is not blank")
                void checkEyeColourIsNotBlank() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotBlank(peopleDTO.getEyeColor()));
                }

                @Test
                @DisplayName("Check eye color is not empty")
                void checkEyeColorIsNotEmpty() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotEmpty(peopleDTO.getEyeColor()));
                }

                @Test
                @DisplayName("Check eye color is not null")
                void checkEyeColorIsNotNull() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotNull(peopleDTO.getEyeColor()));
                }

                @Test
                @DisplayName("Check eye color only contains letters or /")
                void checkEyeColorOnlyContainsLettersOr() {
                    Assertions.assertTrue(peopleDTO.hasOnlyLetters(peopleDTO.getEyeColor()));
                }
            }

            @Nested
            @Tag("birthYearCheck")
            class BirthYearCheck{
                @Test
                @DisplayName("Check birth year is not blank")
                void checkBirthYearIsNotBlank() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotBlank(peopleDTO.getBirthYear()));
                }

                @Test
                @DisplayName("Check birth year is not empty")
                void checkBirthYearIsNotEmpty() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotEmpty(peopleDTO.getBirthYear()));
                }

                @Test
                @DisplayName("Check birth year is not null")
                void checkBirthYearNotNull() {
                    Assertions.assertTrue(peopleDTO.hasAttributeNotNull(peopleDTO.getBirthYear()));
                }

                @Test
                @DisplayName("Check birth year end in BBY or ABY")
                void checkBirthYearEndInBbyOrAby() {

                    Assertions.assertTrue(peopleDTO.hasBirthYearFormat());
                }
            }

            @Nested
            @Tag("genderCheck")
            class genderTests {

                @Test
                @DisplayName("Check gender is **Male**, **Female**, **unknown or **n/a**")
                void checkGenderIsMaleFemaleUnknownOrNA() {
                    Assertions.assertTrue(peopleDTO.hasGender());
                }
            }

            @Nested
            @Tag("urlsChecks")
            class UrlsTests {

                @Nested
                @Tag("homeworldCheck")
                class HomeWorldTest {

                    @Test
                    @DisplayName("Check that home world url return a 200 status code")
                    void checkThatHomeWorldUrlReturnA200StatusCode() {
                        Assertions.assertTrue(peopleDTO.isURLStatusCode200(peopleDTO.getHomeworld()));
                    }

                    @Test
                    @DisplayName("Check url goes to planets resource")
                    void checkUrlGoesToPlanetsResource() {
                        Assertions.assertTrue(peopleDTO.hasCorrectURL("planets", peopleDTO.getHomeworld()));
                    }
                }

                @Nested
                @Tag("filmsChecks")
                class FilmsTests {
                    @Test
                    @DisplayName("Check that films urls return a 200 status code")
                    void checkThatFilmsUrlsReturnA200StatusCode() {
                        Assertions.assertTrue(peopleDTO.hasLoopWithURLStatusCode200(peopleDTO.getFilms()));
                    }

                    @Test
                    @DisplayName("Check url goes to films resource")
                    void checkUrlGoesToFilmsResource() {
                        Assertions.assertTrue(peopleDTO.hasArrayGotCorrectURL("films", peopleDTO.getFilms()));
                    }

                    @Test
                    @DisplayName("Check films list is not empty")
                    void checkFilmsListIsNotEmpty() {
                        Assertions.assertTrue(peopleDTO.hasListNotEmpty(peopleDTO.getFilms()));
                    }

                    @Test
                    @DisplayName("Check films don't contain null values")
                    void checkFilmsDonTContainNullValues() {
                        Assertions.assertTrue(peopleDTO.hasArrayContainsNoNullValues(peopleDTO.getFilms()));
                    }

                    @Test
                    @DisplayName("Check films list is not null")
                    void checkFilmsListIsNotNull() {
                        Assertions.assertTrue(peopleDTO.isListNotNull(peopleDTO.getFilms()));
                    }
                }

                @Nested
                @Tag("speciesChecks")
                class SpeciesTests {
                    @Test
                    @DisplayName("Check that species urls all return a 200 status code")
                    void checkThatSpeciesUrlsAllReturnA200StatusCode() {
                        Assertions.assertTrue(peopleDTO.hasLoopWithURLStatusCode200(peopleDTO.getSpecies()));
                    }

                    @Test
                    @DisplayName("Check url goes to species resource")
                    void checkUrlGoesToSpeciesResource() {
                        Assertions.assertTrue(peopleDTO.hasArrayGotCorrectURL("species", peopleDTO.getSpecies()));
                    }

                    @Test
                    @DisplayName("Check species don't contain null values")
                    void checkSpeciesDonTContainNullValues() {
                        Assertions.assertTrue(peopleDTO.hasArrayContainsNoNullValues(peopleDTO.getSpecies()));
                    }

                    @Test
                    @DisplayName("Check species list is not null")
                    void checkSpeciesListIsNotNull() {
                        Assertions.assertTrue(peopleDTO.hasArrayContainsNoNullValues(peopleDTO.getSpecies()));
                    }
                }

                @Nested
                @Tag("vehiclesChecks")
                class VehiclesTests {
                    @Test
                    @DisplayName("Check that vehicles urls all return a 200 status code")
                    void checkThatVehiclesUrlsAllReturnA200StatusCode() {
                        Assertions.assertTrue(peopleDTO.hasLoopWithURLStatusCode200(peopleDTO.getVehicles()));
                    }

                    @Test
                    @DisplayName("Check url goes to vehicles resource")
                    void checkUrlGoesToVehiclesResource() {
                        Assertions.assertTrue(peopleDTO.hasArrayGotCorrectURL("vehicles", peopleDTO.getVehicles()));
                    }

                    @Test
                    @DisplayName("Check vehicles don't contain null values")
                    void checkVehiclesDonTContainNullValues() {
                        Assertions.assertTrue(peopleDTO.hasArrayContainsNoNullValues(peopleDTO.getVehicles()));
                    }

                    @Test
                    @DisplayName("Check vehicles list is not null")
                    void checkVehiclesListIsNotNull() {
                        Assertions.assertTrue(peopleDTO.isListNotNull(peopleDTO.getVehicles()));
                    }
                }

                @Nested
                @Tag("starshipsChecks")
                class StartshipsTests {
                    @Test
                    @DisplayName("Check that starships urls all return a 200 status code")
                    void checkThatStartshipsUrlsAllReturnA200StatusCode() {
                        Assertions.assertTrue(peopleDTO.hasLoopWithURLStatusCode200(peopleDTO.getStarships()));
                    }

                    @Test
                    @DisplayName("Check url goes to starships resource")
                    void checkUrlGoesToStartshipsResource() {
                        Assertions.assertTrue(peopleDTO.hasArrayGotCorrectURL("starships", peopleDTO.getStarships()));
                    }

                    @Test
                    @DisplayName("Check starships don't contain null values")
                    void checkStartshipsDonTContainNullValues() {
                        Assertions.assertTrue(peopleDTO.hasArrayContainsNoNullValues(peopleDTO.getStarships()));
                    }

                    @Test
                    @DisplayName("Check starships list is not null")
                    void checkStartshipsListIsNotNull() {
                        Assertions.assertTrue(peopleDTO.isListNotNull(peopleDTO.getStarships()));

                    }
                }

                @Nested
                @Tag("datesCheck")
                class DatesTests {

                    @Nested
                    @Tag("createdCheck")
                    class CreatedTests {

                        @Test
                        @DisplayName("Check created date is not blank")
                        void checkCreatedDateIsNotBlank() {
                            Assertions.assertTrue(peopleDTO.hasAttributeNotBlank(peopleDTO.getCreated()));

                        }

                        @Test
                        @DisplayName("Check created date is not null")
                        void checkCreatedDateIsNotNull() {
                            Assertions.assertTrue(peopleDTO.hasAttributeNotNull(peopleDTO.getCreated()));
                        }

                        @Test
                        @DisplayName("Check created date is not empty")
                        void checkCreatedDateIsNotEmpty() {
                            Assertions.assertTrue(peopleDTO.hasAttributeNotEmpty(peopleDTO.getCreated()));
                        }

                        @Test
                        @DisplayName("Check creation date is not in the future")
                        void checkCreationDateIsNotInTheFuture() {
                            Assertions.assertTrue(peopleDTO.hasPastDate(peopleDTO.getCreated()));
                        }
                    }

                    @Nested
                    @Tag("editedCheck")
                    class EditedTests {

                        @Test
                        @DisplayName("Check edited date is not blank")
                        void checkCreatedDateIsNotBlank() {
                            Assertions.assertTrue(peopleDTO.hasAttributeNotBlank(peopleDTO.getEdited()));
                        }

                        @Test
                        @DisplayName("Check edited date is not null")
                        void checkCreatedDateIsNotNull() {
                            Assertions.assertTrue(peopleDTO.hasAttributeNotNull(peopleDTO.getEdited()));
                        }

                        @Test
                        @DisplayName("Check edited date is not empty")
                        void checkCreatedDateIsNotEmpty() {
                            Assertions.assertTrue(peopleDTO.hasAttributeNotEmpty(peopleDTO.getEdited()));
                        }

                        @Test
                        @DisplayName("Check edited date is not in the future")
                        void checkEditedDateIsNotInTheFuture() {
                            Assertions.assertTrue(peopleDTO.hasPastDate(peopleDTO.getEdited()));
                        }

                        @Test
                        @DisplayName("Check edited date is not before created date")
                        void checkEditedDateIsNotBeforeCreatedDate() {
                            Assertions.assertTrue(peopleDTO.hasLogicalEditedDate());
                        }
                    }
                }

                @Nested
                @Tag("urlCheck")
                class UrlTests{
                    @Test
                    @DisplayName("Check that url return a 200 status code")
                    void checkThatUrlReturnA200StatusCode() {
                        Assertions.assertTrue(peopleDTO.isURLStatusCode200(peopleDTO.getUrl()));
                    }

                    @Test
                    @DisplayName("Check url goes to people resource")
                    void checkUrlGoesToPeopleResource() {
                        Assertions.assertTrue(peopleDTO.hasCorrectURL("people", peopleDTO.getUrl()));
                    }

                }
            }

        }
    }





