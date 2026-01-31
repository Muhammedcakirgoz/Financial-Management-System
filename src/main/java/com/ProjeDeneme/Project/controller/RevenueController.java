package com.ProjeDeneme.Project.controller;

import com.ProjeDeneme.Project.dto.RevenuDto;
import com.ProjeDeneme.Project.entity.Revenue;
import com.ProjeDeneme.Project.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/revenue")
public class RevenueController {

    @Autowired
    private RevenueService revenueService;

    @GetMapping
    public ResponseEntity<List<Revenue>> getAllRevenues(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(revenueService.findAll(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Revenue> getRevenueById(@PathVariable String id, @RequestHeader("Authorization") String token) {
        Revenue revenue = revenueService.findById(id, token);
        if (revenue != null) {
            return ResponseEntity.ok(revenue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Revenue> createRevenue(@RequestBody RevenuDto revenueDto, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        Revenue createdRevenue = revenueService.save(revenueDto, jwtToken);
        return ResponseEntity.ok(createdRevenue);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Revenue> updateRevenue(@PathVariable String id, @RequestBody RevenuDto revenueDto, @RequestHeader("Authorization") String token) {
        Revenue existingRevenue = revenueService.findById(id, token);
        if (existingRevenue != null) {
            // Mevcut gelir bilgilerini güncelle
            revenueDto.setId(id);
            // Kullanıcıyı token'dan al
            Revenue updatedRevenue = revenueService.save(revenueDto, token);
            return ResponseEntity.ok(updatedRevenue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRevenue(@PathVariable String id, @RequestHeader("Authorization") String token) {
        Revenue revenue = revenueService.findById(id, token);
        if (revenue != null) {
            revenueService.deleteById(id, token);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/monthly")
    public ResponseEntity<Double> getMonthlyRevenues(@RequestParam int year, @RequestParam Month month, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth()); // Ayın son günü
        double monthlyRevenues = revenueService.calculateMonthlyRevenue(jwtToken, startDate, endDate);
        return ResponseEntity.ok(monthlyRevenues);
    }

    @GetMapping("/yearly")
    public ResponseEntity<Double> getYearlyRevenues(@RequestParam int year, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        LocalDate startDate = LocalDate.of(year, 1, 1); // Yılın başı
        LocalDate endDate = LocalDate.of(year, 12, 31); // Yılın sonu
        double yearlyRevenues = revenueService.calculateYearlyRevenue(jwtToken, startDate, endDate);
        return ResponseEntity.ok(yearlyRevenues);
    }
}
