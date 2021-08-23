package com.joy.bookstore.controller;


import com.joy.bookstore.domain.dto.BookDTO;
import com.joy.bookstore.service.BranchBookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BranchBookController {

    private final BranchBookService branchBookService;

    public BranchBookController(BranchBookService branchBookService) {
        this.branchBookService = branchBookService;
    }

    @RequestMapping(value = "/{branch}/stackRoom/{book}", method = RequestMethod.GET)
    public String isBookInStack(@PathVariable String branch, @PathVariable String book) {
        // whether certain book in stack room or not
        if (branchBookService.isBookInStock(branch, book)) {
            return "True";
        } else {
            return "False";
        }
    }

    @RequestMapping(value = "/{branch}/stackRoom/{book}", method = RequestMethod.POST)
    public String addBookToStack(@PathVariable String branch, @PathVariable String book,
        @RequestBody(required = false) BookDTO bookDTO) {
        // Add book to bookstore's stack room
        if (bookDTO != null) {
            branchBookService.addBook(branch, book, bookDTO.getAuthor());
        } else {
            branchBookService.addBook(branch, book);
        }

        return "Success";
    }

    @RequestMapping(value = "/{branch}/stackRoom/{book}", method = RequestMethod.DELETE)
    public String removeBookFromStock(@PathVariable String branch, @PathVariable String book) {
        // remove book from bookstore's stock
        branchBookService.removeBook(branch, book);
        return "Success";
    }
}
