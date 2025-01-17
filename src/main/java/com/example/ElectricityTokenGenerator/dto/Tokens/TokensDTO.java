package com.example.ElectricityTokenGenerator.dto.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokensDTO {
    private UserEntities accountNumber;
    private Double amountPaid;
    private String tokenGenerated;
    private Long serialNumber;
    private LocalDateTime createdAt;
}