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
@Table(name = "token_generator")
public class TokenGenerator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "generated_token_code", nullable = false, unique = true, length = 16)
    private String generatedTokenCode;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "kilo_watts", nullable = false)
    private Double kiloWatts;

    @Column(name = "generation_date", nullable = false)
    private LocalDateTime generationDate;

    @ManyToOne
    @JoinColumn(name = "generated_by", nullable = false)
    private User accountNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TokenStatus status;
}
