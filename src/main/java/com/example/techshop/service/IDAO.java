package com.example.techshop.service;

import com.example.techshop.model.Product;
import com.example.techshop.model.User;

import java.util.List;


public interface IDAO {
    boolean insertUser(User user);

    boolean deleteUser(int id);

    boolean updateUser(int id, User user);

    User selectUser(int id);

    List<User> selectAllUsers();
    boolean insertProduct(Product product);

    boolean deleteProduct(int id);

    boolean updateProduct(int id, Product product);

    Product selectProduct(int id);

    List<Product> selectAllProducts();

    User checkLogin(String username, String password);
}
