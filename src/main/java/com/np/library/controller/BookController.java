package com.np.library.controller;

import com.np.library.domain.Book;
import com.np.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST kontroler koji obrađuje zahteve vezane za knjige.
 */
@RestController
@RequestMapping("/book")
public class BookController {
    /**
     * Instanca servisa za rad sa knjigama
     */
    @Autowired
    private BookService bookService;
    /**
     * Endpoint za dodavanje knjige.
     * @param book knjiga koja se dodaje
     * @return odgovor sa statusom "No Content" ako je dodavanje uspesno
     */
    @PostMapping("/add")
    public ResponseEntity<Void> saveBook(@RequestBody Book book){
        bookService.saveBook(book);
        return ResponseEntity.noContent().build();
    }
    /**
     * Endpoint za dobavljanje svih knjiga.
     * @return odgovor sa listom svih knjiga
     */
    @GetMapping("get/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }
    /**
     * Endpoint za dobavljanje knjige po ISBN-u.
     * @param isbn ISBN knjige koja se dobavlja
     * @return odgovor sa knjigom koja ima dati ISBN
     */
    @GetMapping("get/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@RequestParam String isbn){
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }
}
