package com.example.techshop.model;

public class Category {
    private int categoryID;
    private String name;

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category(int categoryID, String name) {
        this.categoryID = categoryID;
        this.name = name;
    }
}