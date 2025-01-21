package com.example.ElectricityTokenGenerator.dto.Tokens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenTransferDTO {
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private Double kilowatts;
    private Long TransferTokenId;
    private LocalDateTime createdAt;
}
