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
    public ResponseEntity<?> tokenTransfer(@RequestBody TokenTransferDTO tokenTransferDTO) {
        try {
            // Ensure required fields are present
            if (tokenTransferDTO.getKiloWatts() == null) {
                return ResponseEntity.badRequest().body("Kilowatts value is required.");
            }

            TokenTransferDTO result = tokenTransferService.transferTokens(
                    tokenTransferDTO.getSenderAccountNumber(),
                    tokenTransferDTO.getReceiverAccountNumber(),
                    tokenTransferDTO.getKiloWatts(),
                    tokenTransferDTO.getCreatedAt());
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("An error occurred: " + e.getMessage());
        }
    }
}