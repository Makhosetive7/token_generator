package com.example.ElectricityTokenGenerator.entity.Tokens;

import java.time.LocalDateTime;

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
  @JoinColumn(name = "DonationAccountNumber")
  private TokenEntities donationAccountNumber;

  @ManyToOne
  @JoinColumn(name = "DonatorAccountNumber")
  private TokenEntities donatorsAccountNumber;

  @Column(name = "KiloWatts")
  private Double kiloWatts; 

  @Column(name = "ConvertedValue")
  private Double convertedValue;

  @Column(name = "DonationType")
  private DonationsEnumerator donationType;

  @Column(name = "CreatedAt")
  private LocalDateTime createdAt;
}
