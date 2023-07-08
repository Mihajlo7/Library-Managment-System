package com.np.library.controller;

import com.np.library.domain.Loan;
import com.np.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping("/add")
    public ResponseEntity<Void> createLoan(@RequestBody Loan loan){
        loanService.createLoan(loan);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/return/{id}")
    public ResponseEntity<Void> returnLoan(@PathVariable Long id){
        loanService.returnLoan(id);
        return ResponseEntity.noContent().build();
    }
}
