package com.example.techshop.service;

import com.example.techshop.model.Order;
import com.example.techshop.model.OrderDetails;
import com.example.techshop.model.Product;
import com.example.techshop.model.User;
import com.sun.org.apache.xpath.internal.operations.Or;

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

    List<Product> selectActiveProducts();

    List<Product> selectAllPhones();

    List<Product> selectAllLaptops();

    User checkLogin(String username, String password);

    int insertOrder(Order order);

    boolean insertOrderdetail(int orderID, OrderDetails orderDetails);

    Order selectOrder(int orderID);

    List<Order> selectAllOrders();

    List<Order> selectAllOrdersByUser(int userID);

    boolean updateOrder(int orderID, Order order);

    List<OrderDetails> selectOrderdetailsByOrderID(int orderID);

    boolean insertUserWithImage(User user);
}
