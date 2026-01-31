package com.ProjeDeneme.Project.dto;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalTime;

public class RevenuDto {
    private String id;
    private String description;
    private double amount;
    private LocalDate date;

    private String user; // Kullanıcı bilgisi için DTO

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String userId;
    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
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
