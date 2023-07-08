package com.np.library.service;

import com.np.library.domain.Book;

import java.util.List;
/**
 * Interfejs koji predstavlja servis za rad sa knjigama.
 */

public interface BookService {
    /**
     * Cuva novu knjigu.
     *
     * @param book knjiga koja se cuva
     */
    public void saveBook(Book book);
    /**
     * Vraca sve knjige.
     *
     * @return lista svih knjiga
     */
    public List<Book> getBooks();
    /**
     * Vraca knjigu sa datim ISBN brojem.
     *
     * @param isbn ISBN broj knjige
     * @return knjiga sa datim ISBN brojem
     */
    public Book getBookByIsbn(String isbn);
}
