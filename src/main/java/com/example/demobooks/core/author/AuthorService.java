package com.example.demobooks.core.author;

import com.example.demobooks.core.author.converter.AuthorToAuthorViewConverter;
import com.example.demobooks.core.author.web.AuthorView;
import com.example.demobooks.core.book.Book;
import com.example.demobooks.core.book.BookRepo;
import com.example.demobooks.core.book.web.BookView;
import com.example.demobooks.error.EntityNotFoundException;
import com.example.demobooks.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final AuthorToAuthorViewConverter authorToAuthorViewConverter;
    private final BookRepo bookRepo;
    private final MessageUtil messageUtil;

    public AuthorService(AuthorRepo authorRepo,
                         AuthorToAuthorViewConverter authorToAuthorViewConverter,
                         BookRepo bookRepo,
                         MessageUtil messageUtil) {
        this.authorRepo = authorRepo;
        this.authorToAuthorViewConverter = authorToAuthorViewConverter;
        this.bookRepo = bookRepo;
        this.messageUtil = messageUtil;
    }

    public Author findAuthorOrThrow(long id) {
        return authorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("author.NotFound", id)));
    }

    public Page<String> findAuthorsBooks(long idAuthor, Pageable pageable) {

        Page<String> authorsBooks = authorRepo.findBooksByAuthor(idAuthor, pageable);

        List<String> titlesAuthorsBooks = new ArrayList<>();
        authorsBooks.forEach(titleBook -> {
            titlesAuthorsBooks.add(titleBook);
        });
        return new PageImpl<>(titlesAuthorsBooks, pageable, authorsBooks.getTotalElements());
    }

    public Page<String> findAllAuthor(String likeName, Pageable pageable) {

        Page<Author> authors;
        if (likeName != null) { authors = authorRepo.findByNameLike(likeName.toLowerCase(), pageable); }
        else { authors = authorRepo.findAll(pageable); }

        List<String> authorNames = new ArrayList<>();
        authors.forEach(author -> {
            String authorName = author.getName();
            authorNames.add(authorName);
        });
        return new PageImpl<>(authorNames, pageable, authors.getTotalElements());
    }
}