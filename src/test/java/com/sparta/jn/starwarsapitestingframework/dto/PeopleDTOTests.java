package com.sparta.jn.starwarsapitestingframework.dto;

import com.sparta.jn.starwarsapitestingframework.connection.ConnectionManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Nested;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.net.ConnectException;
import java.util.Arrays;
import java.util.List;

public class PeopleDTOTests {
    private PeopleDTO mockPeopleDTO;
    private ConnectionManager mockConnectionManager;


    @BeforeEach
    void init() {
        mockPeopleDTO = Mockito.mock(PeopleDTO.class);
    }


    @Test
    @DisplayName("given a mass that's larger than 0 return true")
    void givenAMassThatSLargerThan0ReturnTrue() {

        Mockito.when(mockPeopleDTO.getMass()).thenReturn("20");
        Mockito.when(mockPeopleDTO.hasMassAboveZero()).thenCallRealMethod();
        Assertions.assertTrue(mockPeopleDTO.hasMassAboveZero());

    }

    @Test
    @DisplayName("given a mass that's a negative number then return false")
    void givenAMassThatSANegativeNumberThenReturnFalse() {
        Mockito.when(mockPeopleDTO.getMass()).thenReturn("-2");
        Mockito.when(mockPeopleDTO.hasMassAboveZero()).thenCallRealMethod();
        Assertions.assertTrue(!mockPeopleDTO.hasMassAboveZero());
    }

    @Test
    @DisplayName("given mass input is letters throws exception")
    void givenMassInputIsLettersThrowsException() {

        Mockito.when(mockPeopleDTO.getMass()).thenReturn("Hello");
        Mockito.when(mockPeopleDTO.hasMassAboveZero()).thenCallRealMethod();
        Assertions.assertThrows(NumberFormatException.class, () -> mockPeopleDTO.hasMassAboveZero());

    }

    @Test
    @DisplayName("given gender is either 'Male', 'Female', 'n/a' or 'unknown' return true")
    void givenGenderIsEitherMaleFemaleNAOrUnknownReturnTrue() {
        Mockito.when(mockPeopleDTO.getGender()).thenReturn("Male");
        Mockito.when(mockPeopleDTO.hasGender()).thenCallRealMethod();
        Assertions.assertTrue(mockPeopleDTO.hasGender());
    }


    @Test
    @DisplayName("given an attribute that's empty return False")
    void givenAnAttributeThatSEmptyReturnFalse() {
        String nonEmptyString = "";
        Mockito.when(mockPeopleDTO.hasAttributeNotEmpty(nonEmptyString)).thenCallRealMethod();
        Assertions.assertFalse(mockPeopleDTO.hasAttributeNotEmpty(nonEmptyString));

    }

    @Test
    @DisplayName("given an attribute that's not empty return True")
    void givenAnAttributeThatSNotEmptyReturnTrue() {
        String nonEmptyString = "Hello";
        Mockito.when(mockPeopleDTO.hasAttributeNotEmpty(nonEmptyString)).thenCallRealMethod();
        Assertions.assertTrue(mockPeopleDTO.hasAttributeNotEmpty(nonEmptyString));
        
    }

    @Test
    @DisplayName("given an attribute that's blank return false")
    void givenAnAttributeThatSBlankReturnFalse() {
        String blankString = "             ";
        Mockito.when(mockPeopleDTO.hasAttributeNotBlank(blankString)).thenCallRealMethod();
        Assertions.assertFalse(mockPeopleDTO.hasAttributeNotBlank(blankString));

    }

    @Test
    @DisplayName("given an attribute that's not blank return true")
    void givenAnAttributeThatSNotBlankReturnTrue() {
        String blankString = "I'm not a blank attribute";
        Mockito.when(mockPeopleDTO.hasAttributeNotBlank(blankString)).thenCallRealMethod();
        Assertions.assertTrue(mockPeopleDTO.hasAttributeNotBlank(blankString));

    }

