package com.example.ElectricityTokenGenerator.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsersDTO {
    private Long Id;
    private String userName;
    private String phoneNumber;
    private String homeAddress;
    private LocalDateTime timeStamp;
}
