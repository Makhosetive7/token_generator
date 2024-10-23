package com.example.ElectricityTokenGenerator.dto.Tokens;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokensDTO {
    private Long accountNumber;
    private Double amountPaid;
    private String tokenGenerated;
    private Long serialNumber;
    private LocalDateTime createdAt;
}
