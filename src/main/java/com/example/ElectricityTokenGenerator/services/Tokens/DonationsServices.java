package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.DonationsEntity;
import com.example.ElectricityTokenGenerator.enums.DonationsEnumerator;
import com.example.ElectricityTokenGenerator.repository.tokensRepository;
import com.example.ElectricityTokenGenerator.repository.Tokens.DonationsRepository;

import jakarta.transaction.Transactional;


@Service
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

    @Transactional
    public DonationsEntity createDonation(Long accountNumber, Double kiloWatts, Double amountPaid, 
                                          Long serialNumber, DonationsEnumerator donationType, LocalDateTime createdAt) {

        // Fetch the TokensEntity using account number
        Optional<TokensEntity> userAccountOptional = tokensRepository.findById(accountNumber);
        if (userAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("User account not found.");
        }
        TokensEntity userAccount = userAccountOptional.get();

        // Validate the donation
        if (userAccount.getKiloWatts() < MINIMUM_KILOWATTS_CONVERTED) {
            throw new IllegalArgumentException("Insufficient kilowatts to convert. User must have at least 250 kilowatts.");
        }

        Double convertedValue = kiloWatts * CONVERSION_RATE;

        if (convertedValue <= 0 || convertedValue < MINIMUM_DONATION_AMOUNT) {
            throw new IllegalArgumentException("Donation amount must be at least $10.0.");
        }

        // Update the user account
        userAccount.setKiloWatts(userAccount.getKiloWatts() - kiloWatts);
        tokensRepository.save(userAccount);

        // Create the donation entity
        DonationsEntity newDonation = new DonationsEntity();
        newDonation.setAccountNumber(userAccount);
        newDonation.setAmountPaid(amountPaid);
        newDonation.setSerialNumber(serialNumber);
        newDonation.setConvertedValue(convertedValue);
        newDonation.setDonationType(donationType);
        newDonation.setCreatedAt(createdAt);

        return donationsRepository.save(newDonation);
    }
    // - Get donation by ID
    // - Get donation by user ID
    // - Get donation by status
    // - Get donation by date
    // - Get donations for a specific user
     // - Delete a donation



}
