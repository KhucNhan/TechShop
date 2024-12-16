package com.example.techshop.model;

import java.util.Date;

public class User {
    private int userID;
    private String image;
    private String name;
    private String username;
    private String password;
    private String gender;
    private Date dateOfBirth;
    private String role;
    private boolean status;

    public User(int userID, String image, String name, String username, String password, String gender, Date dateOfBirth, String role, boolean status) {
        this.userID = userID;
        this.image = image;
        this.name = name;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.role = role;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
