package com.example.demobooks.core.author.converter;

import com.example.demobooks.core.author.Author;
import com.example.demobooks.core.author.web.AuthorView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorToAuthorViewConverter implements Converter<Author, AuthorView> {

    @Override
    public AuthorView convert(@NonNull Author author) {
        AuthorView view = new AuthorView();
        //view.setId(author.getId());
        view.setName(author.getName());
        return view;
    }
}
