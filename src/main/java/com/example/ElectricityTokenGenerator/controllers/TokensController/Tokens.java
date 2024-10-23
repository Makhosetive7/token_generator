package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ElectricityTokenGenerator.services.TokenServices;
import com.example.ElectricityTokenGenerator.dto.Tokens.TokensGenerationDTO;
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

    // Get tokens by user id
    @GetMapping("/{id}")
    public ResponseEntity<TokensEntity> getTokensById(@PathVariable Long id) {
        Optional<TokensEntity> tokens = tokenServices.getTokensById(id);
        return tokens.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create new tokens
    @PostMapping("generateToken/")
    public ResponseEntity<TokensEntity> createTokens(@RequestBody TokensGenerationDTO request) {
        TokensEntity newToken = tokenServices.createTokens(
                request.getAccountNumber(),
                request.getAmountPaid(),
                request.getSerialNumber(),
                LocalDateTime.now() 

        );
    
        return ResponseEntity.status(HttpStatus.CREATED).body(newToken);
    }
    
    

    // Delete created tokens
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTokens(@PathVariable Long id) {
        tokenServices.deleteTokens(id);
        return ResponseEntity.noContent().build();
    }
}
