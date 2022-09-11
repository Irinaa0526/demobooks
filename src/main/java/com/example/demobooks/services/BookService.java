package com.example.demobooks.services;

import com.example.demobooks.entity.Author;
import com.example.demobooks.entity.Book;
import com.example.demobooks.error.AuthorsIsEmptyException;
import com.example.demobooks.error.UserNotFoundException;
import com.example.demobooks.repositories.AuthorRepo;
import com.example.demobooks.repositories.BookRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public Book saveBook(Book book) throws AuthorsIsEmptyException {
        Set<Author> authors = book.getAuthors();
        if(authors.isEmpty()){
            throw new AuthorsIsEmptyException("Автор не указан");
        }

        //bookRepo.save(book);

        for (Author author : authors) {
            Set<Author> authorsInBase = authorRepo.findByName(author.getName());
            if(authorsInBase.isEmpty()){
                //bookRepo.save(book);
//                author = authorsInBase.iterator().next();
//                book.getAuthors().add(author);
//                author.getBooksAuthor().add(book);
//                authorRepo.save(author);
                authorRepo.save(author);
            }
//            else {
//                //bookRepo.save(book);
//                authorRepo.save(author);
//                book.getAuthors().add(author);
//                author.getBooksAuthor().add(book);
//                authorRepo.save(author);
//            }
        }
        bookRepo.save(book);

        for (Author author : authors) {
            book.getAuthors().add(author);
            author.getBooksAuthor().add(book);
            bookRepo.save(book);
            authorRepo.save(author);
        }

        return bookRepo.save(book);
    }

    public Book getOne(Long id) throws UserNotFoundException {
        Book book = bookRepo.findById(id).get();
        if(book == null){
            throw new UserNotFoundException("Книга не найдена");
        }
        return book;
    }
}
