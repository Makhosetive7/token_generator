package com.example.ElectricityTokenGenerator.dto.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Embaddables.DonationHistory;
import com.example.ElectricityTokenGenerator.enums.DonationsEnumerator;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DonationsDTO {

    private Long Id;
    private Long donationAccountNumber;
    private Long donatorsAccountNumber;
    private Double amountDonated;
    private Double kiloWatts;
    
    @Embedded
    private DonationHistory DonationHistory;
    private DonationsEnumerator donationType;
    private LocalDateTime createdAt;
    

    
}
