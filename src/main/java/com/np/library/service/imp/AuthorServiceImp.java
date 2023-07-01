package com.np.library.service.imp;

import com.np.library.domain.Author;
import com.np.library.repository.AuthorRepository;
import com.np.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
}
