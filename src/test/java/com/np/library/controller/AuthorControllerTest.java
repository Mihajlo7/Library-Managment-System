package com.np.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import com.np.library.domain.Author;
import com.np.library.service.AuthorService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = AuthorController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Author author;
    @BeforeEach
    public void init(){
       author=new Author("Pera Peric","Biografija");
    }

    @Test
    public void getAllAuthorsSuccess() throws Exception {
        List<Author> authors=Arrays.asList(
                new Author("Autor 1","Biografija 1"),
                new Author("Autor 2","Biografija 2"));
        when(authorService.getAuthors()).thenReturn(authors);

        mockMvc.perform(get("/author/get-all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name",is("Autor 1")))
                .andExpect(jsonPath("$[0].biography",is("Biografija 1")))
                .andExpect(jsonPath("$[1].name",is("Autor 2")))
                .andExpect(jsonPath("$[1].biography",is("Biografija 2")));
    }

    @Test
    public void saveAuthorSuccess() throws Exception{
        Author author= new Author("Autor 1","Biografija 1");
        authorService.saveAuthor(author);
        mockMvc.perform(post("/author/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(author)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}