package com.np.library.service.imp;

import com.np.library.domain.BookItem;
import com.np.library.domain.LibraryUser;
import com.np.library.domain.Loan;
import com.np.library.domain.LoanItem;
import com.np.library.domain.enumeration.BookStatus;
import com.np.library.domain.enumeration.LoanItemStatus;
import com.np.library.domain.enumeration.LoanStatus;
import com.np.library.repository.BookItemRepository;
import com.np.library.repository.LibraryUserRepository;
import com.np.library.repository.LoanItemRepository;
import com.np.library.repository.LoanRepository;
import com.np.library.service.BookItemService;
import com.np.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LoanServiceImp implements LoanService {
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LibraryUserRepository libraryUserRepository;
    @Autowired
    private BookItemRepository bookItemRepository;
    @Autowired
    private LoanItemRepository loanItemRepository;
    @Override
    public void createLoan(Loan loan) {
        if(loan==null){
            throw new IllegalArgumentException("The loan must not be a null");
        }
        loan.setStatus(LoanStatus.ACTIVE);
        Optional<LibraryUser> optionalLibraryUser=libraryUserRepository.findById(loan.getLibraryUser().getId());
        if(!optionalLibraryUser.isPresent()){
            throw new RuntimeException("The library user with this id does not exist");
        }
        loanRepository.save(loan);
        Set<LoanItem> newLoanItems=new HashSet<>();
        Set<LoanItem> loanItems=loan.getLoanItems();
        for(LoanItem li:loanItems){
            Optional<BookItem> optionalBookItem=bookItemRepository.findById(li.getBookItem().getId());
            if(!optionalBookItem.isPresent()){
                throw new RuntimeException("The book item with this id does not exist");
            }
            optionalBookItem.get().setBookStatus(BookStatus.BORROWED);
            li.setStatus(LoanItemStatus.ACTIVE);
            li.setDescription(null);
            li.setLoan(loan);
            newLoanItems.add(li);
            //loanItemRepository.save(li);
        }
        loanItemRepository.saveAll(newLoanItems);

    }

    @Override
    public List<Loan> getLoansByLibraryUsers(Long id) {
        Optional<List<Loan>> optionalLoans=loanRepository.findByLibraryUserId(id);
        if(!optionalLoans.isPresent()){
            throw new RuntimeException("The library with this id does not exist");
        }
        return optionalLoans.get();
    }

    @Override
    public void returnLoan(Long id) {
        Optional<Loan> loanOptional=loanRepository.findById(id);
        if(!loanOptional.isPresent()){
            throw new RuntimeException("The loan with this id does not exist");
        }
        Loan loan=loanOptional.get();
        loan.setStatus(LoanStatus.COMPLETED);
        loanRepository.save(loan);
        Set<LoanItem> loanItems=loan.getLoanItems();
        for(LoanItem loanItem:loanItems){
            loanItem.setStatus(LoanItemStatus.RETURNED);
            loanItem.setReturnDate(LocalDate.now());
            BookItem bookItem=loanItem.getBookItem();
            bookItem.setBookStatus(BookStatus.OPEN);
        }
        loanItemRepository.saveAll(loanItems);

    }
}
