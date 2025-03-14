package com.example.ElectricityTokenGenerator.entity.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.enums.TokenStatus;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_code", nullable = false, unique = true, length = 16)
    private String tokenCode;

    @Column(name = "amount", nullable = false)
    private Double amount; // Token value in currency

    @Column(name = "kilo_watts", nullable = false)
    private Double kiloWatts; // Power units

    @Column(name = "purchase_date", nullable = false)
    private LocalDateTime purchaseDate;

    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TokenStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User tokenBuyer;
}