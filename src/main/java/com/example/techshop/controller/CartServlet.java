package com.example.techshop.controller;

import com.example.techshop.model.OrderDetails;
import com.example.techshop.model.Product;
import com.example.techshop.service.DAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {
    private final DAO dao = new DAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("web/cart.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            action = "";
        }


        switch (action) {
            case "home":
                List<Product> products = dao.selectAllProducts();
                req.setAttribute("products", products);
                RequestDispatcher dispatcher = req.getRequestDispatcher("web/product.jsp");
                dispatcher.forward(req, resp);
                break;
            case "decreaseQuantity":
            case "increaseQuantity":
                buttonChangeQuantity(req, resp, action);
                break;
            case "changeQuantity":
                changeQuantity(req, resp);
                break;
        }
    }

    private void changeQuantity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        int productID = Integer.parseInt(req.getParameter("productID"));
        Product product = dao.selectProduct(productID);

        HttpSession session = req.getSession();
        Map<Integer, OrderDetails> cart = (Map<Integer, OrderDetails>) session.getAttribute("cart");
        OrderDetails orderDetails = cart.get(productID);

        if (quantity > product.getQuantity()) {
            String message = "Out of stock";
            req.setAttribute("message", message);
            req.getRequestDispatcher("web/cart.jsp").forward(req, resp);
            return;
        } else if (quantity < 0) {
            String message = "Invalidate number";
            req.setAttribute("message", message);
            req.getRequestDispatcher("web/cart.jsp").forward(req, resp);
            return;
        }

        orderDetails.setQuantity(quantity);

        cart.replace(productID, orderDetails);
        session.setAttribute("cart", cart);

        RequestDispatcher dispatcher = req.getRequestDispatcher("web/cart.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void buttonChangeQuantity(HttpServletRequest req, HttpServletResponse resp, String action) throws ServletException, IOException {
        int productID = Integer.parseInt(req.getParameter("productID"));
        HttpSession session = req.getSession();
        Map<Integer, OrderDetails> cart = (Map<Integer, OrderDetails>) session.getAttribute("cart");
        OrderDetails orderDetails = cart.get(productID);
        
        switch (action) {
            case "decreaseQuantity":
                if (orderDetails.getQuantity() == 0) {
                    String message = "Invalidate number";
                    req.setAttribute("message", message);
                    req.getRequestDispatcher("web/cart.jsp").forward(req, resp);
                    return;
                }

                orderDetails.setQuantity(orderDetails.getQuantity() - 1);
                break;
            case "increaseQuantity":
                Product product = dao.selectProduct(productID);
                if (orderDetails.getQuantity() == product.getQuantity()) {
                    String message = "Out of stock";
                    req.setAttribute("message", message);
                    req.getRequestDispatcher("web/cart.jsp").forward(req, resp);
                    return;
                }

                orderDetails.setQuantity(orderDetails.getQuantity() + 1);
                break;
        }

        cart.replace(productID, orderDetails);
        session.setAttribute("cart", cart);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("web/cart.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

