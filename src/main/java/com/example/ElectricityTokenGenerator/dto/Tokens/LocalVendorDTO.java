package com.example.ElectricityTokenGenerator.dto.Tokens;

import com.example.ElectricityTokenGenerator.enums.LocalVendors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalVendorDTO {
    private String vendorAccountNumber; 
    private String purchaseAccountNumber; 
    private LocalVendors vendorTypeEnumerator; // Type of vendor (e.g., GROCERY_STORE)
    private Double convertedValue; 
    private Double purchaseAmount; // Amount of kilowatts purchased
    private LocalDateTime createdAt; 
}