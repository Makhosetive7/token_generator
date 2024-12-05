package com.example.ElectricityTokenGenerator.controllers.TokensController;

import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.services.Tokens.TokenService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/tokens")
public class TokensDeleteController {

    private final TokenService tokenServices;

    public TokensDeleteController(TokenService tokenServices) {
        this.tokenServices = tokenServices;
    }
        // Delete created tokens
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTokens(@PathVariable Long id) {
        tokenServices.deleteTokens(id);
        return ResponseEntity.noContent().build();
    }



}
