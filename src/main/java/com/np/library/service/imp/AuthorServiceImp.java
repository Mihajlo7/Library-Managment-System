package com.np.library.service.imp;

import com.np.library.domain.Author;
import com.np.library.repository.AuthorRepository;
import com.np.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImp implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public void saveAuthor(Author author) {
        if(author==null){
            throw new IllegalArgumentException("Author must not be null");
        }
//        if(authorRepository.existsById(author.getId())){
//            throw new IllegalArgumentException("This author exist in database");
//        }
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
