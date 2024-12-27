package com.example.techshop.service;

import com.example.techshop.model.RevenueStatistics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RevenueStatisticsService {
    private static final String GET_REVENUE_STATISTICS_BY_YEAR_SQL = "SELECT MONTH(orderDate) AS month, SUM(total) AS totalRevenue FROM orders WHERE status = 'Paid' AND YEAR(orderDate) = ? GROUP BY MONTH(orderDate) ORDER BY MONTH(orderDate)";
    private static final String GET_REVENUE_STATISTICS_BY_MONTH_SQL = "SELECT DAY(orderDate) AS day, SUM(total) AS totalRevenue FROM orders WHERE status = 'Paid' AND YEAR(orderDate) = ? AND MONTH(orderDate) = ? GROUP BY DAY(orderDate) ORDER BY DAY(orderDate)";


    public List<RevenueStatistics> getRevenueStatisticsByYear(String year) {
        List<RevenueStatistics> statisticsList = new ArrayList<>();
        DAO dao = new DAO();
        try (Connection conn = dao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_REVENUE_STATISTICS_BY_YEAR_SQL)) {

            stmt.setString(1, year);
            ResultSet rs = stmt.executeQuery();

            double[] monthlyRevenue = new double[12];

            while (rs.next()) {
                int month = rs.getInt("month") - 1;
                double totalRevenue = rs.getDouble("totalRevenue");
                monthlyRevenue[month] = totalRevenue;
            }

            for (int i = 0; i < 12; i++) {
                statisticsList.add(new RevenueStatistics(i + 1, monthlyRevenue[i]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statisticsList;
    }

    public List<RevenueStatistics> getRevenueStatisticsByMonth(String year, String month) {
        List<RevenueStatistics> statisticsList = new ArrayList<>();
        DAO dao = new DAO();
        try (Connection conn = dao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_REVENUE_STATISTICS_BY_MONTH_SQL)) {

            stmt.setString(1, year);
            stmt.setString(2, month);
            ResultSet rs = stmt.executeQuery();

            double[] dailyRevenue = new double[31];

            while (rs.next()) {
                int day = rs.getInt("day") - 1;
                double totalRevenue = rs.getDouble("totalRevenue");
                dailyRevenue[day] = totalRevenue;
            }

            for (int i = 0; i < 31; i++) {
                statisticsList.add(new RevenueStatistics(i + 1, dailyRevenue[i]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statisticsList;
    }
}

