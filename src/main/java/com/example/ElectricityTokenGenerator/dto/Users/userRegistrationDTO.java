package com.example.ElectricityTokenGenerator.dto.Users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class userRegistrationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password; 
    private String phoneNumber;
    private String accountNumber;
    private String homeAddress;
}