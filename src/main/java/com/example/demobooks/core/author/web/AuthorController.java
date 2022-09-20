package com.example.demobooks.core.author.web;

import com.example.demobooks.core.author.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService service;

    public AuthorController(final AuthorService service) { this.service = service;}

    @GetMapping
    @ResponseBody
    public Page<String> getAllAuthor(@RequestParam(required = false) String name,
                                     @PageableDefault(sort = "name",
                                             direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllAuthor(name, pageable);
    }
}
