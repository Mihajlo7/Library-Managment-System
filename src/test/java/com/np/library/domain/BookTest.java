package com.np.library.domain;

import com.np.library.domain.enumeration.BookStatus;
import com.np.library.domain.enumeration.UsageStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book;

    @BeforeEach
    void setUp() {
        book=new Book();
    }

    @AfterEach
    void tearDown() {
        book=null;
    }

    @Test
    void setIsbn() {
        book.setIsbn("11111");
        assertEquals("11111",book.getIsbn());
    }
    @Test
    void setIsbnNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            book.setIsbn(null);
        });
        assertEquals("The isbn must not be a null",ex.getMessage());
    }
    @Test
    void setIsbnOutOfRange() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            book.setIsbn("11");
        });
        assertEquals("Isbn must have between 3 and 12 numbers",ex.getMessage());
    }

    @Test
    void setTitle() {
        book.setTitle("Naslov");
        assertEquals("Naslov",book.getTitle());
    }
    @Test
    void setTitleNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            book.setTitle(null);
        });
        assertEquals("The title must not have a null value",ex.getMessage());
    }
    @Test
    void setTitleEmpty() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            book.setTitle("");
        });
        assertEquals("The title must not be a empty value",ex.getMessage());
    }

    @Test
    void setSubject() {
        book.setSubject("Opis");
        assertEquals("Opis",book.getSubject());
    }
    @Test
    void setSubjectNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            book.setSubject(null);
        });
        assertEquals("The subject must not have a null value",ex.getMessage());
    }
    @Test
    void setSubjectEmpty() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            book.setSubject("");
        });
        assertEquals("The subject must not be a empty value",ex.getMessage());
    }

    @Test
    void setPublisher() {
        book.setPublisher("Laguna");
        assertEquals("Laguna",book.getPublisher());
    }
    @Test
    void setPublisherNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            book.setPublisher(null);
        });
        assertEquals("The publisher must not have a null value",ex.getMessage());
    }
    @Test
    void setPublisherEmpty() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            book.setPublisher("");
        });
        assertEquals("The publisher must not be a empty value",ex.getMessage());
    }

    @Test
    void setLanguage() {
        book.setLanguage("Srpski");
        assertEquals("Srpski", book.getLanguage());
    }
    @Test
    void setLanguageNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            book.setLanguage(null);
        });
        assertEquals("The language must not have a null value",ex.getMessage());
    }
    @Test
    void setLanguageEmpty() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            book.setLanguage("");
        });
        assertEquals("The language must not be a empty value",ex.getMessage());
    }

    @Test
    void setNumberOfPages() {
        book.setNumberOfPages(120);
        assertEquals(120,book.getNumberOfPages());
    }

    @Test
    void setNumberOfPagesLessThan3() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            book.setNumberOfPages(2);
        });
        assertEquals("Book must have more than 3 pages",ex.getMessage());
    }

    @Test
    void setNumberOfItems() {
       book.setNumberOfItems(2);
       assertEquals(2,book.getNumberOfItems());
    }

    @Test
    void setAuthors() {
        HashSet<Author> authors=new HashSet<>();
        Author a=new Author(1l,"Pera Peric","Biografija",null);
        authors.add(a);
        book.setAuthors(authors);
        assertEquals(authors,book.getAuthors());
    }

    @Test
    void testToString() {
        HashSet<Author> authors1=new HashSet<>();
        Author a=new Author(1l,"Pera Peric","Biografija",null);
        authors1.add(a);
        book=new Book("11111","Seobe","Roman 19 vek","Laguna","Srpski",340,0,authors1,null);
        assertTrue(book.toString().contains("11111"));
        assertTrue(book.toString().contains("Seobe"));
        assertTrue(book.toString().contains("Roman 19 vek"));
        assertTrue(book.toString().contains("Laguna"));
        assertTrue(book.toString().contains("Srpski"));
        assertTrue(book.toString().contains("340"));
        assertTrue(book.toString().contains("0"));

    }

    @Test
    void setBookItems() {
        Set<BookItem> bookItems=new HashSet<>();
        bookItems.add(new BookItem().builder()
                .book(book)
                .id(5L)
                .library(new Library())
                .build());
        book.setBookItems(bookItems);
        assertEquals(bookItems,book.getBookItems());
    }

    @ParameterizedTest
    @CsvSource({
            "11111,Naslov,11111,Naslov,true",
            "22222,Naslov,11111,Naslov,false",
            "11111,Naslov1,11111,Naslov,false"

    })
    @Test
    void testEquals(String isbn1,String naslov1,
                    String isbn2,String naslov2,boolean areEqual) {
        book=new Book().builder()
                .isbn(isbn1)
                .title(naslov1).build();
        Book book1=new Book().builder()
                .isbn(isbn2)
                .title(naslov2).build();
        assertEquals(book.equals(book1),areEqual);
    }
}