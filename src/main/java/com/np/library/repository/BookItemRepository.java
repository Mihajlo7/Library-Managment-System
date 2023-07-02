package com.np.library.repository;

import com.np.library.domain.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookItemRepository extends JpaRepository<BookItem,Long> {
    Optional<List<BookItem>> findByBookIsbn(String isbn);
}
