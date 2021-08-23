package com.joy.bookstore.domain.dto;

public class BookstoreRenameDTO {
    private String branch;
    private String newName;

    public BookstoreRenameDTO() {
    }

    public BookstoreRenameDTO(String branch, String newName) {
        this.branch = branch;
        this.newName = newName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
