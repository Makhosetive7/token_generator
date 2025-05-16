package com.example.ElectricityTokenGenerator.controllers.UserController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Users.AuthResponseDTO;
import com.example.ElectricityTokenGenerator.dto.Users.UserLoginDTO;
import com.example.ElectricityTokenGenerator.entity.Users.AuthResponse;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.services.Users.loginUserService;

import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class loginUserController {

    private final loginUserService loginUserService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> loginUser(@RequestBody UserLoginDTO request) {
        AuthResponseDTO authResponse = loginUserService.loginUser(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(authResponse);
    }
}