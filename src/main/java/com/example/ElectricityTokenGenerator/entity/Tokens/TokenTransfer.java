package com.example.ElectricityTokenGenerator.entity.Tokens;

import com.example.ElectricityTokenGenerator.entity.Users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token_transfers")
public class TokenTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @Column(name = "kilo_watts", nullable = false)
    private Double kiloWatts;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}