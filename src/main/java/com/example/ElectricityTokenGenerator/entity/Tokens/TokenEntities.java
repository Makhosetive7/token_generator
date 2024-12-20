package com.example.ElectricityTokenGenerator.entity.Tokens;


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
public class TokenEntities {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "account_number", unique = true, length = 20)
    private Long accountNumber;

    @Column(name = "amount_paid")
    private Double amountPaid;

    @Column(name = "token_generated", unique = true, length = 20)
    private String tokenGenerated;

    @Column(name = "serial_number", unique = true, length = 20)
    private String serialNumber;

    @Column(name = "kiloWatts")
    private Double kiloWatts;

    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;
}