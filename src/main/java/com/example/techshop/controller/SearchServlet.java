package com.example.techshop.controller;

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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/search")
public class SearchServlet extends HttpServlet {
    private DAO dao;
    @Override
    public void init() throws ServletException {
        dao = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String value = req.getParameter("value");
        String servletPath = req.getParameter("servletPath");
        HttpSession session = req.getSession();
        int userID = (Integer) session.getAttribute("currentUserID");
        User user = dao.selectUser(userID);
        List<Product> products = new ArrayList<>();
        RequestDispatcher requestDispatcher;

        if (user.getRole().equalsIgnoreCase("admin")) {
            if (servletPath.contains("user")) {
                List<User> users = dao.searchUserByName(value);
                req.setAttribute("users", users);
                requestDispatcher = req.getRequestDispatcher("user/list.jsp");
            } else {
                products = dao.adminSearchProduct(value);
                req.setAttribute("products", products);
                requestDispatcher = req.getRequestDispatcher("product/list.jsp");
            }
        } else {
            products = dao.userSearchProduct(value);
            req.setAttribute("userAction", true);
            req.setAttribute("products", products);
            requestDispatcher = req.getRequestDispatcher("web/product.jsp");
        }
        requestDispatcher.forward(req, resp);
    }
}
