package com.np.library.repository;

import com.np.library.domain.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 Repozitorijum za pristup podacima o bibliotekarima.
 @author Mihajlo
 */
@Repository
public interface LibrarianRepository extends JpaRepository<Librarian,Long> {
}
