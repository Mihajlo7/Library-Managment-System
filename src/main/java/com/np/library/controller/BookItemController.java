package com.np.library.controller;

import com.np.library.domain.BookItem;
import com.np.library.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book-item")
public class BookItemController {
    @Autowired
    private BookItemService bookItemService;

    @PostMapping("/add/{isbn}")
    public ResponseEntity<Void> saveBookItem(@PathVariable String isbn, @RequestBody BookItem bookItem) {
        bookItemService.saveBookItem(isbn, bookItem);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/get/all/{isbn}")
    public ResponseEntity<List<BookItem>> getAllBookItems(@PathVariable String isbn){
        return ResponseEntity.ok(bookItemService.getAllBookItems(isbn));
    }
}
