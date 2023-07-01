package com.np.library.controller;

import com.np.library.service.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library-user")
public class LibraryUserController {
    @Autowired
    private LibraryUserService libraryUserService;
}
