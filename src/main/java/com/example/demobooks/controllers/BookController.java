package com.example.demobooks.controllers;

import com.example.demobooks.entity.Book;
import com.example.demobooks.error.UserNotFoundException;
import com.example.demobooks.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity addBook(@RequestBody Book book){
        try {
            bookService.saveBook(book);
            return ResponseEntity.ok("Книга добавлена!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
        }
        //return bookService.saveBook(book);
    }

    @GetMapping
    public ResponseEntity getOneBook(@RequestParam Long id){
        try{
            return ResponseEntity.ok(bookService.getOne(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Произошла ошибка");
        }
    }

}
