package com.joy.bookstore.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.joy.bookstore.domain.dto.BookstoreDTO;
import com.joy.bookstore.domain.dto.BookstoreRenameDTO;
import com.joy.bookstore.domain.po.Bookstore;
import com.joy.bookstore.domain.po.Revenue;
import com.joy.bookstore.repository.BookstoreRepository;
import com.joy.bookstore.repository.RevenueRepository;
import org.springframework.stereotype.Service;

@Service
public class BookstoreService {

    private final BookstoreRepository bookstoreRepository;
    private final RevenueRepository revenueRepository;

    public BookstoreService(BookstoreRepository bookstoreRepository,
        RevenueRepository revenueRepository) {
        this.bookstoreRepository = bookstoreRepository;
        this.revenueRepository = revenueRepository;
    }

    public String getBookstoreDetail(String bookstoreName) throws JsonProcessingException {
        // query branch name, revenue, books & its author
        Optional<Bookstore> optBookstore = bookstoreRepository.getByName(bookstoreName);
        Bookstore bookstore = optBookstore
            .orElseThrow(() -> new RuntimeException("Find no bookstore " + bookstoreName + "."));
        Map<String, String> bookMap = new HashMap<>();
        bookstore.getBookList().forEach(book -> {
            bookMap.put(book.getName(), book.getAuthor().getName());
        });
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(bookMap);
    }

    public void addBookstore(String bookstoreName, String revenueStr) {
        // Add new branch
        Bookstore bookstore = new Bookstore(bookstoreName);
        Revenue revenue = new Revenue(Integer.valueOf(revenueStr));
        bookstore.setRevenue(revenue);
        bookstoreRepository.save(bookstore);
    }

    public void updateName(BookstoreRenameDTO bookstoreRenameDTO) {
        // update branch name
        Optional<Bookstore> optBookstore = bookstoreRepository
            .getByName(bookstoreRenameDTO.getBranch());
        Bookstore bookstore = optBookstore
            .orElseThrow(() -> new RuntimeException(
                "Find no bookstore " + bookstoreRenameDTO.getBranch() + "."));
        bookstore.setName(bookstoreRenameDTO.getNewName());
        bookstoreRepository.save(bookstore);
    }

    public void updateRevenue(BookstoreDTO bookstoreDTO) {
        // update branch revenue
        Optional<Bookstore> optBookstore = bookstoreRepository.getByName(bookstoreDTO.getBranch());
        Bookstore bookstore = optBookstore
            .orElseThrow(
                () -> new RuntimeException("Not find bookstore-" + bookstoreDTO.getBranch()));
        bookstore.getRevenue().setRevenue(Integer.valueOf(bookstoreDTO.getRevenue()));
        revenueRepository.save(bookstore.getRevenue());
    }


}
