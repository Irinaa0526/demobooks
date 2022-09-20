package com.example.demobooks.core.book;

import com.example.demobooks.base.BaseRequest;
import com.example.demobooks.core.author.Author;
import com.example.demobooks.core.book.converter.BookToBookViewConverter;
import com.example.demobooks.core.book.web.BookBaseReq;
import com.example.demobooks.core.book.web.BookView;
import com.example.demobooks.error.EntityNotFoundException;
import com.example.demobooks.core.author.AuthorRepo;
import com.example.demobooks.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepo bookRepo;
    private final BookToBookViewConverter bookToBookViewConverter;
    private final AuthorRepo authorRepo;
    private final MessageUtil messageUtil;

    public BookService(BookRepo bookRepo,
                       BookToBookViewConverter bookToBookViewConverter,
                       AuthorRepo authorRepo,
                       MessageUtil messageUtil) {
        this.bookRepo = bookRepo;
        this.bookToBookViewConverter = bookToBookViewConverter;
        this.authorRepo = authorRepo;
        this.messageUtil = messageUtil;
    }

    public Book findBookOrThrow(long id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("book.NotFound", id)));
    }

    public BookView getBook(long id) {
        Book book = findBookOrThrow(id);
        return bookToBookViewConverter.convert(book);
    }

    public Page<String> findAllBook(String likeTitle, Pageable pageable) {
        Page<Book> books;
        if (likeTitle != null) { books = bookRepo.findByTitleLike(likeTitle.toLowerCase(), pageable); }
        else { books = bookRepo.findAll(pageable); }

        List<String> bookTitles = new ArrayList<>();
        books.forEach(book -> {
            String bookTitle = book.getTitle();
            bookTitles.add(bookTitle);
        });

        return new PageImpl<>(bookTitles, pageable, books.getTotalElements());
    }

    public List<String> findLastBooks() {
        List<String> books = bookRepo.getLastBooks();
        return books;
    }

    public BookView create(BookBaseReq req) {
        Book book = new Book();
        this.prepare(book, req);
        Book bookSave = bookRepo.save(book);
        return bookToBookViewConverter.convert(bookSave);
    }

    @Transactional
    public void delete(long id) {
        try {
            bookRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("book.NotFound", id));
        }
    }

    public BookView update(Book book, BookBaseReq req) {
        Book newBook = this.prepare(book, req);
        Book bookSave = bookRepo.save(newBook);
        return bookToBookViewConverter.convert(bookSave);
    }

    public Book prepare(Book book, BookBaseReq bookBaseReq){
        book.setTitle(bookBaseReq.getTitle());
        book.setDescription(bookBaseReq.getDescription());
        List<Author> authorList = authorRepo.findAllById(bookBaseReq.getAuthors()
                .stream()
                .map(BaseRequest.Id::getId)
                .collect(Collectors.toSet()));
        Set<Author> authors = new HashSet<>(authorList);
        book.setAuthors(authors);
        return book;
    }
}
