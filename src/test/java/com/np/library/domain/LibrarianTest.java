package com.np.library.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LibrarianTest {
    private Librarian librarian;

    @BeforeEach
    void setUp() {
        librarian=new Librarian();
    }

    @AfterEach
    void tearDown() {
        librarian=null;
    }

    @Test
    void setName() {
        librarian.setName("Petar");
        assertEquals("Petar",librarian.getName());
    }
    @Test
    void setNameNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            librarian.setName(null);
        });
        assertEquals("The name must not have a null value",ex.getMessage());
    }
    @Test
    void setNameEmpty() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            librarian.setName("");
        });
        assertEquals("The name must not be a empty value",ex.getMessage());
    }

    @Test
    void setSurname() {
        librarian.setSurname("Petrovic");
        assertEquals("Petrovic",librarian.getSurname());
    }
    @Test
    void setSurnameNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            librarian.setSurname(null);
        });
        assertEquals("The surname must not have a null value",ex.getMessage());
    }
    @Test
    void setSurnameEmpty() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            librarian.setSurname("");
        });
        assertEquals("The surname must not be a empty value",ex.getMessage());
    }

    @Test
    void setFromDate() {
        LocalDate date=LocalDate.of(2020,01,01);
        librarian.setFromDate(date);
        assertEquals(date,librarian.getFromDate());
    }
    @Test
    void setFromDateNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
           librarian.setFromDate(null);
        });
        assertEquals("The from date must be not a null value",ex.getMessage());
    }
    @Test
    void setFromDateisAfter() {
        LocalDate date=LocalDate.of(2024,01,01);
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            librarian.setFromDate(date);
        });
        assertEquals("The from date must be before today",ex.getMessage());
    }
    @Test
    void testToString(){
        Librarian librarian=new Librarian("Mihajlo","Pavlovic", LocalDate.of(2020,06,04),null);
        assertTrue(librarian.toString().contains("Mihajlo"));
        assertTrue(librarian.toString().contains("Pavlovic"));
        assertTrue(librarian.toString().contains("2020-06-04"));
    }

    @ParameterizedTest
    @CsvSource({
            "Pera,Peric,2020-01-01,Pera,Peric,2020-01-01,true",
            "Pera,Mikic,2020-01-01,Pera,Peric,2020-01-01,false",
            "Mika,Peric,2020-01-01,Pera,Peric,2020-01-01,false",
            "Pera,Peric,2020-01-05,Pera,Peric,2020-01-01,false"
    })
    void testEquals(String name1,String surname1,LocalDate date1,
                    String name2,String surname2,LocalDate date2,boolean areEqual) {
        librarian=new Librarian().builder()
                .name(name1)
                .surname(surname1)
                .fromDate(date1).build();
        Librarian librarian1=new Librarian().builder()
                .name(name2)
                .surname(surname2)
                .fromDate(date2).build();
        assertEquals(librarian.equals(librarian1),areEqual);
    }
}