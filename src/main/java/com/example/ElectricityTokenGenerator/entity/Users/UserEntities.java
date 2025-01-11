package com.example.ElectricityTokenGenerator.entity.Users;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class UserEntities{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identity_number", unique = true, length = 10)
    private Long id;

    @Column(name = "FirstName", nullable = false, length = 20)
    private String userName;

    @Column(name = "LastName", nullable = false, length = 20)
    private String lastName;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "AccountNumber", nullable = false, unique = true ,length = 15)
    private String accountNumber;

    @Column(name = "PhoneNumber", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "HomeAddress")
    private String homeAddress;

    @Column(name="TokenBalance")
    private Double tokenBalance;

    @Column(name = "Role")
    private String role;

    @Column(name = "TransactionHistory")
    private String transactionHistory;

    @Column(name = "DonationHistory")
    private String DonationHistory;

    @Column(name = "LocalVendorHistory")
    private String localVendorHistory;
}