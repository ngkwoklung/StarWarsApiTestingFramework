package com.sparta.jn.starwarsapitestingframework.injection;

import com.sparta.jn.starwarsapitestingframework.connection.ConnectionManager;
import com.sparta.jn.starwarsapitestingframework.dto.PeopleDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

public class InjectorTest {
    private static PeopleDTO dto;
    private static int statusCode;
    private static PeopleDTO mockPeopleDTO;

    @BeforeAll
    static void initAll() {
//        mockSpartan = Mockito.mock(Spartan.class);
//        spartan = new Spartan(1,"Manish", "Java,", LocalDate.now());
//        spySpartan = Mockito.spy(spartan); //partial Moc
        mockPeopleDTO = Mockito.mock(PeopleDTO.class);


        dto = Injector.injectActivityDTO(ConnectionManager.getConnection("people","1"));
        statusCode = ConnectionManager.getStatusCode();

    }

    @Test
    @DisplayName("Check if it returns a dto")
    void checkIfItReturnsADto() {
//        Assertions.assertTrue(dto.getClass() == true}
//        System.out.println(dto.getName());
        System.out.println(ConnectionManager.getStatusCode());
    }
}
