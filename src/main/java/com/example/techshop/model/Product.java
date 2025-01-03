package com.example.techshop.model;

public class Product {
    private int productID;
    private String image;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private int categoryID;
    private boolean status;
    private boolean selected;

    public Product(String name, String description, double price, int quantity, int categoryID) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.selected = false;
    }

    public Product(String image, String name, String description, double price, int quantity, int categoryID) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
    }

    public Product(int productID, String image, String name, String description, double price, int quantity, int categoryID, boolean status) {
        this.productID = productID;
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.status = status;
        this.selected = false;
    }

    public Product(String image, String name, String description, double price, int quantity, int categoryID, boolean status) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.categoryID = categoryID;
        this.status = status;
        this.selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productID=" + productID +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", categoryID=" + categoryID +
                ", status=" + status +
                '}';
    }
}
