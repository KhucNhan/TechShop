package com.example.techshop.controller;

import com.example.techshop.model.Order;
import com.example.techshop.model.OrderDetails;
import com.example.techshop.model.Product;
import com.example.techshop.model.User;
import com.example.techshop.service.DAO;
import com.sun.org.apache.xpath.internal.operations.Or;
import jdk.vm.ci.meta.Local;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            case "filter":
                filterOrder(req, resp);
                break;
            default:
                showAllOrders(req, resp);
                break;
        }
    }

    protected void filterOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String start = req.getParameter("start");
        String end = req.getParameter("end");

        List<Order> orders = dao.selectAllOrders();
        List<Order> filteredOrders = new ArrayList<>();
        if (start != null && end != null && !start.isEmpty() && !end.isEmpty()) {
            LocalDateTime startTime = LocalDateTime.parse(start.replace("T", " ") + ":00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            LocalDateTime endTime = LocalDateTime.parse(end.replace("T", " ") + ":59", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            
            for(Order order : orders) {
                LocalDateTime orderDate = order.getOrderDate();
                if (orderDate.isAfter(startTime) && orderDate.isBefore(endTime)) {
                    filteredOrders.add(order);
                }
            }

            if (filteredOrders.isEmpty()) {
                req.setAttribute("message", "There are no order between this time");
                req.setAttribute("alertType", "alert");
                req.setAttribute("orders", orders);
            } else {
                req.setAttribute("orders", filteredOrders);
            }

        } else {
            req.setAttribute("message", "Not null requirement");
            req.setAttribute("alertType", "warning");
            req.setAttribute("orders", orders);
        }

        HttpSession session = req.getSession();
        int userID = (int) session.getAttribute("currentUserID");
        User user = dao.selectUser(userID);
        req.setAttribute("user", user);

        RequestDispatcher dispatcher = req.getRequestDispatcher("order/list.jsp");
        dispatcher.forward(req, resp);
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

        HttpSession session = req.getSession();
        int userID = (Integer) session.getAttribute("currentUserID");
        User user = dao.selectUser(userID);

        List<OrderDetails> orderDetails = dao.selectOrderdetailsByOrderID(orderID);
        List<Product> products = dao.selectAllProducts();

        for (OrderDetails od : orderDetails) {
            for (Product product : products) {
                if (od.getProduct().getProductID() == product.getProductID()) {
                    product.setQuantity(product.getQuantity() - od.getQuantity());
                    dao.updateProduct(product.getProductID(), product);
                }
            }
        }

        List<Order> orders = dao.selectAllOrders();
        req.setAttribute("orders", orders);
        req.setAttribute("user", user);
        req.getRequestDispatcher("order/list.jsp").forward(req, resp);
    }
    private void cancelOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int orderID = Integer.parseInt(req.getParameter("orderID"));
        Order order = dao.selectOrder(orderID);
        order.setStatus("Canceled");
        dao.updateOrder(orderID, order);

        HttpSession session = req.getSession();
        int userID = (Integer) session.getAttribute("currentUserID");
        User user = dao.selectUser(userID);

        List<Order> orders = dao.selectAllOrders();
        req.setAttribute("orders", orders);
        req.setAttribute("user", user);
        req.getRequestDispatcher("order/list.jsp").forward(req, resp);
    }

    private void showAllOrders(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        int userID = (Integer) session.getAttribute("currentUserID");
        User user = dao.selectUser(userID);
        RequestDispatcher dispatcher;

        List<Order> orders;
        if (user.getRole().equalsIgnoreCase("admin")) {
            orders = dao.selectAllOrders();
        } else {
            orders = dao.selectAllOrdersByUser(userID);
        }
        req.setAttribute("orders", orders);
        req.setAttribute("user", user);
        dispatcher = req.getRequestDispatcher("order/list.jsp");

        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
