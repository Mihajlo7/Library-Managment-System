package com.np.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.np.library.domain.*;
import com.np.library.domain.enumeration.*;
import com.np.library.service.LoanService;
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
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = LoanController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class LoanControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private LoanService loanService;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createLoan() throws Exception {
        LibraryUser libraryUser=new LibraryUser("miha123","miha123",null, UserRole.MEMBER
                ,new Library(1L,"Library","Adress","City","Country",null,null),"Mihajlo","Pavlovic",23, AccountStatus.ACTIVE,
                LocalDate.of(2020,01,01),"miha@gmail.com",null);
        Library library=new Library(1L,"Library","Adress","City","Country",null,null);
        Librarian librarian=new Librarian();
        librarian.setId(1L);
        librarian.setLibrary(library);
        BookItem bookItem=new BookItem(1L, UsageStatus.ACTIVE,BookStatus.OPEN,LocalDate.now(),new Book("1111111"),library);
        Set<LoanItem> loanItems1=Set.of(
                new LoanItem(null,null,null,LoanItemStatus.ACTIVE,null,new BookItem())
        );
        Loan loan = new Loan();
        loan.setLoanItems(null);
        loan.setFromDate(LocalDate.of(2022,5,4));
        loan.setToDate(LocalDate.of(2022,6,10));
        loan.setStatus(LoanStatus.ACTIVE);
        loan.setLibraryUser(libraryUser);
        loan.setLibrarian(librarian);
        loan.setLoanItems(loanItems1);

        loanService.createLoan(loan);

        mockMvc.perform(post("/loan/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loan)));
    }

    @Test
    void returnLoan() throws Exception {
        Long loanId=1L;

        loanService.returnLoan(loanId);

        mockMvc.perform(get("/loan/return/{id}", loanId))
                .andExpect(status().isNoContent());
    }
}