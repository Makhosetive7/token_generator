package com.example.ElectricityTokenGenerator.dto.Users;

import javax.management.relation.Role;

import lombok.Builder;
import lombok.Data;



@Data
@Builder
public class UserProfileUpdateDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String homeAddress;
    private Role role;
}