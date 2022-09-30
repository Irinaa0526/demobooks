package com.example.demobooks.core.author;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {

    @Query(value = "SELECT * FROM authors WHERE lower(name) LIKE %:likeName% ORDER BY id ASC", nativeQuery = true)
    Page<Author> findByNameLike(@Param("likeName") String likeName, Pageable pageable);

    Author findAllByName(String nameAuthor);
}
