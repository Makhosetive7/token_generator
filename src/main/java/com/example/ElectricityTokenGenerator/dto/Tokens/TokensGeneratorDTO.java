package com.example.ElectricityTokenGenerator.dto.Tokens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokensGeneratorDTO {
    private String accountNumber;
    private Double amount;
}