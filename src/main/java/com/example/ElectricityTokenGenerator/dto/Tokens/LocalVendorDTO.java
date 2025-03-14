package com.example.ElectricityTokenGenerator.dto.Tokens;

import com.example.ElectricityTokenGenerator.enums.LocalVendors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalVendorDTO {
    private String senderAccount;
    private LocalVendors vendorTypeEnumerator;
    private Double amount;
}
