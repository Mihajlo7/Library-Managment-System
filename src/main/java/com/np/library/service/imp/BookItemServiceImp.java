package com.np.library.service.imp;

import com.np.library.domain.Book;
import com.np.library.domain.BookItem;
import com.np.library.domain.enumeration.UsageStatus;
import com.np.library.repository.BookItemRepository;
import com.np.library.repository.BookRepository;
import com.np.library.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookItemServiceImp implements BookItemService {
    @Autowired
    private BookItemRepository bookItemRepository;
    @Autowired
    private BookRepository bookRepository;
    @Override
    public void saveBookItem(String isbn, BookItem bookItem) {
        Optional<Book> optionalBook=bookRepository.findById(isbn);
        if(!optionalBook.isPresent()){
            throw new IllegalArgumentException("The book with this id does not exist");
        }
        Book book=optionalBook.get();
        bookItem.setBook(book);
        bookItem.setUsageStatus(UsageStatus.ACTIVE);
        bookItemRepository.save(bookItem);

    }

    @Override
    public List<BookItem> getAllBookItems(String isbn) {
        Optional<List<BookItem>> optionalBookItems=bookItemRepository.findByBookIsbn(isbn);
        if(!optionalBookItems.isPresent()){
            throw new IllegalArgumentException("The book with this isbn deos not exist");
        }
        return optionalBookItems.get();
    }
}
