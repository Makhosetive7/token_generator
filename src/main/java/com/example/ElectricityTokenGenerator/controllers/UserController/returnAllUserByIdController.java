package com.example.ElectricityTokenGenerator.controllers.UserController;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.entity.UsersEntity;
import com.example.ElectricityTokenGenerator.services.Users.returnUserByIdService;


@RestController
@Controller
@RequestMapping("/api/users")
public class returnAllUserByIdController {
    
    public final returnUserByIdService  returnUserByIdService;

    public returnAllUserByIdController(returnUserByIdService returnUserByIdService) {
        this.returnUserByIdService = returnUserByIdService;
    }

        // return user by id
    @GetMapping("/{id}")
    public ResponseEntity<UsersEntity> getUserById(@PathVariable Long id) {
        Optional<UsersEntity> user = returnUserByIdService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
