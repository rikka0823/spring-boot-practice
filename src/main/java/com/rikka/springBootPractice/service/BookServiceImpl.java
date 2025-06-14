package com.rikka.springBootPractice.service;

import com.rikka.springBootPractice.entity.Book;
import com.rikka.springBootPractice.model.BookDto;
import com.rikka.springBootPractice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Integer createBook(BookDto bookDto) {
        Book book = Book.builder()
                .author(bookDto.getAuthor())
                .title(bookDto.getTitle())
                .price(bookDto.getPrice())
                .publishedDate(bookDto.getPublishedDate())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        bookRepository.save(book);

        Integer bookId = book.getBookId();
        if (bookId == null) {
            return -1;
        }

        return bookId;
    }

    @Override
    public Book getBookById(Integer bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void updateBook(Integer bookId, BookDto bookDto) {
        Book book = getBookById(bookId);
        book.setPrice(bookDto.getPrice());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPublishedDate(bookDto.getPublishedDate());
        book.setLastModifiedDate(LocalDateTime.now());

        bookRepository.save(book);
    }

    @Override
    public void deleteBookById(Integer bookId) {
        bookRepository.deleteById(bookId);
    }
}
