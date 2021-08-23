package com.joy.bookstore.repository;


import com.joy.bookstore.domain.po.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    // query
    public Optional<Author> getById(Long id);
    public Optional<Author> getByName(String name);

}
