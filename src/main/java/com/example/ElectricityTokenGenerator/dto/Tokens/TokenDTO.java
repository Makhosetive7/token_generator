package com.example.ElectricityTokenGenerator.dto.Tokens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private String tokenBuyer; 
    private String tokenCode; 
    private Double amount; 
    private Double kiloWatts; 
    private LocalDateTime purchaseDate;
    private LocalDateTime expirationDate; 
    private String status; 
}