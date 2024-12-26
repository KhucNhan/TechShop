package com.example.techshop.controller;

import com.example.techshop.model.Product;
import com.example.techshop.model.User;
import com.example.techshop.service.DAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "StatisticalServlet", value = "/statistical")
public class StatisticalServlet extends HttpServlet {
    private DAO dao;

    @Override
    public void init() throws ServletException {
        dao = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        int userID = (Integer) session.getAttribute("currentUserID");
        User user = dao.selectUser(userID);

        Map<List<Double>, List<Product>> productsBestSellMap = new HashMap<>();
        Map<List<Integer>, List<Product>> productsBestSellByQuantityMap = new HashMap<>();

        if (user.getRole().equalsIgnoreCase("admin")) {
            productsBestSellMap = dao.getBestSellProduct(dao.selectAllProducts().size());
            productsBestSellByQuantityMap = dao.getBestSellProductByQuantity(dao.selectAllProducts().size());
        } else {
            productsBestSellMap = dao.getBestSellProduct(dao.selectActiveProducts().size());
            productsBestSellByQuantityMap = dao.getBestSellProductByQuantity(dao.selectActiveProducts().size());
        }


        List<Product> productsBestSell =  new ArrayList<>();
        List<Product> productsBestSellByQuantity =  new ArrayList<>();
        List<Double> totalRevenue = new ArrayList<>();
        List<Integer> totalQuantity = new ArrayList<>();

        for (Map.Entry<List<Double>, List<Product>> entry : productsBestSellMap.entrySet()) {
            totalRevenue = entry.getKey();
            productsBestSell = entry.getValue();
        }

        for (Map.Entry<List<Integer>, List<Product>> entry : productsBestSellByQuantityMap.entrySet()) {
            totalQuantity = entry.getKey();
            productsBestSellByQuantity = entry.getValue();
        }

        req.setAttribute("user", user);
        req.setAttribute("productsBestSell", productsBestSell);
        req.setAttribute("productsBestSellByQuantity", productsBestSellByQuantity);
        req.setAttribute("totalRevenue", totalRevenue);
        req.setAttribute("totalQuantity", totalQuantity);
        req.getRequestDispatcher("web/statistical.jsp").forward(req, resp);
    }
}
