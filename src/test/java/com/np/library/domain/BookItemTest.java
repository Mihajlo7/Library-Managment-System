package com.np.library.domain;

import com.np.library.domain.enumeration.BookStatus;
import com.np.library.domain.enumeration.UsageStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookItemTest {
    private BookItem bookItem;

    @BeforeEach
    void setUp() {
        bookItem=new BookItem();
    }

    @AfterEach
    void tearDown() {
        bookItem=null;
    }

    @Test
    void setUsageStatus() {
        bookItem.setUsageStatus(UsageStatus.ACTIVE);
        assertEquals(UsageStatus.ACTIVE,bookItem.getUsageStatus());
    }
    @Test
    void setUsageStatusNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            bookItem.setUsageStatus(null);
        });
        assertEquals("The usageStats must not have a null value",ex.getMessage());
    }

    @Test
    void setBookStatus() {
        bookItem.setBookStatus(BookStatus.OPEN);
        assertEquals(BookStatus.OPEN,bookItem.getBookStatus());
    }
    @Test
    void setBookStatusNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            bookItem.setBookStatus(null);
        });
        assertEquals("The bookStatus must not have a null value",ex.getMessage());
    }

    @Test
    void setPurchaseDate() {
        LocalDate date=LocalDate.of(2023,06,01);
        bookItem.setPurchaseDate(date);
        assertEquals(date,bookItem.getPurchaseDate());
    }
    @Test
    void setPurchaseDateNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            bookItem.setPurchaseDate(null);
        });
        assertEquals("The purchaseDate must not have a null value",ex.getMessage());
    }
    @Test
    void setPurchaseDateInvalid() {
        LocalDate date=LocalDate.of(2023,10,01);
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            bookItem.setPurchaseDate(date);
        });
        assertEquals("The purchaseDate must be before today",ex.getMessage());
    }

    @Test
    void setBook() {
        Book book1=new Book("11111","Seobe","Roman 19 vek","Laguna","Srpski",340,0,null,null);
        bookItem.setBook(book1);
        assertEquals(book1,bookItem.getBook());
    }

    @Test
    void setLibrary() {
        Library library=new Library(null,"Vukova biblioteka","Narodnih heroja 20","Beograd","Srbija",null,null);
        bookItem.setLibrary(library);
        assertEquals(library,bookItem.getLibrary());
    }

    @Test
    void testToString() {
        Book book1=new Book("11111","Seobe","Roman 19 vek","Laguna","Srpski",340,0,null,null);
        Library library=new Library(null,"Vukova biblioteka","Narodnih heroja 20","Beograd","Srbija",null,null);
        bookItem=new BookItem(1L,UsageStatus.ACTIVE,BookStatus.OPEN,LocalDate.of(2022,2,3),book1,library,null);
        assertTrue(bookItem.toString().contains("1"));
        assertTrue(bookItem.toString().contains("ACTIVE"));
        assertTrue(bookItem.toString().contains("OPEN"));
        assertTrue(bookItem.toString().contains("2022-02-03"));
        assertTrue(bookItem.toString().contains(book1.toString()));
        assertTrue(bookItem.toString().contains(library.toString()));
    }
    @ParameterizedTest
    @CsvSource({
            "1,ACTIVE,OPEN,2023-01-01,1111,1,ACTIVE,OPEN,2023-01-01,1111,true",
            "2,ACTIVE,OPEN,2023-01-01,1111,1,ACTIVE,OPEN,2023-01-01,1111,false",
            "1,HISTORICAL,OPEN,2023-01-01,1111,1,ACTIVE,OPEN,2023-01-01,1111,false",
            "1,ACTIVE,BORROWED,2023-01-01,1111,1,ACTIVE,OPEN,2023-01-01,1111,false"
    })
    void testEquals(Long id1,String usage1,String status1,LocalDate date1,String isbn1,
                    Long id2,String usage2,String status2,LocalDate date2,String isbn2,boolean areEqual) {
        BookItem bookItem1 = new BookItem().builder()
                .id(id1)
                .usageStatus(UsageStatus.valueOf(usage1))
                .bookStatus(BookStatus.valueOf(status1))
                .purchaseDate(date1)
                .book(new Book().builder().isbn(isbn1).build()).build();

        BookItem bookItem2 = new BookItem().builder()
                .id(id2)
                .usageStatus(UsageStatus.valueOf(usage2))
                .bookStatus(BookStatus.valueOf(status2))
                .purchaseDate(date2)
                .book(new Book().builder().isbn(isbn2).build()).build();

        assertEquals(bookItem1.equals(bookItem2), areEqual);
    }
}