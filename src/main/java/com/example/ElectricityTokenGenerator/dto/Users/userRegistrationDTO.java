package com.example.ElectricityTokenGenerator.dto.Users;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String password; 
    private String phoneNumber;
    private String accountNumber;
    private String homeAddress;
}