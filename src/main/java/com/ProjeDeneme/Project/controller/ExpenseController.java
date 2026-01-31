package com.ProjeDeneme.Project.controller;

import com.ProjeDeneme.Project.dto.ExpenseDto;
import com.ProjeDeneme.Project.entity.Expense;
import com.ProjeDeneme.Project.entity.Investment;
import com.ProjeDeneme.Project.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        return ResponseEntity.ok(expenseService.findAll(jwtToken));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable String id, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        Expense expense = expenseService.findById(id, jwtToken);
        if (expense != null) {
            return ResponseEntity.ok(expense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody ExpenseDto expenseDto, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        Expense createdExpense = expenseService.save(expenseDto, jwtToken);
        return ResponseEntity.ok(createdExpense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable String id, @RequestBody ExpenseDto expenseDto, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        Expense existingExpense = expenseService.findById(id, jwtToken);
        if (existingExpense != null) {
            expenseDto.setId(id);
            Expense updatedExpense = expenseService.save(expenseDto, jwtToken);
            return ResponseEntity.ok(updatedExpense);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable String id, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        Expense expense = expenseService.findById(id, jwtToken);
        if (expense != null) {
            expenseService.deleteById(id, jwtToken);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/monthly")
    public ResponseEntity<Double> getMonthlyExpenses(@RequestParam int year, @RequestParam Month month, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth()); // Ayın son günü
        double monthlyExpenses = expenseService.calculateMonthlyExpense(jwtToken, startDate, endDate);
        return ResponseEntity.ok(monthlyExpenses);
    }

    @GetMapping("/yearly")
    public ResponseEntity<Double> getYearlyExpenses(@RequestParam int year, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        LocalDate startDate = LocalDate.of(year, 1, 1); // Yılın başı
        LocalDate endDate = LocalDate.of(year, 12, 31); // Yılın sonu
        double yearlyExpenses = expenseService.calculateYearlyExpense(jwtToken, startDate, endDate);
        return ResponseEntity.ok(yearlyExpenses);
    }
}
