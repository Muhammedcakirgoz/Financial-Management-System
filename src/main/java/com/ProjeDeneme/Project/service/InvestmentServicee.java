package com.ProjeDeneme.Project.service;

import com.ProjeDeneme.Project.dto.InvestmentDto;
import com.ProjeDeneme.Project.dto.RevenuDto;
import com.ProjeDeneme.Project.entity.Investment;
import com.ProjeDeneme.Project.entity.Revenue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public interface InvestmentServicee {
    List<Investment> findAll(String token);

    Investment findById(String id, String token);

    Investment save(InvestmentDto investmentDto, String token) ;

    void deleteById(String id, String token);

    // Aylık gelirleri hesaplamak için
    List<Investment> findMonthlyInvestments(int year, Month month, String token) ;

    // Yıllık gelirleri hesaplamak için
    List<Investment> findYearlyInvestments(int year, String token) ;

}
