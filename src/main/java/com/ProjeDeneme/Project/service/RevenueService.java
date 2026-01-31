package com.ProjeDeneme.Project.service;

import com.ProjeDeneme.Project.dto.RevenuDto;
import com.ProjeDeneme.Project.entity.Revenue;
import com.ProjeDeneme.Project.entity.User;
import com.ProjeDeneme.Project.repository.RevenueRepository;
import com.ProjeDeneme.Project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Revenue işlemlerini gerçekleştiren servis sınıfı.
 * Bu sınıf, gelirlerin yönetimi ile ilgili işlemleri uygulamak için arayüzü kullanır.
 */
@Service
public class RevenueService implements ReevenueService {

    @Autowired
    private RevenueRepository revenueRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    /**
     * Kullanıcının tüm gelirlerini alır.
     *
     * @param token Kullanıcının JWT token'ı
     * @return Kullanıcıya ait tüm gelirlerin listesi
     */
    @Override
    public List<Revenue> findAll(String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return revenueRepository.findByUser(user);
    }

    /**
     * Kullanıcının belirli bir gelir kaydını ID ile alır.
     *
     * @param id Gelir kaydının ID'si
     * @param token Kullanıcının JWT token'ı
     * @return Belirli bir gelir kaydı
     */
    @Override
    public Revenue findById(String id, String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return revenueRepository.findByIdAndUser(id, user).orElse(null);
    }

    /**
     * Kullanıcının yeni bir gelir kaydı oluşturur.
     *
     * @param revenueDto Gelir bilgilerini içeren DTO
     * @param token Kullanıcının JWT token'ı
     * @return Kaydedilen gelir
     */
    @Override
    public Revenue save(RevenuDto revenueDto, String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Revenue revenue = convertToEntity(revenueDto);
        revenue.setUser(user);
        return revenueRepository.save(revenue);
    }

    /**
     * DTO'yu gelir entity'sine dönüştürür.
     *
     * @param revenueDto Gelir bilgilerini içeren DTO
     * @return Gelir entity'si
     */
    private Revenue convertToEntity(RevenuDto revenueDto) {
        Revenue revenue = new Revenue();
        revenue.setDescription(revenueDto.getDescription());
        revenue.setAmount(revenueDto.getAmount());
        revenue.setDate(revenueDto.getDate());
        return revenue;
    }

    /**
     * Belirli bir gelir kaydını ID ile siler.
     *
     * @param id Gelir kaydının ID'si
     * @param token Kullanıcının JWT token'ı
     */
    @Override
    public void deleteById(String id, String token) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Revenue revenue = revenueRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RuntimeException("Revenue not found"));
        revenueRepository.delete(revenue);
    }

    /**
     * Belirli bir tarih aralığındaki aylık gelirleri hesaplar.
     *
     * @param token Kullanıcının JWT token'ı
     * @param startDate Başlangıç tarihi
     * @param endDate Bitiş tarihi
     * @return Aylık gelirlerin toplamı
     */
    @Override
    public double calculateMonthlyRevenue(String token, LocalDate startDate, LocalDate endDate) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Revenue> revenues = revenueRepository.findByUserAndDateBetween(user, startDate, endDate);
        return revenues.stream().mapToDouble(Revenue::getAmount).sum();
    }

    /**
     * Belirli bir tarih aralığındaki yıllık gelirleri hesaplar.
     *
     * @param token Kullanıcının JWT token'ı
     * @param startDate Başlangıç tarihi
     * @param endDate Bitiş tarihi
     * @return Yıllık gelirlerin toplamı
     */
    @Override
    public double calculateYearlyRevenue(String token, LocalDate startDate, LocalDate endDate) {
        String username = jwtService.findUsername(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<Revenue> revenues = revenueRepository.findByUserAndDateBetween(user, startDate, endDate);
        return revenues.stream().mapToDouble(Revenue::getAmount).sum();
    }
}
