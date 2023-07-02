package com.np.library.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LibraryTest {
    private Library library;

    @BeforeEach
    void setUp() {
        library=new Library();
    }

    @AfterEach
    void tearDown() {
        library=null;
    }

    @Test
    void setName() {
        library.setName("Biblioteka");
        assertEquals("Biblioteka",library.getName());
    }
    @Test
    void setNameNull(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            library.setName(null);
        });
        assertEquals("The name must not have a null value",ex.getMessage());
    }
    @Test
    void setNameEmpty(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            library.setName("");
        });
        assertEquals("The name must not be a empty value",ex.getMessage());
    }

    @Test
    void setAddress() {
        library.setAddress("Narodnih heroja");
        assertEquals("Narodnih heroja",library.getAddress());
    }
    @Test
    void setAddressNull(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            library.setAddress(null);
        });
        assertEquals("The address must not have a null value",ex.getMessage());
    }
    @Test
    void setAddressEmpty(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            library.setAddress("");
        });
        assertEquals("The address must not be a empty value",ex.getMessage());
    }

    @Test
    void setCity() {
        library.setCity("Beograd");
        assertEquals("Beograd",library.getCity());
    }
    @Test
    void setCityNull(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            library.setCity(null);
        });
        assertEquals("The city must not have a null value",ex.getMessage());
    }
    @Test
    void setCityEmpty(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            library.setCity("");
        });
        assertEquals("The city must not be a empty value",ex.getMessage());
    }

    @Test
    void setCountry() {
        library.setCountry("Srbija");
        assertEquals("Srbija",library.getCountry());
    }
    @Test
    void setCountryNull(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            library.setCountry(null);
        });
        assertEquals("The country must not have a null value",ex.getMessage());
    }
    @Test
    void setCountryEmpty(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            library.setCountry("");
        });
        assertEquals("The country must not be a empty value",ex.getMessage());
    }

    @Test
    void testToString() {
        library=new Library(1L,"Vukova biblioteka","Narodnih heroja 20","Beograd","Srbija",null,null);
        assertTrue(library.toString().contains("Vukova biblioteka"));
        assertTrue(library.toString().contains("Narodnih heroja 20"));
        assertTrue(library.toString().contains("Beograd"));
        assertTrue(library.toString().contains("Srbija"));
        assertTrue(library.toString().contains("1"));
    }

    @Test
    void setBookItems() {
        Set<BookItem> bookItems=new HashSet<>();
        bookItems.add(new BookItem().builder()
                .book(new Book())
                .id(5L)
                .library(new Library())
                .build());
        library.setBookItems(bookItems);
        assertEquals(bookItems,library.getBookItems());
    }

    @ParameterizedTest
    @CsvSource({
            "1,Biblioteka1,Adresa1,Grad1,Drzava1,1,Biblioteka1,Adresa1,Grad1,Drzava1,true",
            "1,Biblioteka1,Adresa1,Grad1,Drzava1,2,Biblioteka1,Adresa1,Grad1,Drzava1,false",
            "1,Biblioteka1,Adresa1,Grad1,Drzava1,1,Biblioteka2,Adresa1,Grad1,Drzava1,false",
            "1,Biblioteka1,Adresa1,Grad1,Drzava1,1,Biblioteka1,Adresa1,Grad2,Drzava1,false",
            "1,Biblioteka1,Adresa1,Grad1,Drzava1,1,Biblioteka1,Adresa1,Grad1,Drzava2,false"
    })
    void testEquals(Long id1,String name1,String address1,String city1,String country1,
                    Long id2,String name2,String address2,String city2,String country2,boolean are) {
        Library library1=new Library(id1,name1,address1,city1,country1,null,null);
        Library library2=new Library(id2,name2,address2,city2,country2,null,null);
        assertEquals(library1.equals(library2),are);

    }
}