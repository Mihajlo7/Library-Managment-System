package com.np.library.service;

import com.np.library.domain.Author;

import java.util.List;
import java.util.NoSuchElementException;

public interface AuthorService {
    public void saveAuthor(Author author);
    public List<Author> getAuthors();

    Author findByNameAndBiography(Author a)throws NoSuchElementException;
    public boolean existByNameAndBiography(Author a);
}
