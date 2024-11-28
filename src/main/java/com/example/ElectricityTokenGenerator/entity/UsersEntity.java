package com.example.ElectricityTokenGenerator.entity;

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
public class UsersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identity_number", unique = true, length = 10)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String userName;

    @Column(name = "surname", nullable = false, length = 20)
    private String lastName;

    @Column(name = "account_number", nullable = false, unique = true ,length = 15)
    private Long accountNumber;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phoneNumber;

    @Column(name = "home_address", nullable = false)
    private String homeAddress;
}
