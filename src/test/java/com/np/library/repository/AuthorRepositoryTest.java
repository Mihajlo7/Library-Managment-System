package com.np.library.repository;

import com.np.library.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;


    @Test
    void findByNameAndBiographySuccess() {
        Author author = new Author();
        author.setName("Mihajlo Pavlovic");
        author.setBiography("Biografija");
        Author savedAuthor = authorRepository.save(author);

        Optional<Author> foundAuthorOptional = authorRepository.findByNameAndBiography("Mihajlo Pavlovic", "Biografija");

        assertTrue(foundAuthorOptional.isPresent());
        Author foundAuthor = foundAuthorOptional.get();
        assertEquals(savedAuthor.getId(), foundAuthor.getId());
        assertEquals(savedAuthor.getName(), foundAuthor.getName());
        assertEquals(savedAuthor.getBiography(), foundAuthor.getBiography());
    }

    @Test
    void existsByNameAndBiographyTrue() {
        Author author = new Author();
        author.setName("Mihajlo Pavlovic");
        author.setBiography("Biografija");
        authorRepository.save(author);

        boolean exists = authorRepository.existsByNameAndBiography("Mihajlo Pavlovic", "Biografija");

        assertTrue(exists);
    }

    @Test
    void existsByNameAndBiographyFalse() {
        boolean exists = authorRepository.existsByNameAndBiography("Jane Smith", "Biografija2");

        assertFalse(exists);
    }
}