package com.np.library.controller;

import com.np.library.domain.Author;
import com.np.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping("/add")
    public ResponseEntity<Void> saveAuthor(@RequestBody Author author){
        authorService.saveAuthor(author);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/get-all")
    public ResponseEntity<List<Author>> getAll(){
        return ResponseEntity.ok(authorService.getAuthors());
    }
}
