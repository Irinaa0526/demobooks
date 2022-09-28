package com.example.demobooks.core.book.web;

import com.example.demobooks.core.author.Author;
import com.example.demobooks.core.author.web.AuthorView;

import java.util.HashSet;
import java.util.Set;

public class BookView {
    private long id;
    private String title;
    private String description;
    private Set<AuthorView> authors = new HashSet<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AuthorView> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<AuthorView> authors) {
        this.authors = authors;
    }
}
