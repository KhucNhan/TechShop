package com.example.techshop.controller;

import com.example.techshop.model.Product;
import com.example.techshop.service.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends HttpServlet {
    private DAO dao;

    @Override
    public void init() throws ServletException {
        dao = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateProduct(req, resp);
                break;
            case "delete":
                showDeleteProduct(req, resp);
                break;
            case "edit":
                showUpdateProduct(req, resp);
                break;
            case "users":
                goToUsers(req, resp);
                break;
            default:
                showAllProducts(req, resp);
                break;
        }
    }

    private void goToUsers(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect("admins");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showUpdateProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("productID"));
        Product product = dao.selectProduct(id);

        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showDeleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("productID"));
        dao.deleteProduct(id);

        List<Product> products = dao.selectAllProducts();
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showCreateProduct(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
            default:
                break;
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("productID"));
        String image = req.getParameter("image");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));
        boolean status = Boolean.parseBoolean(req.getParameter("status"));
        System.out.println(status);

        Product product = new Product(id, image, name, description, price, quantity, categoryID, status);

        dao.updateProduct(id, product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));

        Product product = new Product(name, description, price, quantity, categoryID);
        dao.insertProduct(product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAllProducts(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = dao.selectAllProducts();
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
