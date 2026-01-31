package com.ProjeDeneme.Project.config;

import com.ProjeDeneme.Project.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");
        final String jwt;
        final String username;
        // Authorization başlığı yoksa veya "Bearer " ile başlamıyorsa devam et
        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Token'ı başlıktan ayır
        jwt = header.substring(7);
        // Token'dan kullanıcı adını al
        username = jwtService.findUsername(jwt);
        // Kullanıcı adı varsa ve SecurityContext'te zaten kimlik doğrulaması yapılmamışsa
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            // Kullanıcı detaylarını yükle
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            // Token doğruluğunu kontrol et
            if (jwtService.tokenControl(jwt, userDetails)) {
                // Kimlik doğrulama token'ını oluştur
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Güvenlik bağlamını güncelle
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        // Filtre zincirine devam et
        filterChain.doFilter(request, response);
    }
}
