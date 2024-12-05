package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.ElectricityTokenGenerator.entity.Tokens.TokensEntity;
import com.example.ElectricityTokenGenerator.services.Tokens.TokenService;


@RestController
@RequestMapping("api/tokens/")
public class Tokens {

    private final TokenService tokenServices;

    @Autowired
    public Tokens(TokenService tokenServices) {
        this.tokenServices = tokenServices;
    }

    // Retrieve all tokens available in the system
    @GetMapping("/")
    public List<TokensEntity> getAllTokens() {
        return tokenServices.getAllTokens();
    }
}