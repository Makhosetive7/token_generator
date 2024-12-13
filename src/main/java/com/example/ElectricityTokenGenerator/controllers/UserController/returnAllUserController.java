package com.example.ElectricityTokenGenerator.controllers.UserController;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import com.example.ElectricityTokenGenerator.services.Users.returnAllUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;




@RestController
@Controller
@RequestMapping("/api/users")
public class returnAllUserController {

    public final returnAllUserService returnAllUserService;

    public returnAllUserController(returnAllUserService returnAllUserService) {
        this.returnAllUserService = returnAllUserService;
    }

    @GetMapping("/allUsers")
    public List<UserEntities> getAllUsers() {
        return returnAllUserService.getAllUsers();
    }
}
