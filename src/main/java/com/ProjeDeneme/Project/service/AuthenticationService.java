package com.ProjeDeneme.Project.service;

import com.ProjeDeneme.Project.dto.UserDto;
import com.ProjeDeneme.Project.dto.UserRequest;
import com.ProjeDeneme.Project.dto.UserResponse;
import com.ProjeDeneme.Project.entity.User;
import com.ProjeDeneme.Project.enums.Role;
import com.ProjeDeneme.Project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * AuthenticationService, kullanıcı kimlik doğrulama ve yetkilendirme işlemlerini yönetir.
 * Kullanıcıların kaydedilmesi, giriş yapması ve token oluşturulması gibi işlemleri içerir.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    /**
     * Yeni bir kullanıcı kaydeder ve JWT token oluşturur.
     *
     * @param userDto Kullanıcı bilgilerini içeren DTO
     * @return Oluşturulan JWT token'ı içeren UserResponse
     */
    public UserResponse save(UserDto userDto) {
        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .nameSurname(userDto.getNameSurname())
                .role(userDto.getRole() != null ? userDto.getRole() : Role.USER) // Rol bilgisini kullanın
                .build();
        userRepository.save(user);
        var token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }

    /**
     * Yönetici (admin) kimlik doğrulaması yapar ve JWT token oluşturur.
     *
     * @param userRequest Kullanıcı giriş bilgilerini içeren DTO
     * @return Oluşturulan JWT token'ı içeren UserResponse
     */
    public UserResponse adminAuth(UserRequest userRequest) {
        return authenticateUser(userRequest, Role.ADMIN);
    }

    /**
     * Kullanıcı kimlik doğrulaması yapar ve JWT token oluşturur.
     *
     * @param userRequest Kullanıcı giriş bilgilerini içeren DTO
     * @return Oluşturulan JWT token'ı içeren UserResponse
     */
    public UserResponse userAuth(UserRequest userRequest) {
        return authenticateUser(userRequest, Role.USER);
    }

    /**
     * Kullanıcı kimlik doğrulamasını yapar ve rol kontrolü ile JWT token oluşturur.
     *
     * @param userRequest Kullanıcı giriş bilgilerini içeren DTO
     * @param role Kullanıcının rolü
     * @return Oluşturulan JWT token'ı içeren UserResponse
     */
    private UserResponse authenticateUser(UserRequest userRequest, Role role) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUsername(), userRequest.getPassword()));
        User user = userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
        if (user.getRole() != role) {
            throw new RuntimeException("Unauthorized");
        }
        String token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }
}
