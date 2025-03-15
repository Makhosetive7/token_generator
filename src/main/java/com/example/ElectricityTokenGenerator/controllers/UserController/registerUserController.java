package com.example.ElectricityTokenGenerator.controllers.UserController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Users.UserRegistrationDTO;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.services.Users.registerUserService;

@RestController
@Controller
@RequestMapping("/api/users")
public class registerUserController {
    
    public final registerUserService registerUserService;

    public registerUserController(registerUserService registerUserService) {
        this.registerUserService = registerUserService;
    }

     // user registration
    @PostMapping("/register")
    public ResponseEntity<User> createUser(@RequestBody UserRegistrationDTO request) {
        User newUser = registerUserService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}