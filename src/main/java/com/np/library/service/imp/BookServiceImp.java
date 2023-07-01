package com.np.library.service.imp;

import com.np.library.domain.Author;
import com.np.library.domain.Book;
import com.np.library.repository.AuthorRepository;
import com.np.library.repository.BookRepository;
import com.np.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BookServiceImp implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Override
    public void saveBook(Book book) {
        if(book==null){
            throw new IllegalArgumentException("Book must not be null");
        }
        Optional<Book> optionalBook=bookRepository.findById(book.getIsbn());
        if(optionalBook.isPresent()){
            throw new RuntimeException("Book with these isbn already exist");
        }
        if(book.getAuthors()!=null){
            Set<Author> authors=book.getAuthors();
            for(Author a:authors){
                Optional<Author>optionalAuthor=authorRepository.findByNameAndBiography(a.getName(),a.getBiography());
                if(!optionalAuthor.isPresent()){
                    authorRepository.save(a);
                }
            }
        }
        bookRepository.save(book);
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books=bookRepository.findAll();
        if(books.size()==0){
            throw new RuntimeException("There are not any Books");
        }
        return books;
    }

    @Override
    public Book getBookByIsbn(String isbn) {
        Optional<Book> optionalBook=bookRepository.findById(isbn);
        if(!optionalBook.isPresent()){
            throw new RuntimeException("The Book with this isbn does not exist");
        }
        return optionalBook.get();
    }
}
