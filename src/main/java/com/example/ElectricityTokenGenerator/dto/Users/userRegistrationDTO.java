package com.example.ElectricityTokenGenerator.dto.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationDTO {

    @NotBlank(message = "First name is required")
    @Size(max = 20, message = "First name must be less than 20 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 20, message = "Last name must be less than 20 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "Password must contain at least 8 characters, including at least one uppercase letter, one lowercase letter, and one number")
    private String password;

    @Pattern(regexp = "^2637[1-9]\\d{7}$\r\n", message = "Invalid phone number format eg 263782082120")
    @Size(max = 10, message = "Phone number must be less than 20 characters")
    private String phoneNumber;
    private String accountNumber;
    private String homeAddress;
}
