package com.example.ElectricityTokenGenerator.dto.Tokens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenTransferDTO {
    private Long sendingAccountNumber;
    private Long receivingAccountNumber;
    private Long amountTransferred;
    private Long kiloWatts;
}
