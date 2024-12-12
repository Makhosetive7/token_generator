package com.example.ElectricityTokenGenerator.controllers.UserController;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.services.Users.deleteUserByIdService;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@Controller
@RequestMapping("/api/users")
public class deleteUserByIdController {
    
    public final deleteUserByIdService deleteUserByIdService;

    public deleteUserByIdController(deleteUserByIdService deleteUserByIdService) {
        this.deleteUserByIdService = deleteUserByIdService;
    }

        // delete available user by id
    @DeleteMapping("account/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        deleteUserByIdService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
