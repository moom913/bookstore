package com.joy.bookstore.domain.dto;

public class BookDTO {
    private String author;

    public BookDTO() {
    }

    public BookDTO(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
