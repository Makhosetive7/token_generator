package com.example.ElectricityTokenGenerator.dto.Tokens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokensGenerationDTO {
    private UserEntities accountNumber;
    private Double amountPaid;
    private String tokenGenerated;
    private String serialNumber;
    private LocalDateTime createdAt;
    private LocalDateTime expiredAt;
    private UserEntities kiloWatts;
    private Boolean activatedToken = false;
}