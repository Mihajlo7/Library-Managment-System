package com.np.library.repository;

import com.np.library.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repozitorijum za pristup podacima o autorima.
 * @author Mihajlo
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    /**
     * Pronalazi autora na osnovu imena i biografije.
     * @param name ime autora
     * @param biography biografija autora
     * @return Optional objekat koji sadrži autora ako postoji, inace prazan Optional
     */
    Optional<Author> findByNameAndBiography(String name, String biography);
    /**

     Proverava da li postoji autor sa datim imenom i biografijom.
     @param name ime autora
     @param biography biografija autora
     @return true ako postoji autor, false inace
     */

    boolean existsByNameAndBiography(String name, String biography);
}
