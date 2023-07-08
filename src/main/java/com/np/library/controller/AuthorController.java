package com.np.library.controller;

import com.np.library.domain.Author;
import com.np.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST kontroler koji obrađuje zahteve vezane za autore.
 */
@RestController
@RequestMapping("/author")
public class AuthorController {
    /**
     * instanca servisa za rad sa autorima
     */
    @Autowired
    private AuthorService authorService;
    /**
     * Endpoint za dodavanje autora.
     * @param author autor koji se dodaje
     * @return odgovor sa statusom "No Content" ako je dodavanje uspešno
     */
    @PostMapping("/add")
    public ResponseEntity<Void> saveAuthor(@RequestBody Author author){
        authorService.saveAuthor(author);
        return ResponseEntity.noContent().build();
    }
    /**
     * Endpoint za dobavljanje svih autora.
     * @return odgovor sa listom svih autora
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<Author>> getAll(){
        return ResponseEntity.ok(authorService.getAuthors());
    }
}
