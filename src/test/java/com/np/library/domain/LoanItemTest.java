package com.np.library.domain;

import com.np.library.domain.enumeration.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LoanItemTest {
    private LoanItem loanItem;

    @BeforeEach
    void setUp() {
       loanItem=new LoanItem();
    }

    @AfterEach
    void tearDown() {
        loanItem=null;

    }

    @Test
    void setReturnDate() {
        LocalDate date=LocalDate.of(2023,07,01);
        loanItem.setStatus(LoanItemStatus.RETURNED);
        loanItem.setReturnDate(date);
        assertEquals(date,loanItem.getReturnDate());
    }
    @Test
    void setReturnDate1() {
        LocalDate date=LocalDate.of(2023,07,01);
        loanItem.setStatus(LoanItemStatus.ACTIVE);
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            loanItem.setReturnDate(date);
        });
        assertEquals("The return date must be null, because loan item is active",ex.getMessage());
    }
    @Test
    void setReturnDate2() {
        LocalDate date=LocalDate.of(2023,07,01);
        loanItem.setStatus(LoanItemStatus.RETURNED);
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            loanItem.setReturnDate(null);
        });
        assertEquals("The return date must not be null, because loan item is returned",ex.getMessage());
    }

    @Test
    void setDescription() {
        loanItem.setStatus(LoanItemStatus.RETURNED);
        loanItem.setDescription("Opis");
        assertEquals("Opis",loanItem.getDescription());
    }
    @Test
    void setDescription1() {
        loanItem.setStatus(LoanItemStatus.ACTIVE);
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            loanItem.setDescription("Opis1");
        });
        assertEquals("The description must be null, because loan item is active",ex.getMessage());
    }
    @Test
    void setDescription2() {
        loanItem.setStatus(LoanItemStatus.RETURNED);
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            loanItem.setDescription(null);
        });
        assertEquals("The description must not be null, because loan item is returned",ex.getMessage());
    }

    @Test
    void setStatus() {
        loanItem.setStatus(LoanItemStatus.RETURNED);
        assertEquals(LoanItemStatus.RETURNED,loanItem.getStatus());
    }

    @Test
    void setLoan() {
        Loan loan=new Loan(1L,LocalDate.now(),LocalDate.of(2023,9,02), LoanStatus.ACTIVE,new LibraryUser(),new Librarian(),null);
        loanItem.setLoan(loan);
        assertEquals(loan,loanItem.getLoan());
    }

    @Test
    void testToString() {
        Book book1=new Book("11111","Seobe","Roman 19 vek","Laguna","Srpski",340,0,null,null);
        Library library=new Library(null,"Vukova biblioteka","Narodnih heroja 20","Beograd","Srbija",null,null);
        BookItem bookItem=new BookItem(1L, UsageStatus.ACTIVE, BookStatus.OPEN,LocalDate.of(2022,2,3),book1,library,null);
        Loan loan=new Loan(1L,LocalDate.now(),LocalDate.of(2023,9,02), LoanStatus.ACTIVE,new LibraryUser(),new Librarian(),null);
        LoanItem loanItem1=new LoanItem(1L,LocalDate.of(2023,01,01),"Knjiga je vracena",LoanItemStatus.ACTIVE,loan,bookItem);
        assertTrue(loanItem1.toString().contains("1"));
        assertTrue(loanItem1.toString().contains("2023-01-01"));
        assertTrue(loanItem1.toString().contains("Knjiga je vracena"));
        assertTrue(loanItem1.toString().contains("ACTIVE"));
        assertTrue(loanItem1.toString().contains(loan.toString()));
        assertTrue(loanItem1.toString().contains(bookItem.toString()));

    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2023-07-01,2023-08-01,ACTIVE,Mika,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Mihajlo,Pavlovic,2020-06-04," +
                    "1,1,2023-07-01,2023-08-01,ACTIVE,Mika,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Mihajlo,Pavlovic,2020-06-04,true",
            "1,1,2023-07-01,2023-08-01,ACTIVE,Mika,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Mihajlo,Pavlovic,2020-06-04," +
                    "2,1,2023-07-01,2023-08-01,ACTIVE,Mika,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Mihajlo,Pavlovic,2020-06-04,false"
    })
    void testEquals(Long loanItemId1,Long id1,LocalDate fromDate1,LocalDate toDate1,String loanStatus1,String libraryUname1,String libraryUsurname1,int age1,String libraryUAccStatus1,LocalDate timeJoin1,
                    String email1,String librarianName1,String librarianSurname1,LocalDate fromDateLibrarian1,
                    Long loanItemId2,Long id2,LocalDate fromDate2,LocalDate toDate2,String loanStatus2,String libraryUname2,String libraryUsurname2,int age2,String libraryUAccStatus2,LocalDate timeJoin2,
                    String email2,String librarianName2,String librarianSurname2,LocalDate fromDateLibrarian2,boolean areEqual) {

        Loan loan1=new Loan(id1,fromDate1,toDate1,LoanStatus.valueOf(loanStatus1),
                new LibraryUser(libraryUname1,libraryUsurname1,age1, AccountStatus.valueOf(libraryUAccStatus1),timeJoin1,email1,null),
                new Librarian(librarianName1,librarianSurname1,fromDateLibrarian1,null),null);

        Loan loan2=new Loan(id2,fromDate2,toDate2,LoanStatus.valueOf(loanStatus2),
                new LibraryUser(libraryUname2,libraryUsurname2,age2,AccountStatus.valueOf(libraryUAccStatus2),timeJoin2,email2,null),
                new Librarian(librarianName2,librarianSurname2,fromDateLibrarian2,null),null);

        LoanItem loanItem1=new LoanItem();
        loanItem1.setId(loanItemId1);
        loanItem1.setLoan(loan1);

        LoanItem loanItem2=new LoanItem();
        loanItem2.setId(loanItemId2);
        loanItem2.setLoan(loan2);

        assertEquals(loanItem1.equals(loanItem2),areEqual);

    }
}