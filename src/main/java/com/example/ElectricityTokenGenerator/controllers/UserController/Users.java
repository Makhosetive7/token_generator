package com.example.ElectricityTokenGenerator.controllers.UserController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Users.userRegistrationDTO;
import com.example.ElectricityTokenGenerator.entity.UsersEntity;
import com.example.ElectricityTokenGenerator.services.UserServices;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequestMapping("/api/users")
public class Users {

    private final UserServices userServices;

    @Autowired
    private Users(UserServices userServices) {
        this.userServices = userServices;
    }

    // get all users
    @GetMapping("/")
    public List<UsersEntity> getAllUsers() {
        return userServices.getAllUsers();
    }

    // return user by id
    @GetMapping("/{id}")
    public ResponseEntity<UsersEntity> getUserById(@PathVariable Long id) {
        Optional<UsersEntity> user = userServices.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // user registration
    @PostMapping("/register")
    public ResponseEntity<UsersEntity> createUser(@RequestBody userRegistrationDTO request) {
        UsersEntity newUser = userServices.createUser(
                request.getUserName(), 
                request.getLastName(), 
                request.getPhoneNumber(), 
                request.getHomeAddress()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

// retrieve user by account number
    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<UsersEntity> getUserByAccountNumber(@PathVariable String accountNumber) {
         Optional<UsersEntity> user = userServices.getUserByAccountNumber(accountNumber); 
         return user.map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)); }


    // delete available user by id
    @DeleteMapping("account/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userServices.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}