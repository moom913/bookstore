package com.joy.bookstore.service;

import java.util.Optional;

import com.joy.bookstore.domain.po.Book;
import com.joy.bookstore.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void deleteBook(String bookId) {
        Optional<Book> optBook =bookRepository.getById(Long.parseLong(bookId));
        optBook.ifPresent(bookRepository::delete);
    }

}
