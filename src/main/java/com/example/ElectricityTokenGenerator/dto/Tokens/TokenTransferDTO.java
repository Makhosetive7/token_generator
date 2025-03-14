package com.example.ElectricityTokenGenerator.dto.Tokens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenTransferDTO {
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private Double kiloWatts;
}
