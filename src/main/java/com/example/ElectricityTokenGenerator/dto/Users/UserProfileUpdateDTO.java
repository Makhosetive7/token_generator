package com.example.ElectricityTokenGenerator.dto.Users;

import javax.management.relation.Role;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileUpdateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String homeAddress;
    private Role role;
}