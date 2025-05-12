package com.example.ElectricityTokenGenerator.controllers.UserController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.services.Users.logoutUserService;

import jakarta.annotation.security.PermitAll;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RestController
@PermitAll
@RequestMapping("/api/users")
public class LogoutUserController {

    public final logoutUserService logoutUserService;

    public LogoutUserController(logoutUserService logoutUserService) {
        this.logoutUserService = logoutUserService;
    }

    // user logout
    @PostMapping("/logout")
    public ResponseEntity<String> logoutUser(HttpServletRequest request) {
        String response = logoutUserService.logoutUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
