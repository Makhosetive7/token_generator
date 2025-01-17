package com.example.ElectricityTokenGenerator.entity.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import com.example.ElectricityTokenGenerator.enums.LocalVendorEnumerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "LOCAL VENDOR TRANSACTIONS DATABASE TABLE"  )
@Entity
@Data
@Builder
public class LocalVendorEntity {
    @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "Vendor", referencedColumnName = "UserID")
    public UserEntities vendorAccountNumber;

    @ManyToOne
    @JoinColumn(name = "Buyer", referencedColumnName = "UserID")
    public UserEntities purchaseAccountNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "VendorType")
    public LocalVendorEnumerator vendorTypeEnumerator;

    @Column(name = "ConvertedValue")
    public Double convertedValue;

    @Column(name = "PurchaseAmount")
    public Double purchaseAmount;

  @Column(name = "KiloWatts")
    private Double kiloWatts;

    @Column(name = "CreatedAt")
    public LocalDateTime createdAt;

}
