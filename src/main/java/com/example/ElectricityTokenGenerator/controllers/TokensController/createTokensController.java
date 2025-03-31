package com.example.ElectricityTokenGenerator.controllers.TokensController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokensGeneratorDTO;
import com.example.ElectricityTokenGenerator.services.Tokens.createTokenService;


@Service
@RestController
@RequestMapping("api/tokens")
public class createTokensController {

    public final createTokenService createTokenService;

   public createTokensController(createTokenService createTokenService) {
    this.createTokenService = createTokenService;
   }


        // Create new token
    @PostMapping("/generateToken")
    public ResponseEntity<TokensGeneratorDTO> createToken(
            @RequestBody TokensGeneratorDTO request) {
        try {
            TokensGeneratorDTO tokenGeneratorDTO = createTokenService.createTokens(
                request.getAccountNumber(), 
                request.getAmount()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(tokenGeneratorDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
