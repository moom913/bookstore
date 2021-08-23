package com.joy.bookstore.repository;

import java.util.List;
import java.util.Optional;

import com.joy.bookstore.domain.po.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    // query
    public Optional<Book> getById(Long id);
    public Optional<Book> getByName(String name);
}
