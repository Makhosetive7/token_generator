package com.example.ElectricityTokenGenerator.entity.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "CREATED TOKENS DATABASE TABLE")
public class CreateTokenEntities {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amountPaid;

    @ManyToOne
    @JoinColumn(name = "token_id", referencedColumnName ="UserID")
    private UserEntities accountNumber;

    private LocalDateTime createdAt;

}
