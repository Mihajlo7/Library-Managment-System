package com.np.library.controller;

import com.np.library.domain.LibraryUser;
import com.np.library.service.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library-user")
public class LibraryUserController {
    @Autowired
    private LibraryUserService libraryUserService;

    @PostMapping("/add")
    public ResponseEntity<Void> saveLibraryUser(@RequestBody LibraryUser libraryUser){
        libraryUserService.saveLibraryUser(libraryUser);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/get/all")
    public ResponseEntity<List<LibraryUser>> getAllLibraryUsers(){
        return ResponseEntity.ok(libraryUserService.getLibraryUsers());
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<LibraryUser> getLibraryUserById(@PathVariable Long id){
        return ResponseEntity.ok(libraryUserService.getLibraryUserById(id));
    }
    @GetMapping("/change/{id}/password/{newPassword}")
    public ResponseEntity<String> changePassword(@PathVariable Long id,@PathVariable String newPassword){
        return ResponseEntity.ok(libraryUserService.changePassword(newPassword,id));
    }
}
