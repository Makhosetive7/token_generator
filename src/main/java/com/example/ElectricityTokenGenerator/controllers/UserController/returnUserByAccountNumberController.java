package com.example.ElectricityTokenGenerator.controllers.UserController;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.services.Users.returnUserByAccountNumberService;


@RestController
@Controller
@RequestMapping("/api/users")
public class returnUserByAccountNumberController {

    public final returnUserByAccountNumberService returnUserByAccountNumberService;

    public returnUserByAccountNumberController(returnUserByAccountNumberService returnUserByAccountNumberService) {
        this.returnUserByAccountNumberService = returnUserByAccountNumberService;
    }

    // retrieve user by account number
    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<User> getUserByAccountNumber(@PathVariable String accountNumber) {
         Optional<User> user = returnUserByAccountNumberService.getUserByAccountNumber(accountNumber); 
         return user.map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); }


}
