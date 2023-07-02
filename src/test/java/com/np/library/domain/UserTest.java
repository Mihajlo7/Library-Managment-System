package com.np.library.domain;

import com.np.library.domain.enumeration.UserRole;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        user=new User();
    }

    @AfterEach
    void tearDown() {
        user=null;
    }

    @Test
    void setUsername() {
        user.setUsername("miha123");
        assertEquals("miha123",user.getUsername());
    }
    @Test
    void setUsernameNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            user.setUsername(null);
        });
        assertEquals("The username must not be a null value",ex.getMessage());
    }
    @Test
    void setUsernameEmpty() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            user.setUsername("");
        });
        assertEquals("The username must not be a empty value",ex.getMessage());
    }

    @Test
    void setPassword() {
        user.setPassword("miha123");
        assertEquals("miha123",user.getPassword());
    }
    @Test
    void setPasswordNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            user.setPassword(null);
        });
        assertEquals("The password must not be a null value",ex.getMessage());
    }
    @Test
    void setPasswordEmpty() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            user.setPassword("");
        });
        assertEquals("The password must not be a empty value",ex.getMessage());
    }

    @Test
    void setRole() {
        user.setRole(UserRole.USER);
        assertEquals(UserRole.USER,user.getRole());
    }
    @Test
    void setRoleNull() {
        Exception ex=assertThrows(IllegalArgumentException.class,()->{
            user.setRole(null);
        });
        assertEquals("The role must not be a null value",ex.getMessage());
    }

    @Test
    void setLibrary() {
        Library library=new Library(null,"Vukova biblioteka","Narodnih heroja 20","Beograd","Srbija",null,null);
        user.setLibrary(library);
        assertEquals(library,user.getLibrary());
    }

    @Test
    void testToString() {
        Library library=new Library(null,"Vukova biblioteka","Narodnih heroja 20","Beograd","Srbija",null,null);
        user=new User("miha123","miha123",1L,UserRole.USER,library);
        assertTrue(user.toString().contains("miha123"));
        assertTrue(user.toString().contains("1"));
        assertTrue(user.toString().contains("USER"));
        assertTrue(user.toString().contains(library.toString()));
    }

    @ParameterizedTest
    @CsvSource({
            "mika123,mika123,1,USER,mika123,mika123,1,USER,true",
            "mika143,mika123,1,USER,mika123,mika123,2,USER,false",
            "mika123,mika123,1,USER,mika123,eeeee,1,USER,false",
            "mika123,mika123,1,USER,mika123,mika123,1,MEMBER,false"
    })
    void testEquals(String username1,String password1,Long id1,String role1,
                    String username2,String password2,Long id2,String role2,boolean areEqual) {
        User user1=new User(username1,password1,id1,UserRole.valueOf(role1),null);
        User user2=new User(username2,password2,id2,UserRole.valueOf(role2),null);

        assertEquals(user1.equals(user2),areEqual);
    }
}