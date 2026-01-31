package com.ProjeDeneme.Project.config;

import com.ProjeDeneme.Project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * ApplicationConfig sınıfı, Spring Security yapılandırmasını içerir.
 *
 * Bu sınıf, kullanıcı kimlik doğrulaması ve yetkilendirmesi için gerekli olan bileşenleri tanımlar ve yapılandırır.
 * - `UserDetailsService`: Kullanıcı bilgilerini almak için kullanılan servis.
 * - `AuthenticationProvider`: Kullanıcı doğrulaması için kullanılan sağlayıcı.
 * - `PasswordEncoder`: Şifreleri güvenli bir şekilde şifrelemek için kullanılan encoder.
 * - `AuthenticationManager`: Kimlik doğrulama işlemlerini yöneten bileşen.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final UserRepository userRepository;

    /**
     * Kullanıcı detaylarını yüklemek için `UserDetailsService` bean'ini tanımlar.
     * Kullanıcı adı ile kullanıcıyı bulur ve bir `UsernameNotFoundException` ile hata durumunu yönetir.
     *
     * @return UserDetailsService bean
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    /**
     * Kimlik doğrulama sağlayıcısını tanımlar.
     * `UserDetailsService` ve `PasswordEncoder`'ı kullanarak kullanıcı doğrulamasını gerçekleştirir.
     *
     * @return AuthenticationProvider bean
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    /**
     * Şifreleri güvenli bir şekilde şifrelemek için `PasswordEncoder` bean'ini tanımlar.
     * BCrypt algoritmasını kullanarak şifreleri şifreler.
     *
     * @return PasswordEncoder bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Kimlik doğrulama işlemlerini yöneten `AuthenticationManager` bean'ini tanımlar.
     * `AuthenticationConfiguration` üzerinden yapılandırılır.
     *
     * @param authenticationConfiguration AuthenticationConfiguration
     * @return AuthenticationManager bean
     * @throws Exception If authentication manager creation fails
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
