package com.example.ElectricityTokenGenerator.dto.Tokens;

import com.example.ElectricityTokenGenerator.enums.LocalVendors;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class LocalVendorDTO {
    private String vendorAccountNumber; 
    private String purchaseAccountNumber; 
    private LocalVendors vendorTypeEnumerator; // Type of vendor (e.g., GROCERY_STORE)
    private Double convertedValue; 
    private Double purchaseAmount; // Amount of kilowatts purchased
    private LocalDateTime createdAt; 
}