package com.example.demobooks.core.author.web;

import com.example.demobooks.core.author.AuthorService;
import com.example.demobooks.core.book.web.BookView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(final AuthorService service) { this.service = service;}

    @GetMapping
    @ResponseBody
    public Page<AuthorView> getAllAuthor(@RequestParam(required = false) String name,
                                     @PageableDefault(sort = "name",
                                             direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllAuthor(name, pageable);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Page<BookView> getAuthorsBooks(@PathVariable Long id,
                                          Pageable pageable) {
        return service.findAuthorsBooks(id, pageable);
    }
}