package com.example.demobooks.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="books")
public class Book {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idBook")
    @GenericGenerator(
            name = "books_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "books_id_seq"),
                    @org.hibernate.annotations.Parameter(name= "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "9223372036854775807"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "books_id_seq")
    private Long idBook;

    @Column(name="title")
    private String title;

    @Column(name="description", columnDefinition = "text")
    private String description;

    //@NotEmpty
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinTable(
            name = "book_author",
            joinColumns = { @JoinColumn(name = "id_book") },
            inverseJoinColumns = { @JoinColumn(name = "id_author") }
    )
    private Set<Author> authors = new HashSet<>();

    public Book(Long idBook, String title, String description, Set<Author> authors) {
        //this.idBook = idBook;
        this.title = title;
        this.description = description;
        this.authors = authors;
    }

    public Book() {
    }

    public Long getIdBook() {
        return this.idBook;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public Set<Author> getAuthors() {
        return this.authors;
    }

    public void setIdBook(Long idBook) {
        this.idBook = idBook;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

//    public boolean equals(final Object o) {
//        if (o == this) return true;
//        if (!(o instanceof Book)) return false;
//        final Book other = (Book) o;
//        if (!other.canEqual((Object) this)) return false;
//        final Object this$idBook = this.getIdBook();
//        final Object other$idBook = other.getIdBook();
//        if (this$idBook == null ? other$idBook != null : !this$idBook.equals(other$idBook)) return false;
//        final Object this$title = this.getTitle();
//        final Object other$title = other.getTitle();
//        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
//        final Object this$description = this.getDescription();
//        final Object other$description = other.getDescription();
//        if (this$description == null ? other$description != null : !this$description.equals(other$description))
//            return false;
//        final Object this$authors = this.getAuthors();
//        final Object other$authors = other.getAuthors();
//        if (this$authors == null ? other$authors != null : !this$authors.equals(other$authors)) return false;
//        return true;
//    }
//
//    protected boolean canEqual(final Object other) {
//        return other instanceof Book;
//    }
//
//    public int hashCode() {
//        final int PRIME = 59;
//        int result = 1;
//        final Object $idBook = this.getIdBook();
//        result = result * PRIME + ($idBook == null ? 43 : $idBook.hashCode());
//        final Object $title = this.getTitle();
//        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
//        final Object $description = this.getDescription();
//        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
//        final Object $authors = this.getAuthors();
//        result = result * PRIME + ($authors == null ? 43 : $authors.hashCode());
//        return result;
//    }

//    public String toString() {
//        return "Book(idBook=" + this.getIdBook() + ", title=" + this.getTitle() + ", description=" + this.getDescription() + ", authors=" + this.getAuthors() + ")";
//    }
    //private List<Author> authors = new ArrayList<>();
}
