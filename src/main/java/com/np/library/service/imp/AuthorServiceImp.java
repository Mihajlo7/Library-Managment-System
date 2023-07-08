package com.np.library.service.imp;

import com.np.library.domain.Author;
import com.np.library.repository.AuthorRepository;
import com.np.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Implementacija servisa za rad sa autorima
 */
@Service
public class AuthorServiceImp implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public void saveAuthor(Author author) {
        if(author==null){
            throw new IllegalArgumentException("Author must not be null");
        }
        Optional<Author> optionalAuthor=authorRepository.findByNameAndBiography(author.getName(),author.getBiography());
        if(optionalAuthor.isPresent()){
            throw new RuntimeException("These author exist");
        }
        authorRepository.save(author);

    }

    @Override
    public List<Author> getAuthors() throws IllegalArgumentException {
        List<Author> authors=authorRepository.findAll();
        if(authors==null){
            throw new RuntimeException("There arent any authors");
        }

        return authors;
    }

    @Override
    public Author findByNameAndBiography(Author a) throws NoSuchElementException {
        Optional<Author> optionalAuthor=authorRepository.findByNameAndBiography(a.getName(),a.getBiography());
        if(!optionalAuthor.isPresent()){
            throw new NoSuchElementException("Author with this name and biography does not exist");
        }
        return optionalAuthor.get();
    }

    @Override
    public boolean existByNameAndBiography(Author a) {
        return authorRepository.existsByNameAndBiography(a.getName(),a.getBiography());
    }
}
