package com.example.ElectricityTokenGenerator.entity.Embaddables;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;

@Embeddable
public class TransactionHistory {
    private Long transactionId;
    private double transactionAmount;
    private LocalDateTime timeStamp;
}
