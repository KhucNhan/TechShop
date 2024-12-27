package com.example.techshop.controller;

import com.example.techshop.model.Order;
import com.example.techshop.model.OrderDetails;
import com.example.techshop.model.Product;
import com.example.techshop.service.DAO;
import com.sun.org.apache.xpath.internal.operations.Or;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

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
            case "selected":
                setSelected(req, resp);
                break;
            case "selectedAll":
                selectAll(req, resp);
                break;
            case "buy":
                buy(req, resp);
                break;
        }
    }

    private void selectAll(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Map<Integer, OrderDetails> cart = (Map<Integer, OrderDetails>) session.getAttribute("cart");
        String selectAll = req.getParameter("selectAll");
        if (selectAll == null) {
            selectAll = "";
        }

        for(Map.Entry<Integer, OrderDetails> cartItem : cart.entrySet()) {
            cartItem.getValue().getProduct().setSelected(selectAll.equals("on"));
        }

        if (selectAll.isEmpty()) {
            selectAll = null;
        }

        double total = sendTotal(req, resp);
        req.setAttribute("total", total);
        req.setAttribute("allSelected", selectAll);

        session.setAttribute("cart", cart);

        RequestDispatcher dispatcher = req.getRequestDispatcher("web/cart.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void buy(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Map<Integer, OrderDetails> cart = (Map<Integer, OrderDetails>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            req.setAttribute("message", "You don't have anything in your cart.");
            req.setAttribute("alertType", "warning");
            session.setAttribute("cartItemCount", 0);
            RequestDispatcher dispatcher = req.getRequestDispatcher("web/cart.jsp");
            try {
                dispatcher.forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        Order order = new Order();
        order.setUserID((Integer) session.getAttribute("currentUserID"));
        order.setTotal(sendTotal(req, resp));
        int orderID = dao.insertOrder(order);

        Iterator<Map.Entry<Integer, OrderDetails>> iterator = cart.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, OrderDetails> cartItem = iterator.next();

            if (cartItem.getValue().getProduct().isSelected()) {
                cartItem.getValue().setTotalPrice(cartItem.getValue().getQuantity() * cartItem.getValue().getPrice());
                dao.insertOrderdetail(orderID, cartItem.getValue());
                Product product = cartItem.getValue().getProduct();
                product.setQuantity(product.getQuantity() - cartItem.getValue().getQuantity());
                iterator.remove();
            }
        }

        session.setAttribute("cart", cart);
        session.setAttribute("cartItemCount", cart.size());
        RequestDispatcher dispatcher = req.getRequestDispatcher("web/cart.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private double sendTotal(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Map<Integer, OrderDetails> cart = (Map<Integer, OrderDetails>) session.getAttribute("cart");
        double total = 0;

        for(Map.Entry<Integer, OrderDetails> cartItem : cart.entrySet()) {
            if (cartItem.getValue().getProduct().isSelected()) {
                total += cartItem.getValue().getQuantity() * cartItem.getValue().getPrice();
            }
        }
        return total;
    }

    private void setSelected(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Map<Integer, OrderDetails> cart = (Map<Integer, OrderDetails>) session.getAttribute("cart");
        int productID = Integer.parseInt(req.getParameter("productID"));

        cart.get(productID).getProduct().setSelected(!cart.get(productID).getProduct().isSelected());

        boolean selectedAll = true;
        for(Map.Entry<Integer, OrderDetails> cartItem : cart.entrySet()) {
            if (!cartItem.getValue().getProduct().isSelected()) {
                selectedAll = false;
                break;
            }
        }

        if (selectedAll) {
            req.setAttribute("allSelected", "null");
        } else {
            req.setAttribute("allSelected", null);
        }

        double total = sendTotal(req, resp);
        req.setAttribute("total", total);

        session.setAttribute("cart", cart);

        RequestDispatcher dispatcher = req.getRequestDispatcher("web/cart.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void changeQuantity(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String quantityStr = req.getParameter("quantity");
        int productID = Integer.parseInt(req.getParameter("productID"));

        if (quantityStr == null || quantityStr.isEmpty()) {
            req.setAttribute("productID", productID);
            req.getRequestDispatcher("/delete-from-cart").forward(req, resp);
            return;
        }

        int quantity = Integer.parseInt(quantityStr);
        Product product = dao.selectProduct(productID);

        HttpSession session = req.getSession();
        Map<Integer, OrderDetails> cart = (Map<Integer, OrderDetails>) session.getAttribute("cart");
        OrderDetails orderDetails = cart.get(productID);

        if (quantity > product.getQuantity()) {
            req.setAttribute("oos", productID);
            req.getRequestDispatcher("web/cart.jsp").forward(req, resp);
            return;
        } else if (quantity < 0) {
            req.setAttribute("in", productID);
            req.getRequestDispatcher("web/cart.jsp").forward(req, resp);
            return;
        }

        orderDetails.setQuantity(quantity);

        double total = sendTotal(req, resp);
        req.setAttribute("total", total);

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
                if (orderDetails.getProduct().isStatus()) {
                    Product product = orderDetails.getProduct();
                    orderDetails.getProduct().setStatus(true);
                    dao.updateProduct(productID, orderDetails.getProduct());
                }

                if (orderDetails.getQuantity() == 1) {
                    cart.remove(productID);
                    session.setAttribute("cart", cart);
                    session.setAttribute("cartItemCount", cart.size());
                    req.getRequestDispatcher("web/cart.jsp").forward(req, resp);
                    return;
                }

                orderDetails.setQuantity(orderDetails.getQuantity() - 1);
                break;
            case "increaseQuantity":
                Product product = dao.selectProduct(productID);
                if (orderDetails.getQuantity() == product.getQuantity()) {
                    req.setAttribute("oos", productID);
                    req.getRequestDispatcher("web/cart.jsp").forward(req, resp);
                    return;
                }

                orderDetails.setQuantity(orderDetails.getQuantity() + 1);
                break;
        }

        double total = sendTotal(req, resp);
        req.setAttribute("total", total);
        session.setAttribute("cart", cart);
        
        RequestDispatcher dispatcher = req.getRequestDispatcher("web/cart.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

