package com.np.library.repository;

import com.np.library.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class LoanItemRepositoryTest {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LibraryUserRepository libraryUserRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Autowired
    private BookItemRepository bookItemRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LoanItemRepository loanItemRepository;

    @Test
    void findByLoanId() {
        Library library=new Library();
        Librarian librarian=new Librarian();
        Library savedLibrary=libraryRepository.save(library);
        librarian.setLibrary(savedLibrary);
        Librarian savedLibrarian=librarianRepository.save(librarian);
        LibraryUser libraryUser=new LibraryUser();
        libraryUser.setLibrary(savedLibrary);
        Loan loan=new Loan();
        LibraryUser savedLibraryUser=libraryUserRepository.save(libraryUser);
        loan.setLibraryUser(savedLibraryUser);
        loan.setLibrarian(savedLibrarian);
        loan=loanRepository.save(loan);
        Set<Loan> loans=Set.of(loan);
        List<Loan> savedLoans=loanRepository.saveAll(loans);
        Book book=new Book("1234567890");
        Book savedBook=bookRepository.save(book);
        BookItem bookItem=new BookItem();
        bookItem.setLibrary(savedLibrary);
        bookItem.setBook(savedBook);
        BookItem savedBookItem=bookItemRepository.save(bookItem);
        LoanItem loanItem=new LoanItem();
        loanItem.setBookItem(savedBookItem);
        loanItem.setLoan(loan);
        LoanItem savedLoanItem=loanItemRepository.save(loanItem);

        List<LoanItem> optionalLoanItems=loanItemRepository.findByLoanId(loan.getId());
        assertEquals(optionalLoanItems.size(),1);
        assertTrue(optionalLoanItems.contains(savedLoanItem));

    }
}