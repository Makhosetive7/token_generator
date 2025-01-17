package com.example.ElectricityTokenGenerator.dto.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import com.example.ElectricityTokenGenerator.enums.LocalVendorEnumerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalVendorDTO {
    public UserEntities vendorAccountNumber;
    public UserEntities purchaseAccountNumber;
    public LocalVendorEnumerator vendorTypeEnumerator;
    public Double convertedValue;
    public Double purchaseAmount;
    private Double kiloWatts;
    public LocalDateTime createdAt;
}
