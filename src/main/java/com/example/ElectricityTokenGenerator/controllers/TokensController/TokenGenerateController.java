package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokensGenerationDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokensEntity;
import com.example.ElectricityTokenGenerator.services.Tokens.TokenService;


@RestController
@RequestMapping("api/tokens")
public class TokenGenerateController {

    private final TokenService tokenServices;

    @Autowired
    public TokenGenerateController(TokenService tokenServices) {
        this.tokenServices = tokenServices;
    }


        // Create new tokens
    @PostMapping("/generateToken")
    public ResponseEntity<TokensEntity> createTokens(@RequestBody TokensGenerationDTO request) {
        TokensEntity newToken = tokenServices.createTokens(
                request.getAccountNumber(),
                request.getAmountPaid(),
                request.getSerialNumber(),
                LocalDateTime.now() 
        );
    
        return ResponseEntity.status(HttpStatus.CREATED).body(newToken);
    }
   
    









}
