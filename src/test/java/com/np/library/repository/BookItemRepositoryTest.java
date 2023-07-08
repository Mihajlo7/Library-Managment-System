package com.np.library.repository;

import com.np.library.domain.Book;
import com.np.library.domain.BookItem;
import com.np.library.domain.Library;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class BookItemRepositoryTest {
    @Autowired
    private BookItemRepository bookItemRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private LibraryRepository libraryRepository;

    @Test
    void findByBookIsbn() {
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setTitle("Naslov");
        Book savedBook = bookRepository.save(book);
        Library library=new Library();
        library.setName("Naziv");
        library.setCity("Grad");
        library.setAddress("Adresa");
       Library savedLibrary= libraryRepository.save(library);
        BookItem bookItem1 = new BookItem();

        bookItem1.setBook(savedBook);
        bookItem1.setLibrary(savedLibrary);
        BookItem savedBookItem1 = bookItemRepository.save(bookItem1);

        BookItem bookItem2 = new BookItem();
        bookItem2.setBook(savedBook);
        bookItem2.setLibrary(savedLibrary);
        BookItem savedBookItem2 = bookItemRepository.save(bookItem2);

        Optional<List<BookItem>> foundBookItemsOptional = bookItemRepository.findByBookIsbn(savedBook.getIsbn());

        assertTrue(foundBookItemsOptional.isPresent());
        List<BookItem> foundBookItems = foundBookItemsOptional.get();
        assertEquals(2, foundBookItems.size());
        assertTrue(foundBookItems.contains(savedBookItem1));
        assertTrue(foundBookItems.contains(savedBookItem2));
    }
}