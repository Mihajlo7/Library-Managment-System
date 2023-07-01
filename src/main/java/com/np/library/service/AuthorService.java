package com.np.library.service;

import com.np.library.domain.Author;

import java.util.List;

public interface AuthorService {
    public void saveAuthor(Author author);
    public List<Author> getAuthors();
}
