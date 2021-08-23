package com.joy.bookstore.repository;

import java.util.Optional;

import com.joy.bookstore.domain.po.Bookstore;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface BookstoreRepository extends CrudRepository<Bookstore, Long> {

    // query
    public Optional<Bookstore> getByName(String name);

    public Iterable<Bookstore> findAll();

    public boolean existsById(Long key);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO \"bookstore_book\" (\"book_id\", \"bookstore_id\") VALUES (:bookId,:bookstoreId);", nativeQuery = true)
    public void addBook(@Param("bookstoreId") Long bookstoreId,
        @Param("bookId") Long bookId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM \"bookstore_book\" WHERE \"book_id\"=:bookId AND \"bookstore_id\"=:bookstoreId ;", nativeQuery = true)
    public void removeBook(@Param("bookstoreId") Long bookstoreId,
        @Param("bookId") Long bookId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM \"bookstore_book\" WHERE \"book_id\"=:bookId ;", nativeQuery = true)
    public void removeAllBranches(@Param("bookId") Long bookId);

}
