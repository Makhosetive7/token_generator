package com.example.ElectricityTokenGenerator.dto.Tokens;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenTransferDTO {
    private UserEntities senderAccountNumber;
    private UserEntities receiverAccountNumber;
    private Double kiloWatts;
    private Long TransferTokenId;
    private LocalDateTime createdAt;
}
