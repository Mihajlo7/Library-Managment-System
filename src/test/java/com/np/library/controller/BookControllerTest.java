package com.np.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.np.library.domain.Book;
import com.np.library.service.AuthorService;
import com.np.library.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = BookController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveBook() throws Exception {
        Book book=new Book("111111","Naslov1","Opis 1","Publisher","Srpski",120,0,null);
        bookService.saveBook(book);
        mockMvc.perform(post("/book/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void getAllBooks() throws Exception {
        List<Book> books= Arrays.asList(
                new Book("111111","Naslov1","Opis 1","Publisher","Srpski",120,0,null),
                new Book("111222","Naslov2","Opis 2","Publisher","Srpski",130,0,null)
        );
        when(bookService.getBooks()).thenReturn(books);
        mockMvc.perform(get("/book/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].isbn",is("111111")))
                .andExpect(jsonPath("$[1].isbn",is("111222")))
                .andExpect(jsonPath("$[0].title",is("Naslov1")))
                .andExpect(jsonPath("$[1].title",is("Naslov2")))
                .andExpect(jsonPath("$[0].subject",is("Opis 1")))
                .andExpect(jsonPath("$[1].subject",is("Opis 2")))
                .andExpect(jsonPath("$[0].publisher",is("Publisher")))
                .andExpect(jsonPath("$[1].publisher",is("Publisher")))
                .andExpect(jsonPath("$[0].language",is("Srpski")))
                .andExpect(jsonPath("$[1].language",is("Srpski")))
                .andExpect(jsonPath("$[0].numberOfPages",is(120)))
                .andExpect(jsonPath("$[1].numberOfPages",is(130)));
    }

    @Test
    void getBookByIsbn() throws Exception {
        Book book=new Book("111111","Naslov1","Opis 1","Publisher","Srpski",120,0,null);
        when(bookService.getBookByIsbn("111111")).thenReturn(book);

        mockMvc.perform(get("/book/get/{isbn}","111111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isbn",is("111111")))
                .andExpect(jsonPath("$.title",is("Naslov1")))
                .andExpect(jsonPath("$.subject",is("Opis 1")))
                .andExpect(jsonPath("$.publisher",is("Publisher")))
                .andExpect(jsonPath("$.language",is("Srpski")))
                .andExpect(jsonPath("$.numberOfPages",is(120)));

    }
}