package com.example.techshop.controller;

import com.example.techshop.model.Order;
import com.example.techshop.model.OrderDetails;
import com.example.techshop.model.Product;
import com.example.techshop.model.User;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "OrderServlet", value = "/orders")
public class OrderServlet extends HttpServlet {
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
            case "accept":
                acceptOrder(req, resp);
                break;
            case "cancel":
                cancelOrder(req, resp);
                break;
            case "detail":
                showOrderDetail(req, resp);
                break;
            default:
                showAllOrders(req, resp);
                break;
        }
    }

    private void showOrderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderID = Integer.parseInt(req.getParameter("orderID"));
        Order order = dao.selectOrder(orderID);
        List<OrderDetails> orderDetails = dao.selectOrderdetailsByOrderID(orderID);

        HttpSession session = req.getSession();
        int userID = (int) session.getAttribute("currentUserID");
        User user = dao.selectUser(userID);

        int totalQuantity = 0;
        for(OrderDetails orderDetail : orderDetails) {
            totalQuantity += orderDetail.getQuantity();
        }

        req.setAttribute("totalQuantity", totalQuantity);
        req.setAttribute("orderDetails", orderDetails);
        req.setAttribute("order", order);
        req.setAttribute("user", user);
        req.getRequestDispatcher("order/detail.jsp").forward(req, resp);
    }

    private void acceptOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderID = Integer.parseInt(req.getParameter("orderID"));
        Order order = dao.selectOrder(orderID);
        order.setStatus("Paid");
        dao.updateOrder(orderID, order);

        List<Order> orders = dao.selectAllOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("order/list.jsp").forward(req, resp);
    }
    private void cancelOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderID = Integer.parseInt(req.getParameter("orderID"));
        Order order = dao.selectOrder(orderID);
        order.setStatus("Canceled");
        dao.updateOrder(orderID, order);

        List<Order> orders = dao.selectAllOrders();
        req.setAttribute("orders", orders);
        req.getRequestDispatcher("order/list.jsp").forward(req, resp);
    }

    private void showAllOrders(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int userID = (Integer) session.getAttribute("currentUserID");
        User user = dao.selectUser(userID);
        RequestDispatcher dispatcher;

        if (user.getRole().equalsIgnoreCase("admin")) {
            List<Order> orders = dao.selectAllOrders();
            req.setAttribute("orders", orders);
            dispatcher = req.getRequestDispatcher("order/list.jsp");
        } else {
            List<Order> orders = dao.selectAllOrdersByUser(userID);
            req.setAttribute("orders", orders);
            req.setAttribute("user", user);
            dispatcher = req.getRequestDispatcher("web/order.jsp");
        }

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
