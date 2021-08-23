package com.joy.bookstore.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.joy.bookstore.domain.dto.BookstoreDTO;
import com.joy.bookstore.domain.dto.BookstoreRenameDTO;
import com.joy.bookstore.service.BookstoreService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookstoreController {

    private final BookstoreService bookstoreService;

    public BookstoreController(BookstoreService bookstoreService) {
        this.bookstoreService = bookstoreService;
    }

    @RequestMapping(value = "/{bookstoreName}/info", method = RequestMethod.GET)
    public String getBookstoreInfo(@PathVariable String bookstoreName) throws JsonProcessingException {
        // query branch name, revenue, books & its author
        return bookstoreService.getBookstoreDetail(bookstoreName);
    }

    @RequestMapping(value = "/branch", method = RequestMethod.POST)
    public String addBookstore(@RequestBody BookstoreDTO bookstore) {
        // Add new branch
        bookstoreService.addBookstore(bookstore.getBranch(), bookstore.getRevenue());


        return "Success";
    }

    @RequestMapping(value = "/branch/rename", method = RequestMethod.PUT)
    public String renameBookstore(@RequestBody BookstoreRenameDTO bookstoreRenameDTO) {
        // update name or revenue
        bookstoreService.updateName(bookstoreRenameDTO);
        return "Success";
    }

    @RequestMapping(value = "/branch/updateRevenue", method = RequestMethod.PUT)
    public String updateRevenue(@RequestBody BookstoreDTO bookstoreDTO) {
        bookstoreService.updateRevenue(bookstoreDTO);
        return "Success";
    }

}
