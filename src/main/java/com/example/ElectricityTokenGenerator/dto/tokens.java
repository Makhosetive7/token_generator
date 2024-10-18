package com.example.ElectricityTokenGenerator.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tokens {
    private Long Id;
    private Long UserId;
    private String userName;
    private LocalDateTime timeStamp;
    private Long batchNumber;
    private Long serialNumber;
}
