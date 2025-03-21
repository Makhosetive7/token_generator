package com.example.ElectricityTokenGenerator.dto.Users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class userRegistrationDTO {
    private String userName;
    private String lastName;
    private String password;
    private String email;
    private String phoneNumber;
    private String homeAddress;
}