package com.np.library.service;

import com.np.library.domain.Author;

import java.util.List;
import java.util.NoSuchElementException;
/**
 * Interfejs servisa za rad sa autorima.
 */
public interface AuthorService {
    /**
     * Cuva novog autora.
     *
     * @param author autor koji se cuva
     * @throws IllegalArgumentException ako je autor null
     * @throws RuntimeException ako autor vec postoji
     */
    public void saveAuthor(Author author);
    /**
     * Vraca sve autore.
     *
     * @return lista autora
     * @throws RuntimeException ako nema dostupnih autora
     */

    public List<Author> getAuthors();
    /**
     * Pronalazi autora po imenu i biografiji.
     *
     * @param a autor sa zadatim imenom i biografijom
     * @return pronađeni autor
     * @throws NoSuchElementException ako autor ne postoji
     */
    Author findByNameAndBiography(Author a)throws NoSuchElementException;
    /**
     * Proverava da li postoji autor sa zadatim imenom i biografijom.
     *
     * @param a autor sa zadatim imenom i biografijom
     * @return true ako postoji autor, false inace
     */
    public boolean existByNameAndBiography(Author a);
}
