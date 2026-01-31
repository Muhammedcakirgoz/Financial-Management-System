package com.ProjeDeneme.Project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserRequest, kullanıcı kimlik doğrulama talepleri için kullanılan veri transfer nesnesidir (DTO).
 *
 * Bu sınıf, kullanıcının kimlik doğrulama işlemleri sırasında gönderilen kullanıcı adı ve şifreyi içerir.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    /**
     * Kullanıcının şifresini temsil eder.
     */
    private String password;

    /**
     * Kullanıcının kullanıcı adını temsil eder.
     */
    private String username;
}
