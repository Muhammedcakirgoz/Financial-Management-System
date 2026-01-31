package com.ProjeDeneme.Project.controller;

import com.ProjeDeneme.Project.dto.InvestmentDto;
import com.ProjeDeneme.Project.entity.Investment;
import com.ProjeDeneme.Project.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @GetMapping
    public ResponseEntity<List<Investment>> getAllInvestments(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        List<Investment> investments = investmentService.findAll(jwtToken);
        return ResponseEntity.ok(investments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Investment> getInvestmentById(@PathVariable String id, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        Investment investment = investmentService.findById(id, jwtToken);
        if (investment != null) {
            return ResponseEntity.ok(investment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Investment> createInvestment(@RequestBody InvestmentDto investmentDto, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        Investment createdInvestment = investmentService.save(investmentDto, jwtToken);
        return ResponseEntity.ok(createdInvestment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Investment> updateInvestment(@PathVariable String id, @RequestBody InvestmentDto investmentDto, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        Investment existingInvestment = investmentService.findById(id, jwtToken);
        if (existingInvestment != null) {
            investmentDto.setId(id);
            Investment updatedInvestment = investmentService.save(investmentDto, jwtToken);
            return ResponseEntity.ok(updatedInvestment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvestment(@PathVariable String id, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        Investment investment = investmentService.findById(id, jwtToken);
        if (investment != null) {
            investmentService.deleteById(id, jwtToken);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
@GetMapping("/monthly")
    public ResponseEntity<List<Investment>> getMonthlyInvestments(@RequestParam int year, @RequestParam Month month, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        return ResponseEntity.ok(investmentService.findMonthlyInvestments(year, month, jwtToken));
    }

    @GetMapping("/yearly")
    public ResponseEntity<List<Investment>> getYearlyInvestments(@RequestParam int year, @RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        return ResponseEntity.ok(investmentService.findYearlyInvestments(year, jwtToken));
    }
}
