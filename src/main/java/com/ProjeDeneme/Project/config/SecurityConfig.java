package com.ProjeDeneme.Project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // CSRF korumasını devre dışı bırak
                .csrf(csrf -> csrf.disable())

                // HTTP isteklerinin yetkilendirilmesi
                .authorizeHttpRequests(authz -> authz
                        // (permitall()) yolundaki istekler herkese açıktır
                        .requestMatchers("/login/**").permitAll()
                        .requestMatchers("/admin/**").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/revenue/**").permitAll()
                        .requestMatchers("/expense/**").permitAll()
                        .requestMatchers("/investment/**").permitAll()
                        .anyRequest().authenticated() // Diğer tüm istekler kimlik doğrulaması gerektirir
                )

                // Oturum yönetimini yapılandır
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless oturum yönetimi kullanılır
                )

                // JWT filtresini UsernamePasswordAuthenticationFilter'dan önce ekle
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
