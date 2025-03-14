package com.example.ElectricityTokenGenerator.entity.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.enums.LocalVendors;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LOCAL_VENDOR")
@Entity
@Data
@Builder
public class LocalVendor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "amount", nullable = false)
  private Double amount;

  @Enumerated(EnumType.STRING)
  @Column(name = "vendor", nullable = false)
  private LocalVendors localVendor; 

  @Column(name = "date_time", nullable = false)
  private LocalDateTime dateTime;

  @JoinColumn(name = "user_id", nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private User sender; // Sender of the donation (the user initiating the donation)

  @Column(name = "receiver_account_number", nullable = false)
    private String receiverAccountNumber; // Receiver account number (from Local Vendor enum)




}
