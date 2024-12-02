package com.example.ElectricityTokenGenerator.controllers.TokensController;

import org.springframework.web.bind.annotation.RestController;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenTransferDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenTransferEntity;
import com.example.ElectricityTokenGenerator.services.Tokens.TokenTransferService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/tokens/")
public class TokenTransferController {

    private final TokenTransferService tokenTransferService;


    public TokenTransferController(TokenTransferService tokenTransferService) {
        this.tokenTransferService = tokenTransferService;
    }
    
        @PostMapping("/tokenTransfer")
    public ResponseEntity<TokenTransferEntity> transferToken(@RequestBody TokenTransferDTO request) {
        TokenTransferEntity transferredTokens = tokenTransferService.transferTokens(
            request.getSenderAccountNumber(),
            request.getReceiverAccountNumber(),
            request.getKilowatts(),
            request.getTransferTokenId(),
            request.getCreatedAt()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(transferredTokens);
    }
}
