package com.example.ElectricityTokenGenerator.dto.Tokens;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class TokenDTO {
    private String tokenBuyer; 
    private String tokenCode; 
    private Double amount; 
    private Double kiloWatts; 
    private LocalDateTime purchaseDate;
    private LocalDateTime expirationDate; 
    private String status; 
}