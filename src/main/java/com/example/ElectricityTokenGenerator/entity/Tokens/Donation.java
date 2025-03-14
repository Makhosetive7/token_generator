package com.example.ElectricityTokenGenerator.entity.Tokens;

import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.enums.Donations;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DONATIONS")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User senderAccountNumber; // Sender of the donation (the user initiating the donation)

    @Column(name = "receiver_account_number", nullable = false)
    private User receiverAccountNumber; // Receiver account number (from Donations enum)

    @Column(name = "kiloWatts", nullable = false)
    private User kiloWatts; // Amount of energy donated (in kilo watts)

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Donations donationType; // Type of donation (e.g., EDUCATION_SUPPORT, HEALTH_SUPPORT)

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt; // Timestamp of the donation
}