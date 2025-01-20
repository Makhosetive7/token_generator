package com.example.ElectricityTokenGenerator.entity.Tokens;


import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;

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

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ALL TOKENS DATABASE TABLE")
public class TokenEntities {
        @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "TokenID", referencedColumnName ="UserID")
    private UserEntities accountNumber;

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