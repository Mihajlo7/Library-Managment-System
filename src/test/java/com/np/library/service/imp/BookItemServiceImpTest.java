package com.np.library.service.imp;

import com.np.library.domain.Book;
import com.np.library.domain.BookItem;
import com.np.library.domain.enumeration.BookStatus;
import com.np.library.domain.enumeration.UsageStatus;
import com.np.library.repository.BookItemRepository;
import com.np.library.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookItemServiceImpTest {

    @Mock
    private BookItemRepository bookItemRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookItemServiceImp bookItemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveBookItemSuccess() {
        String isbn = "1234567890";
        Book book = new Book();
        BookItem bookItem = new BookItem(UsageStatus.ACTIVE, BookStatus.OPEN, LocalDate.now(),book);
        book.setIsbn(isbn);
        when(bookRepository.findById(isbn)).thenReturn(Optional.of(book));
        when(bookItemRepository.save(bookItem)).thenReturn(bookItem);

        bookItemService.saveBookItem(isbn, bookItem);

        verify(bookItemRepository,times(1)).save(bookItem);
        Assertions.assertEquals(UsageStatus.ACTIVE, bookItem.getUsageStatus());
        Assertions.assertEquals(book, bookItem.getBook());

    }
    @Test
    public void saveBookItemInvalidBookExceptionThrown() {
        String isbn = "1234567890";
        BookItem bookItem = new BookItem();
        when(bookRepository.findById(isbn)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookItemService.saveBookItem(isbn, bookItem);
        });
        verify(bookItemRepository, never()).save(bookItem);
    }

    @Test
    void getAllBookItemsSuccess() {
        String isbn = "1234567890";
        Book book = new Book();
        book.setIsbn(isbn);
        List<BookItem> bookItems = new ArrayList<>();
        bookItems.add(new BookItem(UsageStatus.ACTIVE, BookStatus.OPEN, LocalDate.of(2022,01,02),book));
        bookItems.add(new BookItem(UsageStatus.ACTIVE, BookStatus.OPEN, LocalDate.of(2023,1,3),book));
        when(bookItemRepository.findByBookIsbn(isbn)).thenReturn(Optional.of(bookItems));

        List<BookItem> result = bookItemService.getAllBookItems(isbn);

        Assertions.assertEquals(bookItems.size(), result.size());
        Assertions.assertEquals(bookItems, result);
    }
    @Test
    public void getAllBookItemsNonExistingBookExceptionThrown() {
        String isbn = "1234567890";
        when(bookItemRepository.findByBookIsbn(isbn)).thenReturn(Optional.empty());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bookItemService.getAllBookItems(isbn);
        });
    }
}