package com.np.library.controller;

import com.np.library.domain.Loan;
import com.np.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
