package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.DonationsEntity;
import com.example.ElectricityTokenGenerator.enums.DonationsEnumerator;
import com.example.ElectricityTokenGenerator.repository.tokensRepository;
import com.example.ElectricityTokenGenerator.repository.Tokens.DonationsRepository;

import jakarta.transaction.Transactional;

public class DonationsServices {
    private final Double CONVERSION_RATE = 2.5 / 5;
    private final Double MINIMUM_KILOWATTS_CONVERTED = 250.0;
    private final Double MINIMUM_DONATION_AMOUNT = 10.0;


    private final tokensRepository tokensRepository;
    private final DonationsRepository donationsRepository;

    public DonationsServices(DonationsRepository donationsRepository, tokensRepository tokensRepository) {
        this.donationsRepository = donationsRepository;
        this.tokensRepository = tokensRepository;
    }

    
    // - Create a new donation
    @Transactional
    public DonationsEntity createDonation(Long accountNumber, Double kiloWatts, Double amountPaid, 
                                          Long serialNumber, DonationsEnumerator donationType, LocalDateTime createdAt) {
    
        // Fetch the user account from TokensEntity
        Optional<TokensEntity> userAccountOptional = tokensRepository.findById(accountNumber);
        if (userAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("User account not found.");
        }
        TokensEntity userAccount = userAccountOptional.get();
    
        // Validate sufficient kilowatts
        if (userAccount.getKiloWatts() < MINIMUM_KILOWATTS_CONVERTED) {
            throw new IllegalArgumentException("Insufficient kilowatts to convert. User must have at least 250 kilowatts.");
        }
    
        // Calculate the converted value in dollars
        Double convertedValue = kiloWatts * CONVERSION_RATE;
    
        // Validate the converted value
        if (convertedValue <= 0) {
            throw new IllegalArgumentException("Converted value must be greater than $0.0.");
        }
        if (convertedValue < MINIMUM_DONATION_AMOUNT) {
            throw new IllegalArgumentException("Donation amount must be at least $10.0.");
        }
    
        // Deduct the kilowatts from the user's account
        userAccount.setKiloWatts(userAccount.getKiloWatts() - kiloWatts);
        tokensRepository.save(userAccount);
    
        // Create the donation entity
        DonationsEntity newDonation = DonationsEntity.builder()
            .accountNumber(userAccount)
            .amountPaid(amountPaid)
            .serialNumber(serialNumber)
            .convertedValue(convertedValue)
            .donationType(donationType)
            .createdAt(createdAt)
            .build();
    
        // Save the donation to the database
        return donationsRepository.save(newDonation);
    }



    // - Get donation by ID
    // - Get donation by user ID
    // - Get donation by status
    // - Get donation by date
    // - Get donations for a specific user
     // - Delete a donation



}
