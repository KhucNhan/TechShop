package com.example.techshop.controller;

import com.example.techshop.model.Category;
import com.example.techshop.model.Product;
import com.example.techshop.model.User;
import com.example.techshop.service.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

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
            case "active":
                showActiveProduct(req, resp);
                break;
            case "edit":
                showUpdateProduct(req, resp);
                break;
            case "users":
                goToUsers(req, resp);
                break;
            case "detail":
                productDetail(req, resp);
                break;
            case "phone":
                showPhones(req, resp);
                break;
            case "laptop":
                showLaptops(req, resp);
                break;
            default:
                showAllProducts(req, resp);
                break;
        }
    }

    private void showActiveProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("productID"));
        Product product = dao.selectProduct(id);
        product.setStatus(true);
        dao.updateProduct(id, product);

        List<Product> products = dao.selectAllProducts();
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showLaptops(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = dao.selectAllLaptops();
        req.setAttribute("products", products);

        RequestDispatcher dispatcher;
        dispatcher = req.getRequestDispatcher("web/product.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showPhones(HttpServletRequest req, HttpServletResponse resp) {
        List<Product> products = dao.selectAllPhones();
        req.setAttribute("products", products);

        RequestDispatcher dispatcher;
        dispatcher = req.getRequestDispatcher("web/product.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void productDetail(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("productID"));
        Product product = dao.selectProduct(id);

        req.setAttribute("product", product);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/detail.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void goToUsers(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect("users");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showUpdateProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("productID"));
        Product product = dao.selectProduct(id);

        req.setAttribute("product", product);
        List<Category> categories = dao.selectAllCategories();
        req.setAttribute("categories", categories);
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
        List<Category> categories = dao.selectAllCategories();
        req.setAttribute("categories", categories);
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

        Product product = dao.selectProduct(id);
        req.setAttribute("product", product);

        String mess = "Not null requirement";

        if (checkNullInput(req, resp, image, mess)) return;

        if (checkNullInput(req, resp, name, mess)) return;

        if (checkNullInput(req, resp, description, mess)) return;

        if (checkNullInput(req, resp, String.valueOf(price), mess)) return;

        if (checkNullInput(req, resp, String.valueOf(categoryID), mess)) return;

        if (checkNullInput(req, resp, String.valueOf(status), mess)) return;


        if(String.valueOf(quantity).isEmpty() || quantity < 0) {
            req.setAttribute("message", "Not null and invalidate number requirement");
            req.setAttribute("alertType", "alert");
            List<Category> categories = dao.selectAllCategories();
            req.setAttribute("categories", categories);
            RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }


        mess = "Update product success";
        req.setAttribute("message", mess);
        req.setAttribute("alertType", "success");

        product.setStatus(false);
        dao.updateProduct(id, product);

        product = new Product(image, name, description, price, quantity, categoryID);
        dao.insertProductWithImage(product);

        RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean checkNullInput(HttpServletRequest req, HttpServletResponse resp, String input, String mess) {
        if(input.isEmpty()) {
            req.setAttribute("message", mess);
            req.setAttribute("alertType", "alert");
            RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return true;
        }
        return false;
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) {
        String image = req.getParameter("image");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String priceStr = req.getParameter("price");
        String quantityStr = req.getParameter("quantity");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int categoryID = Integer.parseInt(req.getParameter("categoryID"));

        if(categoryID == 0 || name.isEmpty() || description.isEmpty() || priceStr.isEmpty() || quantityStr.isEmpty()) {
            req.setAttribute("message", "Not null requirement.");
            req.setAttribute("alertType", "warning");
            List<Category> categories = dao.selectAllCategories();
            req.setAttribute("categories", categories);
            RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        if (!image.isEmpty()) {
            Product product = new Product(image, name, description, price, quantity, categoryID);
            if (dao.insertProductWithImage(product)) {
                req.setAttribute("message", "Create product success");
                req.setAttribute("alertType", "success");
            } else {
                req.setAttribute("message", "This product name is exist");
                req.setAttribute("alertType", "warning");
            }
        } else {
            Product product = new Product(name, description, price, quantity, categoryID);
            if (dao.insertProduct(product)) {
                req.setAttribute("message", "Create product success");
                req.setAttribute("alertType", "success");
            } else {
                req.setAttribute("message", "This product name is exist");
                req.setAttribute("alertType", "warning");
            }
        }
        List<Category> categories = dao.selectAllCategories();
        req.setAttribute("categories", categories);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void showAllProducts(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int userID = (Integer) session.getAttribute("currentUserID");
        User user = dao.selectUser(userID);
        RequestDispatcher dispatcher;

        if (user.getRole().equalsIgnoreCase("admin")) {
            List<Product> products = dao.selectAllProducts();
            req.setAttribute("products", products);
            dispatcher = req.getRequestDispatcher("product/list.jsp");
        } else {
            List<Product> products = dao.selectActiveProducts();
            req.setAttribute("products", products);
            dispatcher = req.getRequestDispatcher("web/product.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
