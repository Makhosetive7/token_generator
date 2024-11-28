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
}