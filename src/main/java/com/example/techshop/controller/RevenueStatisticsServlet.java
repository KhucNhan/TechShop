package com.example.techshop.controller;

import com.example.techshop.model.RevenueStatistics;
import com.example.techshop.service.RevenueStatisticsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

@WebServlet(value = "/revenue-statistics")
public class RevenueStatisticsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy năm từ request (nếu có)
        String year = request.getParameter("year");

        if (year == null) {
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)); // Mặc định năm hiện tại
        }
        RevenueStatisticsService revenueStatisticsService = new RevenueStatisticsService();
        // Lấy dữ liệu doanh thu theo tháng từ service
        List<RevenueStatistics> statistics = revenueStatisticsService.getRevenueStatisticsByYear(year);

        // Gửi dữ liệu đến JSP
        request.setAttribute("statistics", statistics);
        request.setAttribute("year", year);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/statistical/revenue-statistics.jsp");
        dispatcher.forward(request, response);
    }
}
