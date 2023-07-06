package com.np.library.service.imp;

import com.np.library.domain.BookItem;
import com.np.library.domain.LibraryUser;
import com.np.library.domain.Loan;
import com.np.library.domain.LoanItem;
import com.np.library.domain.enumeration.BookStatus;
import com.np.library.domain.enumeration.LoanItemStatus;
import com.np.library.domain.enumeration.LoanStatus;
import com.np.library.repository.BookItemRepository;
import com.np.library.repository.LibraryUserRepository;
import com.np.library.repository.LoanItemRepository;
import com.np.library.repository.LoanRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;

class LoanServiceImpTest {
    @Mock
    private LoanRepository loanRepository;

    @Mock
    private LibraryUserRepository libraryUserRepository;

    @Mock
    private BookItemRepository bookItemRepository;

    @Mock
    private LoanItemRepository loanItemRepository;

    @InjectMocks
    private LoanServiceImp loanService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createLoanSuccess() {
        Loan loan = new Loan();
        loan.setLibraryUser(new LibraryUser());
        loan.getLibraryUser().setId(1L);
        loan.setLoanItems(new HashSet<>());
        when(libraryUserRepository.findById(loan.getLibraryUser().getId())).thenReturn(Optional.of(new LibraryUser()));
        when(loanRepository.save(loan)).thenReturn(loan);
        when(loanItemRepository.saveAll(anyList())).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        loanService.createLoan(loan);

        // Assert
        verify(loanRepository,times(1)).save(loan);
        verify(loanItemRepository, times(1)).saveAll(anySet());
        Assertions.assertEquals(LoanStatus.ACTIVE, loan.getStatus());
    }
    @Test
    void createLoanNullExceptionThrown(){
        Loan loan=null;
        Assertions.assertThrows(IllegalArgumentException.class,()->{
            loanService.createLoan(loan);
        });
        verify(loanRepository, never()).save(loan);
        verify(loanItemRepository, never()).saveAll(anySet());
    }
    @Test
    public void createLoanInvalidLibraryUserIdExceptionThrown() {
        // Arrange
        Loan loan = new Loan();
        loan.setLibraryUser(new LibraryUser());
        loan.getLibraryUser().setId(1L);

        when(libraryUserRepository.findById(loan.getLibraryUser().getId())).thenReturn(Optional.empty());

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> {
            loanService.createLoan(loan);
        });
        verify(loanRepository, never()).save(loan);
        verify(loanItemRepository, never()).saveAll(anySet());
    }


    @Test
    void returnLoan() {
        Long loanId = 1L;
        Loan loan = new Loan();
        loan.setId(loanId);
        LoanItem loanItem = new LoanItem();
        loanItem.setBookItem(new BookItem());
        loanItem.getBookItem().setBookStatus(BookStatus.BORROWED);
        Set<LoanItem> loanItems = new HashSet<>();
        loanItems.add(loanItem);
        loan.setLoanItems(loanItems);

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
        when(loanItemRepository.findByLoanId(loanId)).thenReturn(new ArrayList<>(loanItems));
        when(loanItemRepository.saveAll(anyList())).thenAnswer(invocation -> invocation.getArgument(0));
        when(loanRepository.save(loan)).thenReturn(loan);

        loanService.returnLoan(loanId);

        verify(loanRepository, times(1)).save(loan);
        verify(loanItemRepository, times(1)).saveAll(anyList());
        Assertions.assertEquals(LoanStatus.COMPLETED, loan.getStatus());
        Assertions.assertEquals(LoanItemStatus.RETURNED, loanItem.getStatus());
        Assertions.assertEquals(LocalDate.now(), loanItem.getReturnDate());
        Assertions.assertEquals(BookStatus.OPEN, loanItem.getBookItem().getBookStatus());
    }
    @Test
    public void returnLoanNonExistingLoanExceptionThrown() {
        Long loanId = 1L;
        when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> {
            loanService.returnLoan(loanId);
        });
        verify(loanItemRepository, never()).findByLoanId(loanId);
        verify(loanItemRepository, never()).saveAll(anyList());
        verify(loanRepository, never()).save(any(Loan.class));
    }
}