package com.np.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Librarian extends JpaRepository<Librarian,Long> {
}
