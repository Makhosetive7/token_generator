package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.entity.Tokens.TokensEntity;
import com.example.ElectricityTokenGenerator.services.Tokens.TokenService;

@RestController
@RequestMapping("api/tokens")
public class TokensByIdController {

    private final TokenService tokenServices;

    public TokensByIdController(TokenService tokenServices) {
        this.tokenServices = tokenServices;
    }


       // Get tokens by user id
    @GetMapping("/{id}")
    public ResponseEntity<TokensEntity> getTokensById(@PathVariable Long id) {
        Optional<TokensEntity> tokens = tokenServices.getTokensById(id);
        return tokens.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    
}
