package com.np.library.service;

import com.np.library.domain.LibraryUser;

import java.util.List;
/**
 * Interfejs koji predstavlja servis za rad sa korisnicima biblioteke.
 */
public interface LibraryUserService {
    /**
     * Cuva novog korisnika biblioteke.
     *
     * @param libraryUser korisnik biblioteke koji se cuva
     */
    public void saveLibraryUser(LibraryUser libraryUser);
    /**
     * Vraca sve korisnike biblioteke.
     *
     * @return lista svih korisnika biblioteke
     */
    public List<LibraryUser> getLibraryUsers();
    /**
     * Vraca korisnika biblioteke sa datim identifikatorom.
     *
     * @param id identifikator korisnika biblioteke
     * @return korisnik biblioteke sa datim identifikatorom
     */
    public LibraryUser getLibraryUserById(Long id);
    /**
     * Menja lozinku korisnika biblioteke sa datim identifikatorom.
     *
     * @param newPassword nova lozinka
     * @param id identifikator korisnika biblioteke
     * @return poruka o uspešnoj promeni lozinke
     */
    public String changePassword(String newPassword,Long id);
}
