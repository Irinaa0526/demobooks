package com.example.demobooks.core.book.converter;

import com.example.demobooks.core.author.Author;
import com.example.demobooks.core.author.converter.AuthorToAuthorViewConverter;
import com.example.demobooks.core.author.web.AuthorView;
import com.example.demobooks.core.book.Book;
import com.example.demobooks.core.book.web.BookView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class BookToBookViewConverter implements Converter<Book, BookView> {

    private final AuthorToAuthorViewConverter authorToAuthorViewConverter;

    public BookToBookViewConverter(AuthorToAuthorViewConverter authorToAuthorViewConverter) {
        this.authorToAuthorViewConverter = authorToAuthorViewConverter;
    }

    @Override
    public BookView convert(@NonNull Book book) {
        BookView view = new BookView();
        view.setId(book.getId());
        view.setTitle(book.getTitle());
        view.setDescription(book.getDescription());

        Set<AuthorView> views = new HashSet<>();
        Set<Author> authors = book.getAuthors();
        authors.forEach(author -> {
            AuthorView authorView = authorToAuthorViewConverter.convert(author);
            views.add(authorView);
        });
        view.setAuthors(views);

        return view;
    }

}
