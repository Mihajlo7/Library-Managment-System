package com.np.library.service;

import com.np.library.domain.BookItem;

import java.util.List;
import java.util.Set;

public interface BookItemService {
    public void saveBookItem(String isbn, BookItem bookItem);
    public List<BookItem> getAllBookItems(String isbn);
}
