package com.example.ElectricityTokenGenerator.entity.Users;

import com.example.ElectricityTokenGenerator.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "account_number", nullable = false, unique = true, length = 15)
    private String accountNumber;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "home_address")
    private String homeAddress;

    @Column(name = "amount_paid", nullable = false)
    private Double amountPaid = 0.0;

    @Column(name = "kilo_watts")
    private Double kiloWatts = 0.0;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}