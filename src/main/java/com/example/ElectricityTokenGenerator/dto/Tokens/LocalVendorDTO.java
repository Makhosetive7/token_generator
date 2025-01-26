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
    public String vendorAccountNumber;
    public String purchaseAccountNumber;
    public LocalVendorEnumerator vendorTypeEnumerator;
    public Double purchaseAmount;
    public LocalDateTime createdAt;
}
