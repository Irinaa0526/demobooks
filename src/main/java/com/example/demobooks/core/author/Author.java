package com.example.demobooks.core.author;

import com.example.demobooks.core.book.Book;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="authors")
public class Author {
    @Id
    @Column(name="id")
    @GenericGenerator(
            name = "authors_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "authors_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "9223372036854775807"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authors_id_seq")
    private long id;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private Set<Book> booksAuthor = new HashSet<>();

    public Author(String name, Set<Book> booksAuthor) {
        this.name = name;
        this.booksAuthor = booksAuthor;
    }

    public Author() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooksAuthor() {
        return booksAuthor;
    }

    public void setBooksAuthors(Set<Book> booksAuthor) {
        this.booksAuthor = booksAuthor;
    }

}
