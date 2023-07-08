package com.np.library.repository;

import com.np.library.domain.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 Repozitorijum za pristup podacima o bibliotekama.
 @author Mihajlo
 */
@Repository
public interface LibraryRepository extends JpaRepository<Library,Long> {
}
