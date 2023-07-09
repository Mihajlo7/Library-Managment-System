package com.np.library.controller;

import com.np.library.domain.Loan;
import com.np.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * REST kontroler koji obrađuje zahteve vezane za zaduzenje.
 */
@RestController
@RequestMapping("/loan")
public class LoanController {
    /**
     * Instanca servisa za rad sa zaduzivanjima
     */
    @Autowired
    private LoanService loanService;
    /**
     * Endpoint za kreiranje nove pozajmice.
     * @param loan zaduzanje koja se kreira
     * @return odgovor sa statusom "No Content" ako je kreiranje uspesno
     */
    @PostMapping("/add")
    public ResponseEntity<Void> createLoan(@RequestBody Loan loan){
        loanService.createLoan(loan);
        return ResponseEntity.noContent().build();
    }
    /**
     * Endpoint za vraćanje zaduzenje na osnovu ID-ja.
     * @param id ID zaduzenje koja se vraća
     * @return odgovor sa statusom "No Content" ako je vracanje uspesno
     */
    @GetMapping("/retunr/{id}")
    public ResponseEntity<Void> returnLoan(@PathVariable Long id){
        loanService.returnLoan(id);
        return ResponseEntity.noContent().build();
    }
}
