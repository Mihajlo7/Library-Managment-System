package com.np.library.repository;

import com.np.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 Repozitorijum za pristup podacima o knjigama.
 @author Mihajlo
 */
@Repository
public interface BookRepository extends JpaRepository<Book,String> {
}
