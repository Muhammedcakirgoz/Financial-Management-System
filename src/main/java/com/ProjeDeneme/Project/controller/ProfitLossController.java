package com.ProjeDeneme.Project.controller;

import com.ProjeDeneme.Project.service.ProfitLossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/profit-loss")
public class ProfitLossController {

    @Autowired
    private ProfitLossService profitLossService;

    // Aylık kar/zarar
    @GetMapping("/monthly")
    public ResponseEntity<Double> getMonthlyProfitLoss(
            @RequestHeader("Authorization") String token,
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        double profitLoss = profitLossService.calculateMonthlyProfitLoss(token, startDate, endDate);
        return ResponseEntity.ok(profitLoss);
    }

    // Yıllık kar/zarar
    @GetMapping("/yearly")
    public ResponseEntity<Double> getYearlyProfitLoss(
            @RequestHeader("Authorization") String token,
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr) {

        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        double profitLoss = profitLossService.calculateYearlyProfitLoss(token, startDate, endDate);
        return ResponseEntity.ok(profitLoss);
    }
}
