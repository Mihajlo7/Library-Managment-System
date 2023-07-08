package com.np.library.repository;

import com.np.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repozitorijum za pristup podacima o korisnicima.
 */
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
