package com.example.ElectricityTokenGenerator.dto.Tokens;

import java.time.LocalDateTime;

import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;
import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import com.example.ElectricityTokenGenerator.enums.DonationsEnumerator;

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
    private UserEntities donationAccountNumber;
    private UserEntities donatorsAccountNumber;
    private Double amountDonated;
    private TokenEntities kiloWatts;
    private DonationsEnumerator donationType;
    private LocalDateTime createdAt;
    

    
}
