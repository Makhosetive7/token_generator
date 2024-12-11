package com.example.ElectricityTokenGenerator.dto.Tokens;

import java.time.LocalDateTime;

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
    public Long vendorAccountNumber;
    public Long purchaseAccountNumber;
    public LocalVendorEnumerator vendorTypeEnumerator;
    public Double convertedValue;
    public Double purchaseAmount;
    public LocalDateTime createdAt;
}
