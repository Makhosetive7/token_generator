package com.example.ElectricityTokenGenerator.entity.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
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
@Table(name = "LOCAL_VENDOR"  )
@Entity
@Data
@Builder
public class LocalVendorEntity {
    @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @ManyToOne
    @JoinColumn(name = "account_number", unique = true)
    public TokensEntity accountNumber;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "vendor_type")
    public LocalVendorEnumerator vendorTypeEnumerator;

    @ManyToOne
    @JoinColumn(name = "kiloWatts")
    public TokensEntity kiloWatts;

    @Column(name = "converted_value")
    public double convertedValue;

    @Column(name = "created_at")
    public LocalDateTime createdAt;

}
