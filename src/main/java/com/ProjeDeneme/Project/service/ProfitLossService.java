package com.ProjeDeneme.Project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProfitLossService {

    @Autowired
    private RevenueService revenueService;

    @Autowired
    private ExpenseService expenseService;

    // Aylık kar/zarar hesaplama
    public double calculateMonthlyProfitLoss(String token, LocalDate startDate, LocalDate endDate) {
        double totalRevenue = revenueService.calculateMonthlyRevenue(token, startDate, endDate);
        double totalExpense = expenseService.calculateMonthlyExpense(token, startDate, endDate);
        return totalRevenue - totalExpense; // Kar/zarar hesaplama
    }

    // Yıllık kar/zarar hesaplama
    public double calculateYearlyProfitLoss(String token, LocalDate startDate, LocalDate endDate) {
        double totalRevenue = revenueService.calculateYearlyRevenue(token, startDate, endDate);
        double totalExpense = expenseService.calculateYearlyExpense(token, startDate, endDate);
        return totalRevenue - totalExpense; // Kar/zarar hesaplama
    }
}
