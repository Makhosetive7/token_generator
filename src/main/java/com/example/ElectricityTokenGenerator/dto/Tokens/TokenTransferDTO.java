package com.example.ElectricityTokenGenerator.dto.Tokens;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenTransferDTO {
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private Double kiloWatts;
    private LocalDateTime createdAt;
}
