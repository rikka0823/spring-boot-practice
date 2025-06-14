package com.rikka.springBootPractice.service;

import com.rikka.springBootPractice.entity.Book;
import com.rikka.springBootPractice.model.BookDto;

public interface BookService {

    Integer createBook(BookDto bookDto);

    Book getBookById(Integer bookId);

    void updateBook(Integer bookId, BookDto bookDto);

    void deleteBookById(Integer bookId);
}
