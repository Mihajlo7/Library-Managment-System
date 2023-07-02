package com.np.library.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AuthorTest {
    private Author author;
    @BeforeEach
    void setUp() {
        author=new Author();
    }

    @AfterEach
    void tearDown() {
        author=null;
    }

    @Test
    void setName() {
        author.setName("Petar Petrovic");
        assertEquals("Petar Petrovic",author.getName());
    }
    @Test
    void setNameNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            author.setName(null);
        });
        assertEquals("The name must not have a null value",ex.getMessage());
    }
    @Test
    void setNameEmpty() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            author.setName("");
        });
        assertEquals("The name must not be a empty value",ex.getMessage());
    }

    @Test
    void setBiography() {
        author.setBiography("Biografija");
        assertEquals("Biografija",author.getBiography());
    }

    @Test
    void setBooks() {
        Set<Book> books=new HashSet<>();
        books.add(new Book().builder().
                isbn("11111").
                authors(null).
                title("Knjiga 1").
                publisher("Laguna").build());
        author.setBooks(books);
        assertEquals(books,author.getBooks());
    }

    @Test
    void testToString() {
        Author a=new Author(1l,"Pera Peric","Biografija",null);
        assertTrue(a.toString().contains("1"));
        assertTrue(a.toString().contains("Pera Peric"));
        assertTrue(a.toString().contains("Biografija"));
    }

    @ParameterizedTest
    @CsvSource({
            "1,Mika Mikic,Biografija,1,Mika Mikic,Biografija,true",
            "1,Mika Mikic,Biografija,2,Mika Mikic,Biografija,false",
            "1,Mika Peric,Biografija,1,Mika Mikic,Biografija,false",
            "1,Mika Mikic,Bio,1,Mika Mikic,Biografija,false"
    })
    void testEquals(Long id1,String name1,String bio1,
                    Long id2,String name2,String bio2,boolean areEqual) {
        author=new Author(id1,name1,bio1,null);
        Author author1=new Author(id2,name2,bio2,null);
        assertEquals(author.equals(author1),areEqual);
    }
}