    @Test
    @DisplayName("given an attribute that's null return false")
    void givenAnAttributeThatSNullReturnFalse() {

        String nullString = null;
        Mockito.when(mockPeopleDTO.hasAttributeNotNull(nullString)).thenCallRealMethod();
        Assertions.assertFalse(mockPeopleDTO.hasAttributeNotNull(nullString));
    }

    @Test
    @DisplayName("given an attribute that's not null return true")
    void givenAnAttributeThatSNotNullReturnTrue() {
        String nullString = "Not a null value";
        Mockito.when(mockPeopleDTO.hasAttributeNotNull(nullString)).thenCallRealMethod();
        Assertions.assertTrue(mockPeopleDTO.hasAttributeNotNull(nullString));
        
    }

    @ParameterizedTest(name = "{displayName} of {argumentsWithNames}")
    @ValueSource(strings = {"220BBY","unknown","220ABY", "19000BBY"})
    @DisplayName("given a star wars birth year format return true ")
    void givenAStarWarsBirthYearFormatReturnTrue(String date) {
        Mockito.when(mockPeopleDTO.getBirthYear()).thenReturn(String.valueOf(date));
        Mockito.when(mockPeopleDTO.hasBirthYearFormat()).thenCallRealMethod();

        Assertions.assertTrue(mockPeopleDTO.hasBirthYearFormat());
    }

    @ParameterizedTest(name = "{displayName} of {argumentsWithNames}")
    @ValueSource(strings = {"2022", "Unknown", "19 BBY", "20 ABY"})
    @DisplayName("given a non starwars date format return false")
    void givenANonStarwarsDateFormatReturnFalse(String date) {
        Mockito.when(mockPeopleDTO.getBirthYear()).thenReturn(String.valueOf(date));
        Mockito.when(mockPeopleDTO.hasBirthYearFormat()).thenCallRealMethod();

        Assertions.assertFalse(mockPeopleDTO.hasBirthYearFormat());
    }

    @ParameterizedTest(name = "{displayName} of {argumentsWithNames}")
    @ValueSource(strings = {"20", "22", "1", "5", "40"})
    @DisplayName("given a measurement with a value above 0 return true")
    void givenAMeasurementWithAValueAbove0ReturnTrue(String measurements) {
        Mockito.when(mockPeopleDTO.hasMeasurementAboveZero(measurements)).thenCallRealMethod();
        Assertions.assertTrue(mockPeopleDTO.hasMeasurementAboveZero(measurements));
    }

    @ParameterizedTest(name = "{displayName} of {argumentsWithNames}")
    @ValueSource(strings = {"-23", "-228", "-17", "-45", "-30"})
    @DisplayName("given a measurement with a value below 0 return false")
    void givenAMeasurementWithAValueBelow0ReturnFalse(String measurements) {
        Mockito.when(mockPeopleDTO.hasMeasurementAboveZero(measurements)).thenCallRealMethod();
        Assertions.assertFalse(mockPeopleDTO.hasMeasurementAboveZero(measurements));
    }

    @ParameterizedTest(name = "{displayName} of {argumentsWithNames}")
    @ValueSource(strings = {"hello", "this", "is", "not", "a", "number"})
    @DisplayName("given a measurement that doesn't contain numbers return false")
    void givenAMeasurementThatDoesnTContainNumbersReturnFalse(String nonNumberMeasurements) {
        Mockito.when(mockPeopleDTO.hasMeasurementContainingNumbers(nonNumberMeasurements)).thenCallRealMethod();
        Assertions.assertFalse(mockPeopleDTO.hasMeasurementContainingNumbers(nonNumberMeasurements));
    }

    @ParameterizedTest(name = "{displayName} of {argumentsWithNames}")
    @ValueSource(strings = {"20", "30", "40", "50", "10", "17"})
    @DisplayName("given a measurement with numbers return true")
    void givenAMeasurementWithNumbersReturnTrue(String numberMeasurements) {
        Mockito.when(mockPeopleDTO.hasMeasurementContainingNumbers(numberMeasurements)).thenCallRealMethod();
        Assertions.assertTrue(mockPeopleDTO.hasMeasurementContainingNumbers(numberMeasurements));
    }

