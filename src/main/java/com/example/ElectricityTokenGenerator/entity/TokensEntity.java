package com.example.ElectricityTokenGenerator.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private Long Id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String userName;

    @Column(name = "account_number", unique = true, nullable = false, length = 20)
    private Long accountNumber;

    @Column(name = "amount_paid", nullable = false)
    private Double amountPaid;

    @Column(name = "token_generated", unique = true, nullable = false, length = 20)
    private String tokenGenerated;

    @Column(name = "serial_number", unique = true, nullable = false, length = 20)
    private String serialNumber;

    private LocalDateTime timeStamp;
}
