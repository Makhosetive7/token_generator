package com.example.ElectricityTokenGenerator.entity.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import com.example.ElectricityTokenGenerator.enums.DonationsEnumerator;

import jakarta.persistence.Column;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DONATION DATABASE TABLE")
public class DonationsEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "Receiver" , referencedColumnName ="UserID")
  private UserEntities donationAccountNumber;

  @ManyToOne
  @JoinColumn(name = "Donor" , referencedColumnName ="UserID")
  private UserEntities donatorsAccountNumber;

  @Column(name = "KiloWatts")
  private Double kiloWatts; 

  @Column(name = "AmountDonated")
  private Double amountDonated;

  @Column(name = "ConvertedValue")
  private Double convertedValue;

  @Column(name = "DonationType")
  private DonationsEnumerator donationType;

  @Column(name = "CreatedAt")
  private LocalDateTime createdAt;
}
