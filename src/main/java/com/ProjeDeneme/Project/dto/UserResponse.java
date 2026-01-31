package com.ProjeDeneme.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserResponse, kullanıcı kimlik doğrulama sonuçlarını taşıyan veri transfer nesnesidir (DTO).
 *
 * Bu sınıf, kullanıcı doğrulama işlemi sonucunda döndürülen JWT token'ı içerir.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    /**
     * Kullanıcıya verilen JWT token'ı temsil eder.
     */
    private String token;
}
