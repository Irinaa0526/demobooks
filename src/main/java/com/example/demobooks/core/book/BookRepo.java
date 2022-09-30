package com.example.demobooks.core.book;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    @Query(value = "SELECT * FROM books ORDER BY id DESC LIMIT 10", nativeQuery = true)
    List<Book> getLastBooks();

    @Query(value = "SELECT * FROM books WHERE lower(title) LIKE %:likeTitle% ORDER BY id DESC", nativeQuery = true)
    Page<Book> findByTitleLike(@Param("likeTitle") String likeTitle, Pageable pageable);

}