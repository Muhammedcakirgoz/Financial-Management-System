package com.ProjeDeneme.Project.dto;

import com.ProjeDeneme.Project.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserDto, kullanıcı bilgilerini taşıyan veri transfer nesnesidir (DTO).
 *
 * Bu sınıf, kullanıcı kayıt ve güncelleme işlemlerinde kullanılacak temel bilgileri içerir.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    /**
     * Kullanıcının ad ve soyadını temsil eder.
     */
    private String nameSurname;

    /**
     * Kullanıcının kullanıcı adını temsil eder.
     */
    private String username;

    /**
     * Kullanıcının şifresini temsil eder.
     */
    private String password;

    /**
     * Kullanıcının rolünü temsil eder. (Örneğin: ADMIN, USER)
     */
    private Role role;
}
