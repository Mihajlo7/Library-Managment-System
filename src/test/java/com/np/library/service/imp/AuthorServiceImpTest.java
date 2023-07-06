package com.np.library.service.imp;

import com.np.library.domain.Author;
import com.np.library.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImpTest {
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImp authorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void saveAuthorValidAuthorSuccess() {
        Author author = new Author("Petar Petrovic", "Biografija");
        when(authorRepository.findByNameAndBiography(author.getName(), author.getBiography()))
                .thenReturn(Optional.empty());

        authorService.saveAuthor(author);

        verify(authorRepository,times(1)).save(author);
    }

    @Test
    public void testSaveAuthorDuplicateAuthorExceptionThrown() {

        Author existingAuthor = new Author("Petar Petrovic", "Biografija");
        when(authorRepository.findByNameAndBiography(existingAuthor.getName(), existingAuthor.getBiography()))
                .thenReturn(Optional.of(existingAuthor));

        Assertions.assertThrows(RuntimeException.class, () -> {
            authorService.saveAuthor(existingAuthor);
        });
    }

    @Test
    public void testSaveAuthorNullAuthorExceptionThrown() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            authorService.saveAuthor(null);
        });
    }

    @Test
    public void testGetAuthors_AuthorsExist_Success() {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("Petar Petrovic", "Biografija 1"));
        authors.add(new Author("Mika Mikic", "Biografija 2"));
        when(authorRepository.findAll()).thenReturn(authors);

        List<Author> result = authorService.getAuthors();

        Assertions.assertEquals(authors.size(), result.size());
        Assertions.assertEquals(authors, result);
    }

    @Test
    public void testGetAuthorsNoAuthorsExceptionThrown() {
        when(authorRepository.findAll()).thenReturn(null);

        Assertions.assertThrows(RuntimeException.class, () -> {
            authorService.getAuthors();
        });
    }

}