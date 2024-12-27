package com.example.techshop.service;

import com.example.techshop.model.*;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DAO implements IDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/techshop?useUnicode=true&characterEncoding=UTF-8";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "nhan771026";
    private static final String USER_SELECT_PRODUCT_BY_NAME = "select * from products where name like ? and status = true";
    private static final String SELECT_PRODUCT_BY_NAME = "select * from products where name like ?";
    private static final String SELECT_INSERT_USER_STATUS = "select status from users where name = ? and username = ? and password = ?";
    private static final String SELECT_USER_BY_UP = "select * from users where username = ? and password = ?";
    private static final String INSERT_USERS_SQL = "INSERT INTO Users (name, username, password, gender, dateOfBirth) VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_USER_WITH_IMAGE_SQL = "INSERT INTO Users (image, name, username, password, gender, dateOfBirth) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_ID = "select * from users where userID =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String SEARCH_USERS = "select * from users where name like ?";
    private static final String DELETE_USERS_SQL = "update users set status = false where userID = ?;";
    private static final String UPDATE_USERS_SQL = "update users set image = ?, name = ?, username = ?, password = ?, gender = ?, dateOfBirth = ?, role = ?, status = ? where userID = ?;";

    private static final String SELECT_INSERT_PRODUCT_STATUS = "select status from products where name = ?";
    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO Products (name, description, price, quantity, categoryID) VALUES (?, ?, ?, ?, ?);";
    private static final String INSERT_PRODUCTS_WITH_IMG_SQL = "INSERT INTO Products (image, name, description, price, quantity, categoryID) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = "select * from products where productID =?";
    private static final String SELECT_ALL_PRODUCTS = "select * from products group by productID order by productID desc";
    private static final String SELECT_ACTIVE_PRODUCTS = "select * from products where status = true";
    private static final String DELETE_PRODUCTS_SQL = "update products set status = false where productID = ?;";
    private static final String UPDATE_PRODUCTS_SQL = "update products set image = ?, name = ?, description = ?, price = ?, quantity = ?, categoryID = ?, status = ? where productID = ?;";
    private static final String SELECT_ALL_PHONES = "select * from products where categoryID = 1";
    private static final String SELECT_ALL_LAPTOPS = "select * from products where categoryID = 2";


    private static final String INSERT_NEW_ORDER = "insert into orders (userID, orderDate, total) value (?, ?, ?)";
    private static final String INSERT_NEW_ORDERDETAIL = "insert into orderdetails (orderID, productID, quantity, price, totalPrice) values (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_ORDERS = "select o.orderID, o.userID, o.orderDate, o.total, o.status, u.name from orders o join users u on o.userID = u.userID group by orderID order by orderID desc";
    private static final String SELECT_ORDER = "select * from orders where orderId = ?";
    private static final String SELECT_ORDERS_BY_USERID = "select * from orders where userID = ?";

    private static final String UPDATE_ORDER = "update orders set userID = ?, orderDate = ?, total = ?, status = ? where orderID = ?";

    private static final String SELECT_ORDERDETAILS_BY_ORDERID = "select * from orderdetails where orderID = ?";

    private static final String SELECT_ALL_CATEGORIES = "select * from categories";

    public List<Category> selectAllCategories() {
        connection = getConnection();
        PreparedStatement preparedStatement;
        List<Category> categories = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                categories.add(new Category(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }

            if (categories.isEmpty()) {
                return null;
            }

            return categories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<OrderDetails> selectOrderdetailsByOrderID(int orderID) {
        connection = getConnection();
        PreparedStatement preparedStatement;
        List<OrderDetails> orderDetails = new ArrayList<>();
        DAO dao = new DAO();
        Order order = dao.selectOrder(orderID);

        try {
            preparedStatement = connection.prepareStatement(SELECT_ORDERDETAILS_BY_ORDERID);
            preparedStatement.setInt(1, orderID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orderDetails.add(new OrderDetails(
                        resultSet.getInt(1),
                        order,
                        dao.selectProduct(resultSet.getInt(3)),
                        resultSet.getInt(4),
                        resultSet.getDouble(5),
                        resultSet.getDouble(6)
                ));
            }

            if (orderDetails.isEmpty()) {
                return null;
            }
            return orderDetails;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order selectOrder(int orderID) {
        connection = getConnection();
        PreparedStatement preparedStatement;
        List<Order> orders = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_ORDER);
            preparedStatement.setInt(1, orderID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new Order(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getTimestamp(3).toLocalDateTime(),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                );
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> selectAllOrders() {
        connection = getConnection();
        PreparedStatement preparedStatement;
        List<Order> orders = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orders.add(new Order(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getTimestamp(3).toLocalDateTime(),
                        resultSet.getDouble(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                ));
            }

            if (orders.isEmpty()) {
                return null;
            } else {
                return orders;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> selectAllOrdersByUser(int userID) {
        connection = getConnection();
        PreparedStatement preparedStatement;
        List<Order> orders = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(SELECT_ORDERS_BY_USERID);
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                orders.add(new Order(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getTimestamp(3).toLocalDateTime(),
                        resultSet.getDouble(4),
                        resultSet.getString(5)
                ));
            }

            if (orders.isEmpty()) {
                return null;
            } else {
                return orders;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateOrder(int orderID, Order order) {
        connection = getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ORDER);
            preparedStatement.setInt(5, order.getOrderID());
            preparedStatement.setInt(1, order.getUserID());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
            preparedStatement.setDouble(3, order.getTotal());
            preparedStatement.setString(4, order.getStatus());
            int row = preparedStatement.executeUpdate();

            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int insertOrder(Order order) {
        connection = getConnection();
        PreparedStatement preparedStatement;
        LocalDateTime currentDateTime = LocalDateTime.now();
        ResultSet resultSet;
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, order.getUserID());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(currentDateTime));
            preparedStatement.setDouble(3, order.getTotal());
            int row = preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (row != 0) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean insertOrderdetail(int orderID, OrderDetails orderDetails) {
        connection = getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(INSERT_NEW_ORDERDETAIL);
            preparedStatement.setInt(1, orderID);
            preparedStatement.setInt(2, orderDetails.getProduct().getProductID());
            preparedStatement.setInt(3, orderDetails.getQuantity());
            preparedStatement.setDouble(4, orderDetails.getPrice());
            preparedStatement.setDouble(5, orderDetails.getTotalPrice());
            int row = preparedStatement.executeUpdate();

            return row > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

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

            boolean status = false;
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
    public boolean insertUserWithImage(User user) {
        connection = getConnection();
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(SELECT_INSERT_USER_STATUS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();

            boolean status = false;
            if (resultSet.next()) {
                status = resultSet.getBoolean(1);
            }

            int row = 0;
            if (!status) {
                preparedStatement = connection.prepareStatement(INSERT_USER_WITH_IMAGE_SQL);
                preparedStatement.setString(1, user.getImage());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getUsername());
                preparedStatement.setString(4, user.getPassword());
                preparedStatement.setString(5, user.getGender());
                preparedStatement.setString(6, user.getDateOfBirth().toString());
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
    public List<User> searchUserByName(String value) {
        connection = getConnection();
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_USERS);
            preparedStatement.setString(1, "%" + value + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

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
    public Map<List<Double>, List<Product>> getBestSellProduct(int numberOfProduct) {
        connection = getConnection();
        CallableStatement callableStatement;
        Map<List<Double>, List<Product>> map = new HashMap<>();
        List<Product> products = new ArrayList<>();
        List<Double> totalRevenue = new ArrayList<>();

        try {
            callableStatement = connection.prepareCall("call getBestSellProduct(?)");
            callableStatement.setInt(1, numberOfProduct);
            ResultSet resultSet = callableStatement.executeQuery();
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
                totalRevenue.add(resultSet.getDouble(9));
            }

            if (products.isEmpty()) {
                return null;
            } else {
                map.put(totalRevenue, products);
                return map;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<List<Integer>, List<Product>> getBestSellProductByQuantity(int numberOfProduct) {
        connection = getConnection();
        CallableStatement callableStatement;
        Map<List<Integer>, List<Product>> map = new HashMap<>();
        List<Product> products = new ArrayList<>();
        List<Integer> totalQuantity = new ArrayList<>();
        try {
            callableStatement = connection.prepareCall("call getBestSellProductByQuantity(?)");
            callableStatement.setInt(1, numberOfProduct);
            ResultSet resultSet = callableStatement.executeQuery();
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
                totalQuantity.add(resultSet.getInt(9));
            }

            if (products.isEmpty()) {
                return null;
            } else {
                map.put(totalQuantity, products);
                return map;
            }
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

            boolean status = false;
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

            boolean status = false;
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
    public List<Product> userSearchProduct(String value) {
        connection = getConnection();
        List<Product> searchProducts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(USER_SELECT_PRODUCT_BY_NAME);
            preparedStatement.setString(1, "%" + value + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                searchProducts.add(new Product(
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
            return searchProducts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> adminSearchProduct(String value) {
        connection = getConnection();
        List<Product> searchProducts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_NAME);
            preparedStatement.setString(1, "%" + value + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                searchProducts.add(new Product(
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
            return searchProducts;
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
