package com.joy.bookstore.controller;

import com.joy.bookstore.service.PrintBeans;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BeansController {

    private final PrintBeans printBeans;

    public BeansController(PrintBeans printBeans) {
        this.printBeans = printBeans;
    }

    @RequestMapping(value = "/beans", method = RequestMethod.GET)
    public String getBeans() {
        return printBeans.printBeans();
    }
}
