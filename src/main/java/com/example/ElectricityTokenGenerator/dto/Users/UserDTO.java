package com.example.ElectricityTokenGenerator.dto.Users;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    @NotBlank(message = "First name is required")
    @Size(max = 20, message = "First name must be less than 20 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 20, message = "Last name must be less than 20 characters")
    private String lastName;

     @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number must be less than 20 characters")
    private String phoneNumber;

    @NotBlank(message = "Account number is required")
    @Size(max = 15, message = "Account number must be less than 15 characters")
    private String accountNumber;

    @Column(name = "kilo_watts")
    private Double kiloWatts;
}