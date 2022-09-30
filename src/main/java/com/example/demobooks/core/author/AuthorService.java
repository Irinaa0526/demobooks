package com.example.demobooks.core.author;

import com.example.demobooks.core.author.converter.AuthorToAuthorViewConverter;
import com.example.demobooks.core.author.web.AuthorView;
import com.example.demobooks.core.book.Book;
import com.example.demobooks.core.book.BookRepo;
import com.example.demobooks.core.book.converter.BookToBookViewConverter;
import com.example.demobooks.core.book.web.BookView;
import com.example.demobooks.error.EntityNotFoundException;
import com.example.demobooks.util.MessageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepo authorRepo;
    private final AuthorToAuthorViewConverter authorToAuthorViewConverter;
    private final BookToBookViewConverter bookToBookViewConverter;
    private final BookRepo bookRepo;
    private final MessageUtil messageUtil;

    public AuthorService(AuthorRepo authorRepo,
                         AuthorToAuthorViewConverter authorToAuthorViewConverter,
                         BookToBookViewConverter bookToBookViewConverter, BookRepo bookRepo,
                         MessageUtil messageUtil) {
        this.authorRepo = authorRepo;
        this.authorToAuthorViewConverter = authorToAuthorViewConverter;
        this.bookToBookViewConverter = bookToBookViewConverter;
        this.bookRepo = bookRepo;
        this.messageUtil = messageUtil;
    }

    public Author findAuthorOrThrow(Long id) {
        return authorRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("author.NotFound", id)));
    }

    public Page<BookView> findAuthorsBooks(Long idAuthor, Pageable pageable) {
        Author author = findAuthorOrThrow(idAuthor);
        List<Book> books = new ArrayList<>(author.getBooksAuthor());

        List<BookView> bookViews = new ArrayList<>();
        books.forEach(book ->
            bookViews.add(bookToBookViewConverter.convert(book))
        );

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), bookViews.size());

        return new PageImpl<>(bookViews.subList(start,end), pageable, bookViews.size());
    }

    public Page<AuthorView> findAllAuthor(String likeName, Pageable pageable) {

        Page<Author> authors;
        if (likeName != null) {
            authors = authorRepo.findByNameLike(likeName.toLowerCase(), pageable);
        }
        else {
            authors = authorRepo.findAll(pageable);
        }

        List<AuthorView> authorViews = new ArrayList<>();
        authors.forEach(author -> authorViews.add(authorToAuthorViewConverter.convert(author)));

        return new PageImpl<>(authorViews, pageable, authors.getTotalElements());
    }
}