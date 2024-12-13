package com.example.ElectricityTokenGenerator.entity.Embaddables;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class DonationHistory {
    
    private Long DonationId;
    private String DonationCause;
    private Double DonationAmount;
    private LocalDateTime timeStamp;


    
}
