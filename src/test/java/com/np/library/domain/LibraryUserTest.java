package com.np.library.domain;

import com.np.library.domain.enumeration.AccountStatus;
import com.np.library.domain.enumeration.LoanStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LibraryUserTest {
    private LibraryUser libraryUser;

    @BeforeEach
    void setUp() {
        libraryUser=new LibraryUser();
    }

    @AfterEach
    void tearDown() {
        libraryUser=null;
    }

    @Test
    void setName() {
        libraryUser.setName("Petar");
        assertEquals("Petar",libraryUser.getName());
    }

    @Test
    void setNameNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            libraryUser.setName(null);
        });
        assertEquals("The name must not have a null value",ex.getMessage());
    }
    @Test
    void setNameEmpty() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            libraryUser.setName("");
        });
        assertEquals("The name must not be a empty value",ex.getMessage());
    }
    @Test
    void setSurname() {
        libraryUser.setSurname("Petrovic");
        assertEquals("Petrovic",libraryUser.getSurname());
    }
    @Test
    void setSurnameNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            libraryUser.setSurname(null);
        });
        assertEquals("The surname must not have a null value",ex.getMessage());
    }
    @Test
    void setSurnameEmpty() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            libraryUser.setSurname("");
        });
        assertEquals("The surname must not be a empty value",ex.getMessage());
    }

    @Test
    void setAge() {
        libraryUser.setAge(19);
        assertEquals(19,libraryUser.getAge());
    }
    @Test
    void setAgeUnder(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            libraryUser.setAge(2);
        });
        assertEquals("The member of library must have more than 10 year",ex.getMessage());
    }

    @Test
    void setStatus() {
        libraryUser.setStatus(AccountStatus.ACTIVE);
        assertEquals(AccountStatus.ACTIVE,libraryUser.getStatus());
    }
    @Test
    void setStatusNull(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            libraryUser.setStatus(null);
        });
        assertEquals("Status must not be null",ex.getMessage());
    }

    @Test
    void setTimeJoined() {
        LocalDate date=LocalDate.of(2023,01,01);
        libraryUser.setTimeJoined(date);
        assertEquals(date,libraryUser.getTimeJoined());
    }
    @Test
    void setTimeJoinNull(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            libraryUser.setTimeJoined(null);
        });
        assertEquals("TimeJoined must not be null",ex.getMessage());
    }

    @Test
    void setEmail() {
        libraryUser.setEmail("pera@gmail.com");
        assertEquals("pera@gmail.com",libraryUser.getEmail());
    }
    @Test
    void setEmailNull(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            libraryUser.setEmail(null);
        });
        assertEquals("The email must not have a null value",ex.getMessage());
    }
    @Test
    void setEmailEmpty(){
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            libraryUser.setEmail("");
        });
        assertEquals("The email must not be a empty value",ex.getMessage());
    }


    @Test
    void testToString() {
        LibraryUser user1=new LibraryUser("Mika","Mikic",20, AccountStatus.ACTIVE,LocalDate.of(2022,06,04),"mika@gmail.com",null);
        assertTrue(user1.toString().contains("Mika"));
        assertTrue(user1.toString().contains("Mikic"));
        assertTrue(user1.toString().contains("20"));
        assertTrue(user1.toString().contains("ACTIVE"));
        assertTrue(user1.toString().contains("2022-06-04"));
        assertTrue(user1.toString().contains("mika@gmail.com"));

    }

    @Test
    void setLoans() {
        Loan loan=new Loan(1L,LocalDate.now(),LocalDate.of(2023,9,02), LoanStatus.ACTIVE,libraryUser,new Librarian(),null);
        Set<Loan> loans=new HashSet<>();loans.add(loan);
        libraryUser.setLoans(loans);
        assertEquals(loans,libraryUser.getLoans());
    }

    @ParameterizedTest
    @CsvSource({
            "Mika,Mikic,20,ACTIVE,2020-01-01,mika@gmail.com,Mika,Mikic,20,ACTIVE,2020-01-01,mika@gmail.com,true",
            "Mika,Mikic,20,ACTIVE,2020-01-01,mika@gmail.com,Pera,Mikic,20,ACTIVE,2020-01-01,mika@gmail.com,false",
            "Mika,Mikic,20,CLOSED,2020-01-01,mika@gmail.com,Mika,Mikic,20,ACTIVE,2020-01-01,mika@gmail.com,false",
            "Mika,Mikic,20,ACTIVE,2022-01-01,mika@gmail.com,Mika,Mikic,20,ACTIVE,2020-01-01,mika@gmail.com,false",
            "Mika,Mikic,20,ACTIVE,2020-01-01,mika@gmail.com,Mika,Mikic,23,ACTIVE,2020-01-01,mika@gmail.com,false"
    })
    void testEquals(String name1,String surname1,int age1,String status1,LocalDate date1,String email1,
                    String name2,String surname2,int age2,String status2,LocalDate date2,String email2,boolean areEqual) {
        LibraryUser libraryUser1=new LibraryUser(name1,surname1,age1,AccountStatus.valueOf(status1),date1,email1,null);
        LibraryUser libraryUser2=new LibraryUser(name2,surname2,age2,AccountStatus.valueOf(status2),date2,email2,null);
        assertEquals(libraryUser1.equals(libraryUser2),areEqual);
    }
}