package com.example.ElectricityTokenGenerator.controllers.TokensController;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenTransferDTO;
import com.example.ElectricityTokenGenerator.services.Tokens.TokenTransferService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/tokens/")
public class TokenTransferController {

    private final TokenTransferService tokenTransferService;

    public TokenTransferController(TokenTransferService tokenTransferService) {
        this.tokenTransferService = tokenTransferService;
    }

    /**
     * Endpoint to transfer tokens between users.
     *
     * @param senderAccountNumber   The account number of the sender.
     * @param receiverAccountNumber The account number of the receiver.
     * @param kilowatts             The amount of kilowatts to transfer.
     * @param createdAt             The date and time of the transfer.
     * @return The created token transfer as a TokenTransferDTO.
     */
    @PostMapping("/tokenTransfer")
    public ResponseEntity<TokenTransferDTO> tokenTransfer(
            @RequestParam String senderAccountNumber,
            @RequestParam String receiverAccountNumber,
            @RequestParam Double kilowatts,
            @RequestParam LocalDateTime createdAt) {

        try {
            // Call the service to transfer tokens
            TokenTransferDTO tokenTransferDTO = tokenTransferService.transferTokens(
                    senderAccountNumber, receiverAccountNumber, kilowatts, createdAt);

            // Return the created token transfer with a 201 CREATED status
            return ResponseEntity.status(HttpStatus.CREATED).body(tokenTransferDTO);
        } catch (RuntimeException e) {
            // Handle exceptions (e.g., user not found, insufficient kilowatts)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}