package com.np.library.service;

import com.np.library.domain.BookItem;

import java.util.List;
import java.util.Set;
/**
 * Interfejs servisa za rad sa primerkom knjige.
 */
public interface BookItemService {
    /**
     * Cuva novi primerak knjige za datu ISBN.
     *
     * @param isbn ISBN knjige
     * @param bookItem primerak knjige koji se cuva
     */
    public void saveBookItem(String isbn, BookItem bookItem);
    /**
     * Vraca sve primerke knjige sa datim ISBN.
     *
     * @param isbn ISBN knjige
     * @return lista svih primeraka knjige
     */
    public List<BookItem> getAllBookItems(String isbn);
}
