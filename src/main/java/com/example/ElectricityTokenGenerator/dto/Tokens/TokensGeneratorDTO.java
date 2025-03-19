package com.example.ElectricityTokenGenerator.dto.Tokens;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokensGeneratorDTO {
    private String accountNumber;
    private Double amount;
}