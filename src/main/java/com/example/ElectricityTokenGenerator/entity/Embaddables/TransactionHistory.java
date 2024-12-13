package com.example.ElectricityTokenGenerator.entity.Embaddables;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Embeddable
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private double transactionAmount;
    private LocalDateTime timeStamp;
}
