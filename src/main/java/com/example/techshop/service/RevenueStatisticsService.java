package com.example.techshop.service;

import com.example.techshop.model.RevenueStatistics;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RevenueStatisticsService {
    private static final String GET_REVENUE_STATISTICS_SQL = "SELECT MONTH(orderDate) AS month, SUM(total) AS totalRevenue FROM orders WHERE status = 'Paid' AND YEAR(orderDate) = ? GROUP BY MONTH(orderDate) ORDER BY MONTH(orderDate)";

    public List<RevenueStatistics> getRevenueStatisticsByYear(String year) {
        List<RevenueStatistics> statisticsList = new ArrayList<>();
        DAO dao = new DAO();
        try (Connection conn = dao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_REVENUE_STATISTICS_SQL)) {

            stmt.setString(1, year);
            ResultSet rs = stmt.executeQuery();

            // Khởi tạo 12 tháng với doanh thu mặc định là 0
            double[] monthlyRevenue = new double[12];

            while (rs.next()) {
                int month = rs.getInt("month") - 1; // Trừ 1 vì tháng bắt đầu từ 1
                double totalRevenue = rs.getDouble("totalRevenue");
                monthlyRevenue[month] = totalRevenue; // Lưu doanh thu vào đúng tháng
            }

            for (int i = 0; i < 12; i++) {
                statisticsList.add(new RevenueStatistics(i + 1, monthlyRevenue[i]));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statisticsList;
    }
}

