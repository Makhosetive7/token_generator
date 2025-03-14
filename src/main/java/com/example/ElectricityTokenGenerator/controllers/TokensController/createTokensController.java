package com.example.ElectricityTokenGenerator.controllers.TokensController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenDTO;
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
     public ResponseEntity<TokenDTO> createToken(
            @RequestParam String tokenBuyer,
            @RequestParam Double amountPaid) {
        try {
            TokenDTO tokenDTO = createTokenService.createTokens(tokenBuyer, amountPaid);
            return ResponseEntity.status(HttpStatus.CREATED).body(tokenDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
   
    









}
