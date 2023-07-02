package com.np.library.domain;

import com.np.library.domain.enumeration.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LoanTest {
    private Loan loan;

    @BeforeEach
    void setUp() {
        loan=new Loan();
    }

    @AfterEach
    void tearDown() {
        loan=null;
    }

    @Test
    void setFromDate() {
        LocalDate date=LocalDate.of(2023,07,01);
        loan.setFromDate(date);
        assertEquals(date,loan.getFromDate());
    }
    @Test
    void setFromDateNull(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            loan.setFromDate(null);
        });
        assertEquals("The from date must not be a null value",ex.getMessage());

    }

    @Test
    void setToDate() {
        LocalDate date=LocalDate.of(2023,07,01);
        LocalDate date1=LocalDate.of(2023,06,01);
        loan.setFromDate(date1);
        loan.setToDate(date);
        assertEquals(date,loan.getToDate());
    }
    @Test
    void setToDateNull(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            loan.setToDate(null);
        });
        assertEquals("The from date must not be a null value",ex.getMessage());
    }
    @Test
    void setToDateFromDate(){
        LocalDate date1=LocalDate.of(2023,07,01);
        LocalDate date2=LocalDate.of(2023,8,01);
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            loan.setFromDate(date2);
            loan.setToDate(date1);
        });
        assertEquals("The from date must not be before toDate",ex.getMessage());
    }

    @Test
    void setStatus() {
        loan.setStatus(LoanStatus.ACTIVE);
        assertEquals(LoanStatus.ACTIVE,loan.getStatus());
    }
    @Test
    void setStatusNull(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            loan.setStatus(null);
        });
        assertEquals("Status must not be null",ex.getMessage());
    }

    @Test
    void setLoanItems() {
        LoanItem loanItem=new LoanItem(1L,null,null, LoanItemStatus.ACTIVE,loan,new BookItem());
        Set<LoanItem> loanItems=new HashSet<>();loanItems.add(loanItem);
        loan.setLoanItems(loanItems);
        assertEquals(loanItems,loan.getLoanItems());
    }

    @Test
    void setLibraryUser() {
        LibraryUser user1=new LibraryUser("Mika","Mikic",20, AccountStatus.ACTIVE,LocalDate.of(2022,06,04),"mika@gmail.com",null);
        loan.setLibraryUser(user1);
        assertEquals(user1,loan.getLibraryUser());
    }

    @Test
    void setLibrarian() {
        Librarian librarian=new Librarian("Mihajlo","Pavlovic", LocalDate.of(2020,06,04),null);
        loan.setLibrarian(librarian);
        assertEquals(librarian,loan.getLibrarian());
    }

    @Test
    void testToString() {
        LibraryUser user1=new LibraryUser("Mika","Mikic",20, AccountStatus.ACTIVE,LocalDate.of(2022,06,04),"mika@gmail.com",null);
        Librarian librarian=new Librarian("Mihajlo","Pavlovic", LocalDate.of(2020,06,04),null);
        loan=new Loan(1L,LocalDate.of(2023,07,01),LocalDate.of(2023,8,01),LoanStatus.ACTIVE,user1,librarian,null);
        assertTrue(loan.toString().contains("1"));
        assertTrue(loan.toString().contains("2023-07-01"));
        assertTrue(loan.toString().contains("2023-08-01"));
        assertTrue(loan.toString().contains("ACTIVE"));
        assertTrue(loan.toString().contains(user1.toString()));
        assertTrue(loan.toString().contains(librarian.toString()));

    }

    @ParameterizedTest
    @CsvSource({
            "1,2023-07-01,2023-08-01,ACTIVE,Mika,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Mihajlo,Pavlovic,2020-06-04," +
                    "1,2023-07-01,2023-08-01,ACTIVE,Mika,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Mihajlo,Pavlovic,2020-06-04,true",
            "1,2023-07-01,2023-08-01,ACTIVE,Mika,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Mihajlo,Pavlovic,2020-06-04," +
                    "2,2023-07-01,2023-08-01,ACTIVE,Mika,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Mihajlo,Pavlovic,2020-06-04,false",
            "1,2023-07-01,2023-08-01,ACTIVE,Pera,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Mihajlo,Pavlovic,2020-06-04," +
                    "1,2023-07-01,2023-08-01,ACTIVE,Mika,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Mihajlo,Pavlovic,2020-06-04,false",
            "1,2023-07-01,2023-08-01,ACTIVE,Mika,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Pavle,Pavlovic,2020-06-04," +
                    "1,2023-07-01,2023-08-01,ACTIVE,Mika,Mikic,20,ACTIVE,2022-06-04,mika@gmail.com,Mihajlo,Pavlovic,2020-06-04,false",
    })
    void testEquals(Long id1,LocalDate fromDate1,LocalDate toDate1,String loanStatus1,String libraryUname1,String libraryUsurname1,int age1,String libraryUAccStatus1,LocalDate timeJoin1,
                    String email1,String librarianName1,String librarianSurname1,LocalDate fromDateLibrarian1,
                    Long id2,LocalDate fromDate2,LocalDate toDate2,String loanStatus2,String libraryUname2,String libraryUsurname2,int age2,String libraryUAccStatus2,LocalDate timeJoin2,
                    String email2,String librarianName2,String librarianSurname2,LocalDate fromDateLibrarian2,boolean areEqual) {
        Loan loan1=new Loan(id1,fromDate1,toDate1,LoanStatus.valueOf(loanStatus1),
                new LibraryUser(libraryUname1,libraryUsurname1,age1,AccountStatus.valueOf(libraryUAccStatus1),timeJoin1,email1,null),
                new Librarian(librarianName1,librarianSurname1,fromDateLibrarian1,null),null);

        Loan loan2=new Loan(id2,fromDate2,toDate2,LoanStatus.valueOf(loanStatus2),
                new LibraryUser(libraryUname2,libraryUsurname2,age2,AccountStatus.valueOf(libraryUAccStatus2),timeJoin2,email2,null),
                new Librarian(librarianName2,librarianSurname2,fromDateLibrarian2,null),null);
        assertEquals(loan1.equals(loan2),areEqual);
    }
}