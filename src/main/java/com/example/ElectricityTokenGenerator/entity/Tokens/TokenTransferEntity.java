package com.example.ElectricityTokenGenerator.entity.Tokens;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TOKEN_TRANSFER")
public class TokenTransferEntity {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @Column(name = "receiverAccount_number", unique = true, nullable = false, length = 20)
    private TokensEntity receiverAccountNumber;
    
    @ManyToOne
    @Column(name = "senderAccount_number", unique = true, nullable = false, length = 20)
    private TokensEntity senderAccountNumber;

    @Column(name = "Token_Transfer_Id", unique = true, nullable = false)
    private Long TransferTokenId;

    @Column(name = "kiloWatts")
    private Double kiloWatts;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;


}
