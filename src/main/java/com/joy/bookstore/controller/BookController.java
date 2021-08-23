package com.joy.bookstore.controller;


import com.joy.bookstore.service.BookService;
import com.joy.bookstore.service.BranchBookService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookService bookService;
    private final BranchBookService branchBookService;

    public BookController(BookService bookService,
        BranchBookService branchBookService) {
        this.bookService = bookService;
        this.branchBookService = branchBookService;
    }

    @RequestMapping(value = "/book/{bookId}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
        return "Success";
    }

    @RequestMapping(value = "/book/{bookId}/out-of-print", method = RequestMethod.DELETE)
    public String outOfPrint(@PathVariable String bookId) {
        branchBookService.outOfPrint(bookId);
        return "Success";
    }

}
