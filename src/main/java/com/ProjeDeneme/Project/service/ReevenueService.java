package com.ProjeDeneme.Project.service;

import com.ProjeDeneme.Project.dto.RevenuDto;
import com.ProjeDeneme.Project.entity.Revenue;
import com.ProjeDeneme.Project.entity.User;

import java.time.LocalDate;
import java.util.List;

/**
 * Revenue işlemleri için servis arayüzü.
 * Bu arayüz, gelirlerin yönetimi ile ilgili temel operasyonları tanımlar.
 */
public interface ReevenueService {

    /**
     * Tüm gelirleri alır.
     *
     * @param token Kullanıcının JWT token'ı
     * @return Kullanıcıya ait tüm gelirlerin listesi
     */
    List<Revenue> findAll(String token);

    /**
     * Belirli bir gelir kaydını ID ile alır.
     *
     * @param id Gelir kaydının ID'si
     * @param token Kullanıcının JWT token'ı
     * @return Belirli bir gelir kaydı
     */
    Revenue findById(String id, String token);

    /**
     * Yeni bir gelir kaydeder.
     *
     * @param revenuedto Gelir bilgilerini içeren DTO
     * @param token Kullanıcının JWT token'ı
     * @return Kaydedilen gelir
     */
    Revenue save(RevenuDto revenuedto, String token);

    /**
     * Belirli bir gelir kaydını ID ile siler.
     *
     * @param id Gelir kaydının ID'si
     * @param token Kullanıcının JWT token'ı
     */
    void deleteById(String id, String token);

    /**
     * Belirli bir tarih aralığındaki aylık gelirleri hesaplar.
     *
     * @param token Kullanıcının JWT token'ı
     * @param startDate Başlangıç tarihi
     * @param endDate Bitiş tarihi
     * @return Aylık gelirlerin toplamı
     */
    double calculateMonthlyRevenue(String token, LocalDate startDate, LocalDate endDate);

    /**
     * Belirli bir tarih aralığındaki yıllık gelirleri hesaplar.
     *
     * @param token Kullanıcının JWT token'ı
     * @param startDate Başlangıç tarihi
     * @param endDate Bitiş tarihi
     * @return Yıllık gelirlerin toplamı
     */
    double calculateYearlyRevenue(String token, LocalDate startDate, LocalDate endDate);
}
