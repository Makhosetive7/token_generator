package com.example.ElectricityTokenGenerator.controllers.TokensController;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.ElectricityTokenGenerator.dto.Tokens.TokensGenerationDTO;
import com.example.ElectricityTokenGenerator.services.Tokens.createTokenService;

@RestController
@RequestMapping("api/tokens")
public class createTokensController {

    private final createTokenService createTokenService;

    public createTokensController(createTokenService createTokenService) {
        this.createTokenService = createTokenService;
    }

    @PostMapping("/generateToken")
    public ResponseEntity<TokensGenerationDTO> generateToken(@RequestBody TokensGenerationDTO request) {
        TokensGenerationDTO newToken = createTokenService.createTokens(
                request.getAccountNumber(),
                request.getAmountPaid(),
                request.getSerialNumber(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(newToken);
    }
}