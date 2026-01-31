package com.ProjeDeneme.Project.service;

import com.ProjeDeneme.Project.dto.ExpenseDto;
import com.ProjeDeneme.Project.dto.RevenuDto;
import com.ProjeDeneme.Project.entity.Expense;
import com.ProjeDeneme.Project.entity.Revenue;
import com.ProjeDeneme.Project.entity.User;
import com.ProjeDeneme.Project.repository.ExpenseRepository;
import com.ProjeDeneme.Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService implements ExpenseServicee {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public List<Expense> findAll(String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return expenseRepository.findByUser(user);
    }

    public Expense findById(String id, String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return expenseRepository.findByIdAndUser(id, user).orElse(null);
    }

    public Expense save(ExpenseDto expenseDto, String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Expense expense = convertToEntity(expenseDto);
        expenseDto.setUser(user);
        return expenseRepository.save(expense);
    }
    private Expense convertToEntity(ExpenseDto expenseDto) {
        Expense expense = new Expense();
        expense.setDescription(expenseDto.getDescription());
        expense.setAmount(expenseDto.getAmount());
        expense.setDate(expenseDto.getDate());
        return expense;
    }

    public void deleteById(String id, String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Expense expense = expenseRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Expense not found"));
        expenseRepository.delete(expense);
    }

    // Aylık giderleri hesaplamak için
    public double calculateMonthlyExpense(String token, LocalDate startDate, LocalDate endDate) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Expense> expenses = expenseRepository.findByUserAndDateBetween(user, startDate, endDate);
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }

    // Yıllık giderleri hesaplamak için
    public double calculateYearlyExpense(String token, LocalDate startDate, LocalDate endDate) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Expense> expenses = expenseRepository.findByUserAndDateBetween(user, startDate, endDate);
        return expenses.stream().mapToDouble(Expense::getAmount).sum();
    }
}
