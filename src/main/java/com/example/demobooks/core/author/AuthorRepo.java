package com.example.demobooks.core.author;

import com.example.demobooks.core.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AuthorRepo extends JpaRepository<Author, Long> {
    Set<Author> findByName(String title);
}
