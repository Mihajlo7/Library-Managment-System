package com.np.library.repository;

import com.np.library.domain.Librarian;
import com.np.library.domain.Library;
import com.np.library.domain.LibraryUser;
import com.np.library.domain.Loan;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class LoanRepositoryTest {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LibraryUserRepository libraryUserRepository;
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private LibrarianRepository librarianRepository;
    @Test
    void findByLibraryUserId() {
        Library library=new Library();
        Librarian librarian=new Librarian();
        Library savedLibrary=libraryRepository.save(library);
        librarian.setLibrary(savedLibrary);
        Librarian savedLibrarian=librarianRepository.save(librarian);
        LibraryUser libraryUser=new LibraryUser();
        libraryUser.setLibrary(savedLibrary);
        Loan loan1=new Loan();
        LibraryUser savedLibraryUser=libraryUserRepository.save(libraryUser);
        loan1.setLibraryUser(savedLibraryUser);
        loan1.setLibrarian(savedLibrarian);
        Set<Loan> loans=Set.of(loan1);
        List<Loan> savedLoans=loanRepository.saveAll(loans);

        Optional<List<Loan>> optionalLoans=loanRepository.findByLibraryUserId(savedLibraryUser.getId());

        assertTrue(optionalLoans.isPresent());
        assertEquals(optionalLoans.get().size(),1);
        assertTrue(optionalLoans.get().contains(loan1));
    }
}