package com.example.techshop.service;

import com.example.techshop.model.Product;
import com.example.techshop.model.User;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO implements IDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/techshop";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "nhan771026";
    private static final String SELECT_INSERT_USER_STATUS = "select status from users where name = ?, username = ?, password = ?";
    private static final String SELECT_USER_BY_UP = "select * from users where username = ? and password = ?";
    private static final String INSERT_USERS_SQL = "INSERT INTO Users (name, username, password, gender, dateOfBirth) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select * from users where userID =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "update users set status = false where userID = ?;";
    private static final String UPDATE_USERS_SQL = "update users set image = ?, name = ?, username = ?, password = ?, gender = ?, dateOfBirth = ?, role = ?, status = ? where userID = ?;";

    private static final String SELECT_INSERT_PRODUCT_STATUS = "select status from products where name = ?";
    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO Products (name, description, price, quantity, categoryID) VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_PRODUCTS_WITH_IMG_SQL = "INSERT INTO Products (image, name, description, price, quantity, categoryID) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = "select * from products where productID =?";
    private static final String SELECT_ALL_PRODUCTS = "select * from products";
    private static final String SELECT_ACTIVE_PRODUCTS = "select * from products where status = true";
    private static final String DELETE_PRODUCTS_SQL = "update products set status = false where productID = ?;";
    private static final String UPDATE_PRODUCTS_SQL = "update products set image = ?, name = ?, description = ?, price = ?, quantity = ?, categoryID = ?, status = ? where productID = ?;";
    private static final String SELECT_ALL_PHONES = "select * from products where categoryID = 1";
    private static final String SELECT_ALL_LAPTOPS = "select * from products where categoryID = 2";


    private Connection connection = null;

    protected Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public boolean insertUser(User user) {
        connection = getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SELECT_INSERT_USER_STATUS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean status = true;
            if (resultSet.next()) {
                status = resultSet.getBoolean(1);
            }

            int row = 0;
            if (!status) {
                preparedStatement = connection.prepareStatement(INSERT_USERS_SQL);
                preparedStatement.setString(1, user.getName());
                preparedStatement.setString(2, user.getUsername());
                preparedStatement.setString(3, user.getPassword());
                preparedStatement.setString(4, user.getGender());
                preparedStatement.setString(5, user.getDateOfBirth().toString());
                row = preparedStatement.executeUpdate();
                return row > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteUser(int id) {
        connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USERS_SQL);
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateUser(int id, User user) {
        connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);
            preparedStatement.setString(1, user.getImage());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getGender());
            preparedStatement.setString(6, user.getDateOfBirth().toString());
            preparedStatement.setString(7, user.getRole());
            preparedStatement.setBoolean(8, user.isStatus());
            preparedStatement.setInt(9, id);
            int row = preparedStatement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User selectUser(int id) {
        connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDate(7),
                        resultSet.getString(8),
                        resultSet.getBoolean(9)
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> selectAllUsers() {
        connection = getConnection();
        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_USERS);

            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDate(7),
                        resultSet.getString(8),
                        resultSet.getBoolean(9)
                ));
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insertProduct(Product product) {
        connection = getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SELECT_INSERT_PRODUCT_STATUS);
            preparedStatement.setString(1, product.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean status = true;
            if (resultSet.next()) {
                status = resultSet.getBoolean(1);
            }

            int row = 0;
            if (!status) {
                preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL);
                preparedStatement.setString(1, product.getName());
                preparedStatement.setString(2, product.getDescription());
                preparedStatement.setDouble(3, product.getPrice());
                preparedStatement.setInt(4, product.getQuantity());
                preparedStatement.setInt(5, product.getCategoryID());
                row = preparedStatement.executeUpdate();
                return row > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean insertProductWithImage(Product product) {
        connection = getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SELECT_INSERT_PRODUCT_STATUS);
            preparedStatement.setString(1, product.getName());
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean status = true;
            if (resultSet.next()) {
                status = resultSet.getBoolean(1);
            }

            int row = 0;
            if (!status) {
                preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_WITH_IMG_SQL);
                preparedStatement.setString(1, product.getImage());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getDescription());
                preparedStatement.setDouble(4, product.getPrice());
                preparedStatement.setInt(5, product.getQuantity());
                preparedStatement.setInt(6, product.getCategoryID());
                row = preparedStatement.executeUpdate();
                return row > 0;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteProduct(int id) {
        connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCTS_SQL);
            preparedStatement.setInt(1, id);
            int row = preparedStatement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateProduct(int id, Product product) {
        connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);
            preparedStatement.setString(1, product.getImage());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.setInt(5, product.getQuantity());
            preparedStatement.setInt(6, product.getCategoryID());
            preparedStatement.setBoolean(7, product.isStatus());
            preparedStatement.setInt(8, id);
            int row = preparedStatement.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product selectProduct(int id) {
        connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("productID"),
                        resultSet.getString("image"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("categoryID"),
                        resultSet.getBoolean("status")
                );
                return product;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> selectAllProducts() {
        connection = getConnection();
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_PRODUCTS);

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("productID"),
                        resultSet.getString("image"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("categoryID"),
                        resultSet.getBoolean("status")
                ));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> selectActiveProducts() {
        connection = getConnection();
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ACTIVE_PRODUCTS);

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("productID"),
                        resultSet.getString("image"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("categoryID"),
                        resultSet.getBoolean("status")
                ));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> selectAllPhones() {
        connection = getConnection();
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_PHONES);

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("productID"),
                        resultSet.getString("image"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("categoryID"),
                        resultSet.getBoolean("status")
                ));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> selectAllLaptops() {
        connection = getConnection();
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL_LAPTOPS);

            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt("productID"),
                        resultSet.getString("image"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("categoryID"),
                        resultSet.getBoolean("status")
                ));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User checkLogin(String username, String password) {
        connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_UP);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getDate(7),
                        resultSet.getString(8),
                        resultSet.getBoolean(9)
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
