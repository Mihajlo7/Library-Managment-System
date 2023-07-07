package com.np.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.np.library.domain.Book;
import com.np.library.domain.BookItem;
import com.np.library.domain.enumeration.BookStatus;
import com.np.library.domain.enumeration.UsageStatus;
import com.np.library.service.BookItemService;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(controllers = BookItemController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class BookItemControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private BookItemService bookItemService;
    @Autowired
    private ObjectMapper objectMapper;
    @Test
    void saveBookItem() throws Exception {
        BookItem bookItem=new BookItem(UsageStatus.ACTIVE, BookStatus.OPEN, LocalDate.now(),new Book("111111"));
        bookItemService.saveBookItem("111111",bookItem);
        mockMvc.perform(post("/book-item/add/{isbn}","111111")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(bookItem)));
    }

    @Test
    void getAllBookItems() throws Exception {
        Book book=new Book("111111");
        List<BookItem> bookItems= Arrays.asList(
                new BookItem(UsageStatus.ACTIVE,BookStatus.OPEN,LocalDate.now(),book),
                new BookItem(UsageStatus.HISTORICAL,BookStatus.OPEN,LocalDate.now(),book)
        );
        when(bookItemService.getAllBookItems("111111")).thenReturn(bookItems);

        mockMvc.perform(get("/book-item/get/all/{isbn}","111111"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].usageStatus",is("ACTIVE")))
                .andExpect(jsonPath("$[0].bookStatus",is("OPEN")))
                .andExpect(jsonPath("$[1].usageStatus",is("HISTORICAL")))
                .andExpect(jsonPath("$[1].bookStatus",is("OPEN")));
    }
}