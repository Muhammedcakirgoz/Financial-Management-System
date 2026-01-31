package com.ProjeDeneme.Project.service;

import com.ProjeDeneme.Project.dto.InvestmentDto;
import com.ProjeDeneme.Project.dto.RevenuDto;
import com.ProjeDeneme.Project.entity.Investment;
import com.ProjeDeneme.Project.entity.Revenue;
import com.ProjeDeneme.Project.entity.User;
import com.ProjeDeneme.Project.repository.InvestmentRepository;
import com.ProjeDeneme.Project.repository.UserRepository;
import com.ProjeDeneme.Project.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Service
public class InvestmentService implements InvestmentServicee {

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Override
    public List<Investment> findAll(String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return investmentRepository.findByUser(user);
    }
    @Override
    public Investment findById(String id, String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return investmentRepository.findByIdAndUser(id, user).orElse(null);
    }

    public Investment save(InvestmentDto investmentDto, String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Investment investment =convertToEntity(investmentDto);
        investment.setUser(user);
        return investmentRepository.save(investment);
    }

    private Investment convertToEntity(InvestmentDto investmentDto) {
        Investment investment = new Investment();
        investment.setDescription(investmentDto.getDescription());
        investment.setAmount(investmentDto.getAmount());
        investment.setDate(investmentDto.getDate());
        return investment;
    }
    @Override
    public void deleteById(String id, String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Investment investment = investmentRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Investment not found"));
        investmentRepository.delete(investment);
    }

    public List<Investment> findMonthlyInvestments(int year, Month month, String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
        return investmentRepository.findByUserAndDateBetween(user, startDate, endDate);
    }

    public List<Investment> findYearlyInvestments(int year, String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        LocalDate startDate = LocalDate.of(year, 1, 1);
        LocalDate endDate = LocalDate.of(year, 12, 31);
        return investmentRepository.findByUserAndDateBetween(user, startDate, endDate);
    }
}
