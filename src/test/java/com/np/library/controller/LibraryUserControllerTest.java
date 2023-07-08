package com.np.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.np.library.domain.Library;
import com.np.library.domain.LibraryUser;
import com.np.library.domain.enumeration.AccountStatus;
import com.np.library.domain.enumeration.UserRole;
import com.np.library.service.LibraryUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LibraryUserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class LibraryUserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryUserService libraryUserService;

    @Autowired
    private ObjectMapper objectMapper;
    @BeforeEach
    void setUp() {
    }

    @Test
    void saveLibraryUserSuccess() throws Exception {
        LibraryUser libraryUser=new LibraryUser("miha123","miha123",null, UserRole.MEMBER
                ,new Library(1L,"Library","Adress","City","Country",null,null),"Mihajlo","Pavlovic",23, AccountStatus.ACTIVE,
                LocalDate.of(2020,01,01),"miha@gmail.com",null);
        libraryUserService.saveLibraryUser(libraryUser);

        mockMvc.perform(post("/library-user/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libraryUser)))
                        .andExpect(status().isNoContent());
    }

    @Test
    void getAllLibraryUsers() throws Exception {
        List<LibraryUser> libraryUsers= Arrays.asList(
                new LibraryUser("miha123","miha123",null, UserRole.MEMBER
                ,new Library(1L,"Library","Adress","City","Country",null,null),"Mihajlo","Pavlovic",23, AccountStatus.ACTIVE,
                LocalDate.of(2020,01,01),"miha@gmail.com",null),
                 new LibraryUser("miha1","miha1",null, UserRole.MEMBER
                ,new Library(1L,"Library","Adress","City","Country",null,null),"Mihajlo","Pavlovic",23, AccountStatus.ACTIVE,
                LocalDate.of(2020,01,01),"miha123@gmail.com",null)

        );
        when(libraryUserService.getLibraryUsers()).thenReturn(libraryUsers);

        mockMvc.perform(get("/library-user/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(libraryUsers.size())))
                .andExpect(jsonPath("$[0].username",is("miha123")))
                .andExpect(jsonPath("$[0].password",is("miha123")))
                .andExpect(jsonPath("$[0].email",is("miha@gmail.com")))
                .andExpect(jsonPath("$[1].username",is("miha1")))
                .andExpect(jsonPath("$[1].password",is("miha1")))
                .andExpect(jsonPath("$[1].email",is("miha123@gmail.com")));
    }

    @Test
    void getLibraryUserById() throws Exception {
        Long userId = 3L;
        LibraryUser libraryUser = new LibraryUser();

        when(libraryUserService.getLibraryUserById(userId)).thenReturn(libraryUser);

        mockMvc.perform(get("/library-user/get/{id}", userId));
    }

    @Test
    void changePassword() throws Exception {
        Long userId = 1L;
        String newPassword = "newPassword";
        String expectedResponse = "Password changed successfully.";

        when(libraryUserService.changePassword(newPassword, userId)).thenReturn(expectedResponse);

        mockMvc.perform(get("/library-user/change/{id}/password/{newPassword}", userId, newPassword))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(expectedResponse)));
    }
}