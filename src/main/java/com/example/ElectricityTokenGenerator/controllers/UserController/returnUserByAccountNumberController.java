package com.example.ElectricityTokenGenerator.controllers.UserController;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

@RestController
@Controller
@RequestMapping("/api/users")
public class returnUserByAccountNumberController {

    public final userRepository userRepository;

    public returnUserByAccountNumberController(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    // retrieve user by account number
    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<UserEntities> getUserByAccountNumber(@PathVariable String accountNumber) {
         Optional<UserEntities> user = userRepository.findByAccountNumber(accountNumber); 
         return user.map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); }


}
