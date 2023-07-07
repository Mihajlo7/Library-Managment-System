package com.np.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import com.np.library.domain.Author;
import com.np.library.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AuthorControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveAuthor() {
    }

    @Test
    void getAllSuccess() throws Exception {
        List<Author> authors= Arrays.asList(new Author("Pera Peric","Biografija1"),new Author("Mika Mikic","Biografija2"));
        when(authorService.getAuthors()).thenReturn(authors);
        mockMvc.perform(get("author/get-all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].name",is("Pera Peric")))
                .andExpect(jsonPath("$[0].biography",is("Biografija1")))
                .andExpect(jsonPath("$[1].name",is("Mika Mikic")))
                .andExpect(jsonPath("$[1].biography",is("Biografija2")));

    }
}