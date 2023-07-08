package com.np.library.repository;

import com.np.library.domain.LibraryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 Repozitorijum za pristup podacima o clanovima biblioteke.
 @author Mihajlo
 */
@Repository
public interface LibraryUserRepository extends JpaRepository<LibraryUser,Long> {
}
