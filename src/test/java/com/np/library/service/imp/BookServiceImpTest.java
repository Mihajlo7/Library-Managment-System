package com.np.library.service.imp;

import com.np.library.domain.Author;
import com.np.library.domain.Book;
import com.np.library.repository.AuthorRepository;
import com.np.library.repository.BookItemRepository;
import com.np.library.repository.BookRepository;
import com.np.library.service.AuthorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImpTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorService authorService;

    @InjectMocks
    private BookServiceImp bookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveValidBookSuccess() {
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setTitle("Naslov 1");
        book.setLanguage("Srpski");
        book.setPublisher("Laguna");
        when(bookRepository.findById(book.getIsbn())).thenReturn(Optional.empty());
        when(bookRepository.save(book)).thenReturn(book);

        bookService.saveBook(book);

        verify(bookRepository,times(1)).save(book);
    }
    @Test
    public void saveBookDuplicateBookExceptionThrown() {
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setTitle("Naslov 1");
        book.setLanguage("Srpski");
        book.setPublisher("Laguna");
        when(bookRepository.findById(book.getIsbn())).thenReturn(Optional.of(book));

        Assertions.assertThrows(RuntimeException.class, () -> {
            bookService.saveBook(book);
        });
        verify(bookRepository, never()).save(book);
    }
    @Test
    public void saveBookWithNewAuthorsValidSaved() {
        // Arrange
        Book book = new Book();
        book.setIsbn("1234567890");
        book.setTitle("Naslov 1");
        book.setLanguage("Srpski");
        book.setPublisher("Laguna");
        Author author = new Author();
        author.setName("Petar Petrovic 1");
        author.setBiography("Biografija 1");
        Set<Author> authors = new HashSet<>();
        authors.add(author);
        book.setAuthors(authors);

        when(bookRepository.findById(book.getIsbn())).thenReturn(Optional.empty());
        when(authorService.existByNameAndBiography(author)).thenReturn(false);
        when(bookRepository.save(book)).thenReturn(book);
        doNothing().when(authorService).saveAuthor(author);

        bookService.saveBook(book);
        verify(bookRepository, times(1)).save(book);
        verify(authorService, times(1)).saveAuthor(author);


    }

    @Test
    void getBooksSuccess() {

        List<Book> books = new ArrayList<>();
        books.add(new Book("12345678","Naslov1","Roman","Laguna","Srpski",120,2,null));
        books.add(new Book("12345888","Naslov2","Roman","Laguna","Srpski",120,2,null));
        when(bookRepository.findAll()).thenReturn(books);

        List<Book> result = bookService.getBooks();

        Assertions.assertEquals(books.size(), result.size());
        Assertions.assertEquals(books, result);
    }
    @Test
    public void getBooksNoBooksExceptionThrown() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<>());

        Assertions.assertThrows(RuntimeException.class, () -> {
            bookService.getBooks();
        });
    }

    @Test
    void getBookByIsbnSuccess() {
        String isbn="1234567890";
        Book book = new Book();
        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));

        Book result = bookService.getBookByIsbn(isbn);

        Assertions.assertEquals(book,result);

    }
    @Test
    public void getBookByIsbnNonExistingBookExceptionThrown() {
        String isbn = "1234567890";
        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        Assertions.assertThrows(RuntimeException.class, () -> {
            bookService.getBookByIsbn(isbn);
        });
    }
}