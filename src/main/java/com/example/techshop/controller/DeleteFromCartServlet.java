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
import java.util.List;
import java.util.Map;

@WebServlet(name = "DeleteFromCartServlet", value = "/delete-from-cart")
public class DeleteFromCartServlet extends HttpServlet {
    private final DAO dao = new DAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int productID = Integer.parseInt(req.getParameter("productID"));

        HttpSession session = req.getSession();
        Object object = session.getAttribute("cart");

        if (object != null) {
            Map<Integer, OrderDetails> cart = (Map<Integer, OrderDetails>) object;
            cart.remove(productID);
            session.setAttribute("cart", cart);
            session.setAttribute("cartItemCount", cart.size());
        }
        resp.sendRedirect(req.getContextPath() + "/cart");
    }
}
