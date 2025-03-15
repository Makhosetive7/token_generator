package com.example.ElectricityTokenGenerator.entity.Tokens;

import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.enums.Donations;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "donation_type", nullable = false)
    private Donations donationType;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver; // from donations enumerator

    @Column(name = "kilo_watts", nullable = false)
    private Double kiloWatts;

    @Column(name = "converted_value", nullable = false)
    private Double convertedValue;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}