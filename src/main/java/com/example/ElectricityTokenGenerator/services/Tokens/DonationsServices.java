package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.Tokens.DonationsEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;
import com.example.ElectricityTokenGenerator.enums.DonationsEnumerator;
import com.example.ElectricityTokenGenerator.repository.Tokens.DonationsRepository;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

import jakarta.transaction.Transactional;


@Service
public class DonationsServices {
    private final Double CONVERSION_RATE = 2.5 / 5;
    private final Double MINIMUM_KILOWATTS_CONVERTED = 250.0;
    private final Double MINIMUM_DONATION_AMOUNT = 10.0;

   private final TokenRepository tokenRepository;
    private final DonationsRepository donationsRepository;
    private final userRepository userRepository;

    public DonationsServices(DonationsRepository donationsRepository, TokenRepository tokenRepository, userRepository userRepository) {
        this.donationsRepository = donationsRepository;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public DonationsEntity createDonation(String donationAccountNumber, String donatorsAccountNumber, Double amountDonated,Double kiloWatts ,DonationsEnumerator donationType, LocalDateTime createdAt) {

        // Fetch account number information for donation account Number
        Optional<TokenEntities> donationAccountOptional = tokenRepository.findByAccountNumber(donationAccountNumber);
        if (donationAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Donation account not found.");
        }

        Optional<TokenEntities> donatorsAccountOptional = tokenRepository.findByAccountNumber(donatorsAccountNumber);
        if (donatorsAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Donators account not found.");
        }

        TokenEntities donationAccount = donationAccountOptional.get();
        TokenEntities donatorsAccount = donatorsAccountOptional.get();

        // Validate the donation
        if (donatorsAccount.getKiloWatts() < MINIMUM_KILOWATTS_CONVERTED) {
            throw new IllegalArgumentException("Insufficient kilowatts to convert. User must have at least 250 kilowatts.");
        }

        Double convertedValue = kiloWatts * CONVERSION_RATE;

        if (convertedValue <= 0 || convertedValue < MINIMUM_DONATION_AMOUNT) {
            throw new IllegalArgumentException("Donation amount must be at least $10.0.");
        }

        // Update donations Account
        donatorsAccount.setKiloWatts(donatorsAccount.getKiloWatts() - kiloWatts);
        tokenRepository.save(donatorsAccount);

        // Create the donation entity
        DonationsEntity newDonation = new DonationsEntity();
        newDonation.setDonationAccountNumber(donationAccount);
        newDonation.setDonatorsAccountNumber(donatorsAccount);
        newDonation.setAmountDonated(amountDonated);
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
