package com.example.ElectricityTokenGenerator.entity.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.User;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "token_transfers")
public class TokenTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User senderAccountNumber;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiverAccountNumber;

    @Column(name = "transferred_token_code", nullable = false, unique = true, length = 16)
    private String transferredTokenCode;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "kilo_watts", nullable = false)
    private Double kiloWatts;

    @Column(name = "transfer_date", nullable = false)
    private LocalDateTime transferDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TokenTransfer status;
}
