package com.example.demobooks.core.book.web;

import com.example.demobooks.core.book.Book;
import com.example.demobooks.core.book.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService service;

    public BookController(final BookService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public BookView getBook(@PathVariable Long id) {
        return service.getBook(id);
    }

    @GetMapping
    @ResponseBody
    public Page<String> getAllBook(@RequestParam(required = false) String title,
                                   @PageableDefault(sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllBook(title, pageable);
    }

    @GetMapping("/main")
    @ResponseBody
    public List<String> getLast(){
        return service.findLastBooks();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public BookView create(@RequestBody @Valid BookBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id){
        service.delete(id);
    }

    @PutMapping("/{id}")
    public BookView updateBook(@PathVariable(name = "id") Long id,
                                   @RequestBody @Valid BookBaseReq req){
        Book book = service.findBookOrThrow(id);
        return service.update(book, req);
    }
}