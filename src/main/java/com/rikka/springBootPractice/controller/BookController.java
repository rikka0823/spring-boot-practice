package com.rikka.springBootPractice.controller;

import com.rikka.springBootPractice.entity.Book;
import com.rikka.springBootPractice.model.BookDto;
import com.rikka.springBootPractice.service.BookService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {

    Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody @Valid BookDto bookDto) {
        Integer bookId = bookService.createBook(bookDto);
        Book book = bookService.getBookById(bookId);

        if (book == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable Integer bookId) {
//        return ResponseEntity.status(HttpStatus.OK)
//                .body(bookService.getBookById(bookId));
        Book book = bookService.getBookById(bookId);
        System.out.println("Got book: " + book); // 加個 log 看有沒有成功
        return ResponseEntity.ok(book);
    }

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer bookId,
                                           @RequestBody @Valid BookDto bookDto) {
        Book book = bookService.getBookById(bookId);
        bookService.updateBook(bookId, bookDto);

        return ResponseEntity.status(HttpStatus.OK).body(book);
    }

    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable Integer bookId) {
        try {
            bookService.deleteBookById(bookId);
        } catch (EmptyResultDataAccessException e) {
            logger.info(e.getMessage(), e);
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
