package com.joy.bookstore.domain.dto;

public class BookstoreDTO {
    private String branch;
    private String revenue;

    public BookstoreDTO() {
    }

    public BookstoreDTO(String branch, String revenue) {
        this.branch = branch;
        this.revenue = revenue;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }
}
