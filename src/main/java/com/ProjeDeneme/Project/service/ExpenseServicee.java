package com.ProjeDeneme.Project.service;

import com.ProjeDeneme.Project.dto.ExpenseDto;
import com.ProjeDeneme.Project.dto.RevenuDto;
import com.ProjeDeneme.Project.entity.Expense;
import com.ProjeDeneme.Project.entity.Revenue;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseServicee {
    List<Expense> findAll(String token);

    Expense findById(String id, String token);

    Expense save(ExpenseDto expenseDto, String token) ;

    void deleteById(String id, String token);

    // Aylık gelirleri hesaplamak için
    double calculateMonthlyExpense(String token, LocalDate startDate, LocalDate endDate) ;

    // Yıllık gelirleri hesaplamak için
    double calculateYearlyExpense(String token, LocalDate startDate, LocalDate endDate) ;

}
