package com.np.library.service;

import com.np.library.domain.Book;

import java.util.List;

public interface BookService {
    public void saveBook(Book book);
    public List<Book> getBooks();
    public Book getBookByIsbn(String isbn);
}
