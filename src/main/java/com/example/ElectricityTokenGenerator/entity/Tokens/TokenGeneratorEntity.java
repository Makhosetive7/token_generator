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
@Table(name = "tokens_generated")
public class TokenGeneratorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_number")
    private UserEntities accountNumber;

    @Column(name = "amount_paid")
    private Double amountPaid;

    @Column(name = "token_generated", unique = true, length = 20)
    private String tokenGenerated;

    @Column(name = "serial_number", unique = true, length = 20)
    private String serialNumber;

    @Column(name = "kilo_watts")
    private Double kiloWatts;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expired_at")
    private LocalDateTime expiredAt;
}