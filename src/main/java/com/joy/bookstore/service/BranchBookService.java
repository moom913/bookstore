package com.joy.bookstore.service;


import com.joy.bookstore.domain.po.Author;
import com.joy.bookstore.domain.po.Book;
import com.joy.bookstore.domain.po.Bookstore;
import com.joy.bookstore.repository.AuthorRepository;
import com.joy.bookstore.repository.BookRepository;
import com.joy.bookstore.repository.BookstoreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BranchBookService {

    private final BookstoreRepository bookstoreRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BranchBookService(BookstoreRepository bookstoreRepository, BookRepository bookRepository,
                             AuthorRepository authorRepository) {
        this.bookstoreRepository = bookstoreRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public boolean isBookInStock(String bookstoreName, String bookName) {
        // query certain book
        Optional<Bookstore> optBookstore = bookstoreRepository.getByName(bookstoreName);
        Bookstore bookstore = optBookstore
            .orElseThrow(() -> new RuntimeException("Not find bookstore " + bookstoreName));
        List<Book> books = bookstore.getBookList();
        List<String> bookNames = books.stream()
            .map(Book::getName)
            .collect(Collectors.toList());

        return bookNames.contains(bookName);
    }

    public void addBook(String bookstoreName, String bookName) {
        // Add book to bookstore
        Optional<Bookstore> optBookstore = bookstoreRepository.getByName(bookstoreName);
        Bookstore bookstore = optBookstore
            .orElseThrow(() -> new RuntimeException("Not find bookstore-" + bookstoreName));
        Optional<Book> optBook = bookRepository.getByName(bookName);
        Book book = optBook.orElseThrow(() -> new RuntimeException("Not find book-" + bookName));
        bookstoreRepository.addBook(bookstore.getId(), book.getId());
    }

    public void addBook(String bookstoreName, String bookName, String authorName) {
        // Add book to bookstore
        Optional<Bookstore> optBookstore = bookstoreRepository.getByName(bookstoreName);
        Bookstore bookstore = optBookstore
            .orElseThrow(() -> new RuntimeException("Not find bookstore-" + bookstoreName));

        Optional<Author> optAuthor = authorRepository.getByName(authorName);
        Author author = optAuthor.orElseGet(() -> authorRepository.save(new Author(authorName)));

        Book book = new Book(bookName);
        book.setAuthor(author);
        bookRepository.save(book);

        bookstoreRepository.addBook(bookstore.getId(), book.getId());
    }

    public void removeBook(String bookstoreName, String bookName) {
        // remove book from bookstore
        Optional<Bookstore> optBookstore = bookstoreRepository.getByName(bookstoreName);
        Bookstore bookstore = optBookstore
            .orElseThrow(() -> new RuntimeException("Not find bookstore-" + bookstoreName));
        Optional<Book> optBook = bookRepository.getByName(bookName);
        Book book = optBook.orElseThrow(() -> new RuntimeException("Not find book-" + bookName));
        bookstoreRepository.removeBook(bookstore.getId(), book.getId());
    }

    public void outOfPrint(String bookId) {
        // remove certain book's branches
        bookstoreRepository.removeAllBranches(Long.parseLong(bookId));
    }
}
