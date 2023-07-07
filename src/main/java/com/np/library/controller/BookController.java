package com.np.library.controller;

import com.np.library.domain.Book;
import com.np.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping("/add")
    public ResponseEntity<Void> saveBook(@RequestBody Book book){
        bookService.saveBook(book);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("get/all")
    public ResponseEntity<List<Book>> getAllBooks(){
        return ResponseEntity.ok(bookService.getBooks());
    }
    @GetMapping("get/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn){
        return ResponseEntity.ok(bookService.getBookByIsbn(isbn));
    }
}
