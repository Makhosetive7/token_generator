package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.DonationsEntity;
import com.example.ElectricityTokenGenerator.enums.DonationsEnumerator;
import com.example.ElectricityTokenGenerator.repository.UserRepository;
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
    private final UserRepository  userRepository;

    public DonationsServices(DonationsRepository donationsRepository, tokensRepository tokensRepository, UserRepository userRepository) {
        this.donationsRepository = donationsRepository;
        this.tokensRepository = tokensRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public DonationsEntity createDonation(Long donationAccountNumber, Long donatorsAccountNumber, Double amountDonated,Double kiloWatts ,DonationsEnumerator donationType, LocalDateTime createdAt) {

        // Fetch account number information for donation account Number
        Optional<TokensEntity> donationAccountOptional = tokensRepository.findByAccountNumber(donationAccountNumber);
        if (donationAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Donation account not found.");
        }

        Optional<TokensEntity> donatorsAccountOptional = tokensRepository.findByAccountNumber(donatorsAccountNumber);
        if (donatorsAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Donators account not found.");
        }

        TokensEntity donationAccount = donationAccountOptional.get();
        TokensEntity donatorsAccount = donatorsAccountOptional.get();

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
        tokensRepository.save(donatorsAccount);

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
