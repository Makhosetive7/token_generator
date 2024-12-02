package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ElectricityTokenGenerator.services.TokenServices;
import com.example.ElectricityTokenGenerator.entity.TokensEntity;


@RestController
@RequestMapping("api/tokens/")
public class Tokens {

    private final TokenServices tokenServices;

    @Autowired
    public Tokens(TokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }

    // Retrieve all tokens available in the system
    @GetMapping("/")
    public List<TokensEntity> getAllTokens() {
        return tokenServices.getAllTokens();
    }
}