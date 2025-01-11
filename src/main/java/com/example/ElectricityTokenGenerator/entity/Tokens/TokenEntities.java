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

    @Column(name = "AccountNumber", unique = true, length = 20)
    private Long accountNumber;

    @Column(name = "AmountPaid")
    private Double amountPaid;

    @Column(name = "TokenGenerated", unique = true, length = 20)
    private String tokenGenerated;

    @Column(name = "SerialNumber", unique = true, length = 20)
    private String serialNumber;

    @Column(name = "KiloWatts")
    private Double kiloWatts;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;

    @Column(name = "ExpiredAt")
    private LocalDateTime expiredAt;
}