package com.example.ElectricityTokenGenerator.dto.Tokens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokensGenerationDTO {
    private Long accountNumber;
    private Double amountPaid;
    private String tokenGenerated;
    private Long batchNumber;
    private String serialNumber;
    private LocalDateTime timeStamp;
}
