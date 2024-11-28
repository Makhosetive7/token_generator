package com.example.ElectricityTokenGenerator.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class TokenTransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sending_account", nullable = true)
    private Long sendingAccountNumber;

    @Column(name = "receiving_account", nullable = true)
    private Long receivingAccountNumber;

    @Column(name = "amount_transferred")
    private Long amountTransferred;

    @Column(name = "transfer_date")
    private LocalDateTime transferDate;

    @Column(name = "kiloWatts")
    private Long kiloWatts;

}
