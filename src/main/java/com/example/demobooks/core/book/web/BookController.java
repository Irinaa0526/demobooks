package com.example.demobooks.core.book.web;

import com.example.demobooks.core.book.BookService;
import com.example.demobooks.error.EntityNotFoundException;
import com.example.demobooks.error.ValuesAreNotFilledException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping()
public class BookController {

    private final BookService service;

    public BookController(final BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    @ResponseBody
    public Page<BookView> getAllBook(@RequestParam(required = false) String title,
                                   @PageableDefault(sort = "title", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllBook(title, pageable);
    }

    @GetMapping("/books/{id}")
    @ResponseBody
    public ResponseEntity getBook(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getBook(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/main")
    @ResponseBody
    public List<BookView> getLast(){
        return service.findLastBooks();
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public ResponseEntity create(@RequestBody @Valid BookBaseReq req) {
        try {
            return ResponseEntity.ok(service.create(req));
        } catch (ValuesAreNotFilledException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity deleteBook(@PathVariable Long id){
        try {
            service.delete(id);
            return ResponseEntity.ok("Книга удалена!");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}