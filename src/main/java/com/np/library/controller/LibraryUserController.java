package com.np.library.controller;

import com.np.library.domain.LibraryUser;
import com.np.library.service.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST kontroler koji obrađuje zahteve vezane za clanove biblioteke.
 */
@RestController
@RequestMapping("/library-user")
public class LibraryUserController {
    /**
     * Instanca servisa za rad sa clanovima biblioteke
     */
    @Autowired
    private LibraryUserService libraryUserService;
    /**
     * Endpoint za dodavanje korisnika biblioteke.
     * @param libraryUser korisnik biblioteke koji se dodaje
     * @return odgovor sa statusom "No Content" ako je dodavanje uspesno
     */
    @PostMapping("/add")
    public ResponseEntity<Void> saveLibraryUser(@RequestBody LibraryUser libraryUser){
        libraryUserService.saveLibraryUser(libraryUser);
        return ResponseEntity.noContent().build();
    }
    /**
     * Endpoint za dobavljanje svih korisnika biblioteke.
     * @return odgovor sa listom svih korisnika biblioteke
     */
    @GetMapping("/get/all")
    public ResponseEntity<List<LibraryUser>> getAllLibraryUsers(){
        return ResponseEntity.ok(libraryUserService.getLibraryUsers());
    }
    /**
     * Endpoint za dobavljanje korisnika biblioteke na osnovu ID-ja.
     * @param id ID korisnika biblioteke koji se dobavlja
     * @return odgovor sa korisnikom biblioteke ako postoji, inace odgovor sa statusom "Not Found"
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<LibraryUser> getLibraryUserById(@PathVariable Long id){
        return ResponseEntity.ok(libraryUserService.getLibraryUserById(id));
    }
    /**
     * Endpoint za promenu sifre korisnika biblioteke.
     * @param id ID korisnika biblioteke cija se lozinka menja
     * @param newPassword nova lozinka koja se postavlja
     * @return odgovor sa porukom o uspesnoj promeni sifre
     */
    @GetMapping("/change/{id}/password/{newPassword}")
    public ResponseEntity<String> changePassword(@PathVariable Long id,@PathVariable String newPassword){
        return ResponseEntity.ok(libraryUserService.changePassword(newPassword,id));
    }
}
