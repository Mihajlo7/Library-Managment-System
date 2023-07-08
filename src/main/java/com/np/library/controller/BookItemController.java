package com.np.library.controller;

import com.np.library.domain.BookItem;
import com.np.library.service.BookItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST kontroler koji obrađuje zahteve vezane za primerke knjiga.
 */
@RestController
@RequestMapping("/book-item")
public class BookItemController {
    /**
     * Instanca servisa za rad sa primercima knjiga
     */
    @Autowired
    private BookItemService bookItemService;
    /**
     * Endpoint za dodavanje primerka knjige.
     * @param isbn ISBN knjige za koju se dodaje primerak
     * @param bookItem primerak knjige koji se dodaje
     * @return odgovor sa statusom "No Content" ako je dodavanje uspesno
     */
    @PostMapping("/add/{isbn}")
    public ResponseEntity<Void> saveBookItem(@PathVariable String isbn, @RequestBody BookItem bookItem) {
        bookItemService.saveBookItem(isbn, bookItem);
        return ResponseEntity.noContent().build();
    }
    /**
     * Endpoint za dobavljanje svih primeraka knjige.
     * @param isbn ISBN knjige ciji se primerci dobavljaju
     * @return odgovor sa listom svih primeraka knjige
     */
    @GetMapping("/get/all/{isbn}")
    public ResponseEntity<List<BookItem>> getAllBookItems(@PathVariable String isbn){
        return ResponseEntity.ok(bookItemService.getAllBookItems(isbn));
    }
}