    @Test
    @Disabled
    @DisplayName("check url status code returns 200 then return true")
    void checkUrlStatusCodeReturns200ThenReturnTrue() {

        Assertions.assertTrue(mockPeopleDTO.isURLStatusCode200("http//www.google.com"));

    }

    @Test
    @Disabled
    @DisplayName("check that url array status codes return 200")
    void checkThatUrlArrayStatusCodesReturn200() {


    }

    @Test
    @DisplayName("check that if a film value is empty return false")
    void checkThatIfAFilmValueIsEmptyReturnFalse() {
        List<String> films = Arrays.asList();

        Mockito.when(mockPeopleDTO.getFilms()).thenReturn(films);
        Mockito.when(mockPeopleDTO.hasFilmEntry()).thenCallRealMethod();
        Assertions.assertFalse(mockPeopleDTO.hasFilmEntry());
    }

    @Test
    @DisplayName("check that if a film has a value return true")
    void checkThatIfAFilmHasAValueReturnTrue() {
        List<String> films = Arrays.asList("Film 1", "Film 2", "Film 3");

        Mockito.when(mockPeopleDTO.getFilms()).thenReturn(films);
        Mockito.when(mockPeopleDTO.hasFilmEntry()).thenCallRealMethod();
        Assertions.assertTrue(mockPeopleDTO.hasFilmEntry());

    }

    @Test
    @DisplayName("check that an array that has null values returns false")
    void checkThatAnArrayDoesnTHaveNullValues() {
        List<String> array = Arrays.asList(null, "film 2");
        Mockito.when(mockPeopleDTO.hasArrayContainsNoNullValues(array)).thenCallRealMethod();
        Assertions.assertFalse(mockPeopleDTO.hasArrayContainsNoNullValues(array));
    }

    @Test
    @DisplayName("check that an array that without null values returns true")
    void checkThatAnArrayThatWithoutNullValuesReturnsTrue() {
        List<String> array = Arrays.asList("film 1", "film 2");
        Mockito.when(mockPeopleDTO.hasArrayContainsNoNullValues(array)).thenCallRealMethod();
        Assertions.assertTrue(mockPeopleDTO.hasArrayContainsNoNullValues(array));

    }

    @Test
    @DisplayName("given a URL with an incorrect format return false")
    void givenAUrlWithAnIncorrectFormatReturnFalse() {
        Mockito.when(mockPeopleDTO.hasCorrectURL("people", "https://swapi.com/api/people/10")).thenCallRealMethod();
        Assertions.assertFalse(mockPeopleDTO.hasCorrectURL("people", "https://swapi.com/api/people/10"));

    }

    @Test
    @DisplayName("given a URL with the correct format return true")
    void givenAUrlWithTheCorrectFormatReturnTrue() {
        Mockito.when(mockPeopleDTO.hasCorrectURL("people", "https://swapi.dev/api/people/5")).thenCallRealMethod();
        Assertions.assertTrue(mockPeopleDTO.hasCorrectURL("people", "https://swapi.dev/api/people/5"));

    }

    @Test
    @DisplayName("given an array of URLs that have an incorrect format return false")
    void givenAnArrayOfUrLsThatHaveAnIncorrectFormatReturnFalse() {
        List<String> array = Arrays.asList("https://swapi.com/api/people/10", "https://swapi.com/api/people/AZ", "https://swapl.com/api/people/15");
        Mockito.when(mockPeopleDTO.hasArrayGotCorrectURL("people", array)).thenCallRealMethod();
        Assertions.assertFalse(mockPeopleDTO.hasArrayGotCorrectURL("people", array));
    }

    @Test
    @DisplayName("given an array of URLs that have the correct format return true")
    void givenAnArrayOfUrLsThatHaveTheCorrectFormatReturnTrue() {
        List<String> array = Arrays.asList("https://swapi.dev/api/people/10", "https://swapi.dev/api/people/10", "https://swapi.dev/api/people/10");
        Mockito.when(mockPeopleDTO.hasArrayGotCorrectURL("people", array)).thenCallRealMethod();
        Assertions.assertTrue(mockPeopleDTO.hasArrayGotCorrectURL("people", array));

    }

}
