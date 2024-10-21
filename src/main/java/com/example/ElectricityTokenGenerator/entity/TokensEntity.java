package com.example.ElectricityTokenGenerator.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TokensEntity {
    private Long Id;
    private Long UserId;
    private String userName;
    private LocalDateTime timeStamp;
    private Long batchNumber;
    private Long serialNumber;
}
