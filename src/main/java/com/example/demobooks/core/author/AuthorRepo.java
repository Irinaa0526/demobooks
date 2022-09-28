package com.example.demobooks.core.author;

import com.example.demobooks.core.book.Book;
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

    @Query(value = "SELECT * FROM books " +
            "JOIN book_author " +
            "on books.id = book_author.id_book " +
            "WHERE book_author.id_author = :idAuthor ORDER BY books.title ASC", nativeQuery = true)
    Page<Book> findBooksByAuthor(@Param("idAuthor") Long idAuthor, Pageable pageable);

    Author findAllByName(String nameAuthor);
}
