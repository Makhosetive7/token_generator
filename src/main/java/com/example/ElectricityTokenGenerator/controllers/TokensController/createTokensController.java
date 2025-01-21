package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokensGenerationDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;
import com.example.ElectricityTokenGenerator.services.Tokens.createTokenService;


@Service
@RestController
@RequestMapping("api/tokens")
public class createTokensController {

    public final createTokenService createTokenService;

   public createTokensController(createTokenService createTokenService) {
    this.createTokenService = createTokenService;
   }


        // Create new tokens
    @PostMapping("/generateToken")
    public ResponseEntity<TokenEntities> createTokens(@RequestBody TokensGenerationDTO request) {
        TokenEntities newToken = createTokenService.createTokens(
                request.getAccountNumber(),
                request.getAmountPaid(),
                request.getSerialNumber(),
                LocalDateTime.now() 
        );
    
        return ResponseEntity.status(HttpStatus.CREATED).body(newToken);
    }
   
    









}
