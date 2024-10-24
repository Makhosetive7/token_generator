package com.example.ElectricityTokenGenerator.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TOKENS")
public class TokensEntity {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "account_number", unique = true, nullable = false, length = 20)
    private Long accountNumber;

    @Column(name = "amount_paid", nullable = false)
    private Double amountPaid;

    @Column(name = "token_generated", unique = true, nullable = false, length = 20)
    private String tokenGenerated;

    @Column(name = "serial_number", unique = true, nullable = false, length = 20)
    private String serialNumber;

    @Column(name = "sending_account", nullable = false)
    private Long sendingAccountNumber;

    @Column(name = "receiving_account", nullable = false)
    private Long receivingAccountNumber;

    @Column(name = "amount_transferred")
    private Long  amountTransferred;

    @Column(name = "transfer_date")
    private LocalDateTime transferDate;

    @Column(name = "kiloWatts")
    private Double kiloWatts;

    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    
    @Column(name = "expirersAt")
    private LocalDateTime expiredAt;
}
