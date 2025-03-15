package com.example.ElectricityTokenGenerator.dto.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.enums.Donations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationsDTO {
    private String senderAccountNumber; 
    private String receiverAccountNumber; 
    private Double kiloWatts;
    private Double convertedValue;
    private Donations donationType; // Type of donation (e.g., EDUCATION_SUPPORT, HEALTH_SUPPORT)
    private LocalDateTime createdAt;

}