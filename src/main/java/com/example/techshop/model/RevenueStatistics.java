package com.example.techshop.model;

public class RevenueStatistics {
    private int month;
    private double totalRevenue;

    public RevenueStatistics(int month, double totalRevenue) {
        this.month = month;
        this.totalRevenue = totalRevenue;
    }

    public int getMonth() {
        return month;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}

