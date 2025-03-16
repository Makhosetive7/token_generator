package com.example.ElectricityTokenGenerator.entity.Users;

import java.time.LocalDateTime;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import com.example.ElectricityTokenGenerator.entity.Tokens.DonationsEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.LocalVendorEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class UserEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @NotBlank(message = "First name is required")
    @Size(max = 20, message = "First name must be less than 20 characters")
    @Column(name = "firstName", nullable = false, length = 20)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 20, message = "Last name must be less than 20 characters")
    @Column(name = "lastName", nullable = false, length = 20)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Account number is required")
    @Size(max = 15, message = "Account number must be less than 15 characters")
    @Column(name = "accountNumber", nullable = false, unique = true, length = 15)
    private String accountNumber;

    @NotBlank(message = "Phone number is required")
    @Size(max = 20, message = "Phone number must be less than 20 characters")
    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "homeAddress")
    private String homeAddress;

    @Column(name = "amountPaid", nullable = false)
    private Double amountPaid = 0.0;

    @Column(name = "kiloWatts")
    private Double kiloWatts = 0.0;

    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

}