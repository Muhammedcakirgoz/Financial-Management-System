package com.ProjeDeneme.Project.controller;

import com.ProjeDeneme.Project.dto.UserDto;
import com.ProjeDeneme.Project.dto.UserRequest;
import com.ProjeDeneme.Project.dto.UserResponse;
import com.ProjeDeneme.Project.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthenticationController, kullanıcı kimlik doğrulama ve kayıt işlemlerini yöneten REST API uç noktalarını sağlar.
 *
 * Bu sınıf, kullanıcıların giriş yapabilmesi ve yeni kullanıcıların kaydedilebilmesi için gerekli işlemleri sunar.
 */
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/admin-auth")
    public ResponseEntity<UserResponse> adminAuth(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(authenticationService.adminAuth(userRequest));
    }

    @PostMapping("/user-auth")
    public ResponseEntity<UserResponse> userAuth(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(authenticationService.userAuth(userRequest));
    }


    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authenticationService.save(userDto));
    }
}
