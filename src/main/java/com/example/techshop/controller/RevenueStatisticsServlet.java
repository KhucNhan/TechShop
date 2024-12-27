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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet(value = "/revenue-statistics")
public class RevenueStatisticsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy năm từ request (nếu có)
        String year = request.getParameter("year");
        String month = request.getParameter("month");

        if (year == null || month == null) {
            year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR)); // Mặc định năm hiện tại
            month = String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1); // mặc định là tháng hiện tại
        }

        RevenueStatisticsService revenueStatisticsService = new RevenueStatisticsService();
        List<RevenueStatistics> statistics = new ArrayList<>();
        if (month.equals("0")) {
            statistics = revenueStatisticsService.getRevenueStatisticsByYear(year);
        } else {
            statistics = revenueStatisticsService.getRevenueStatisticsByMonth(year, month);
        }

        request.setAttribute("statistics", statistics);
        request.setAttribute("year", year);
        request.setAttribute("month", month);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/statistical/revenue-statistics.jsp");
        dispatcher.forward(request, response);
    }
}
