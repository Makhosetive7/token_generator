package com.example.ElectricityTokenGenerator.dto.Users;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsersDTO {
    private Long Id;
    private String userName;
    private String accountNumber;
    private String email;
    private String phoneNumber;
    private String homeAddress;
    private String transfer_history;
    private String donation_history;
}