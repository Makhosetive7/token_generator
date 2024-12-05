package com.example.ElectricityTokenGenerator.entity.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.enums.DonationsEnumerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_number")
    private TokensEntity accountNumber;

    @ManyToOne
    @JoinColumn(name = "kiloWatts")
    private TokensEntity  kiloWatts;

    private Double amountPaid;

      @Column(name = "converted_value")
    public Double convertedValue;


    private Long serialNumber;

    private DonationsEnumerator donationType;

    private LocalDateTime createdAt;

}
