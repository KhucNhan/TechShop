package com.example.techshop.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Order {
    private int orderID;
    private int userID;
    private LocalDateTime orderDate;
    private double total;
    private String status;
    private String userName;

    public Order() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Order(int orderID, int userID, LocalDateTime orderDate, double total, String status) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.total = total;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Order(int orderID, int userID, LocalDateTime orderDate, double total, String status, String userName) {
        this.orderID = orderID;
        this.userID = userID;
        this.orderDate = orderDate;
        this.total = total;
        this.status = status;
        this.userName = userName;
    }
}
