package com.ProjeDeneme.Project.dto;

import com.ProjeDeneme.Project.entity.User;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDate;

public class ExpenseDto {
    private String id;
    private String description;
    private double amount;
    private LocalDate date;



    @DBRef
    private User user; // Kullanıcı ile ilişki

    // Getters and Setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
