package com.np.library.repository;

import com.np.library.domain.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 Repozitorijum za pristup podacima o primercima knjiga.
 @author Mihajlo
*/
@Repository
public interface BookItemRepository extends JpaRepository<BookItem,Long> {

    /**
    Pronalazi primerke knjiga na osnovu ISBN broja knjige.
    @param isbn ISBN broj knjige
    @return Optional objekat koji sadrži listu primeraka knjiga ako postoje, inace prazan Optional
    */
    Optional<List<BookItem>> findByBookIsbn(String isbn);
}
