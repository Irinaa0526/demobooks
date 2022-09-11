package com.example.demobooks.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="authors")
public class Author {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAuthor")
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
    private Long idAuthor;

    @Column(name="name")
    private String name;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.LAZY)
    private Set<Book> booksAuthor = new HashSet<>();

    public Author(Long idAuthor, String name, Set<Book> booksAuthor) {
        //this.idAuthor = idAuthor;
        this.name = name;
        this.booksAuthor = booksAuthor;
    }

    public Author() {
    }

    public Long getIdAuthor() {
        return this.idAuthor;
    }

    public String getName() {
        return this.name;
    }

    public Set<Book> getBooksAuthor() {
        return this.booksAuthor;
    }

    public void setIdAuthor(Long idAuthor) {
        this.idAuthor = idAuthor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBooksAuthors(Set<Book> booksAuthor) {
        this.booksAuthor = booksAuthor;
    }

//    public boolean equals(final Object o) {
//        if (o == this) return true;
//        if (!(o instanceof Author)) return false;
//        final Author other = (Author) o;
//        if (!other.canEqual((Object) this)) return false;
//        final Object this$idAuthor = this.getIdAuthor();
//        final Object other$idAuthor = other.getIdAuthor();
//        if (this$idAuthor == null ? other$idAuthor != null : !this$idAuthor.equals(other$idAuthor)) return false;
//        final Object this$name = this.getName();
//        final Object other$name = other.getName();
//        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
//        final Object this$booksAuthors = this.getBooksAuthors();
//        final Object other$booksAuthors = other.getBooksAuthors();
//        if (this$booksAuthors == null ? other$booksAuthors != null : !this$booksAuthors.equals(other$booksAuthors))
//            return false;
//        return true;
//    }
//
//    protected boolean canEqual(final Object other) {
//        return other instanceof Author;
//    }
//
//    public int hashCode() {
//        final int PRIME = 59;
//        int result = 1;
//        final Object $idAuthor = this.getIdAuthor();
//        result = result * PRIME + ($idAuthor == null ? 43 : $idAuthor.hashCode());
//        final Object $name = this.getName();
//        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
//        final Object $booksAuthors = this.getBooksAuthors();
//        result = result * PRIME + ($booksAuthors == null ? 43 : $booksAuthors.hashCode());
//        return result;
//    }

//    public String toString() {
//        return "Author(idAuthor=" + this.getIdAuthor() + ", name=" + this.getName() + ", booksAuthors=" + this.getBooksAuthor() + ")";
//    }
    //private List<Book> booksAuthors = new ArrayList<>();
}
