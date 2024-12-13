package com.example.ElectricityTokenGenerator.entity.Tokens;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @JoinColumn(name = "receiverAccount_number")
    private TokenEntities receiverAccountNumber;
    
    @ManyToOne
    @JoinColumn(name = "senderAccount_number")
    private TokenEntities senderAccountNumber;

    @Column(name = "Token_Transfer_Id")
    private Long TransferTokenId;

    @Column(name = "kiloWatts")
    private Double kiloWatts;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;


}
