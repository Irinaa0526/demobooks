package com.example.demobooks.repositories;

import com.example.demobooks.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    Set<Author> findByName(String title);
}
