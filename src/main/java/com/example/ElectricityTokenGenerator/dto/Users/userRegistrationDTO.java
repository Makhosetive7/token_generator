package com.example.ElectricityTokenGenerator.dto.Users;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class userRegistrationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password; 
    private String phoneNumber;
    private String homeAddress;
    private String accountNumber;
}