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
    private String serialNumber;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private Long kiloWatts;
}
