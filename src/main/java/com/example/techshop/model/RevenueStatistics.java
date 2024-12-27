package com.example.techshop.model;

public class RevenueStatistics {
    private int time;
    private double totalRevenue;

    public RevenueStatistics(int time, double totalRevenue) {
        this.time = time;
        this.totalRevenue = totalRevenue;
    }

    public int gettime() {
        return time;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}

