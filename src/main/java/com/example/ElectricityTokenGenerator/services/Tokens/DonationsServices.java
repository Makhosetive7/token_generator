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

    public DonationsServices(DonationsRepository donationsRepository, TokenRepository tokenRepository,
            userRepository userRepository) {
        this.donationsRepository = donationsRepository;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public DonationsEntity createDonation(DonationsEnumerator donationType, String donationsAccountNumber,
            String donatorsAccountNumber, Double kiloWatts, LocalDateTime createdAt) {

        // Validate that the account number matches the required account number for the
        // given donation type
        Optional<TokenEntities> donationsAccountOptional = tokenRepository
                .findByAccountNumber(donationType.getRequiredAccountNumber());
        if (donationsAccountOptional.isEmpty() ||
                !donationsAccountOptional.get().getAccountNumber().equals(donationType.getRequiredAccountNumber())) {
            throw new IllegalArgumentException("Provided account number does not match the required account number for "
                    + donationType.name() + ".");
        }

        // Validate that the donators account exists
        Optional<TokenEntities> donatorsAccountOptional = tokenRepository.findByAccountNumber(donatorsAccountNumber);
        if (donatorsAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Donators account not found.");
        }

        TokenEntities donatorsAccount = donatorsAccountOptional.get();

        // Validate the donation
        if (donatorsAccount.getKiloWatts() < MINIMUM_KILOWATTS_CONVERTED) {
            throw new IllegalArgumentException(
                    "Insufficient kilowatts to convert. User must have at least 250 kilowatts.");
        }

        Double convertedValue = kiloWatts * CONVERSION_RATE;

        if (convertedValue <= 0 || convertedValue < MINIMUM_DONATION_AMOUNT) {
            throw new IllegalArgumentException("Donation amount must be at least $10.0.");
        }

        // Update the user's kiloWatts and save the updated user
        userRepository.findByAccountNumber(donatorsAccountNumber).ifPresentOrElse(user -> {
            double currentKiloWatts = user.getKiloWatts() != null ? user.getKiloWatts() : 0.0;
            double newKiloWatts = currentKiloWatts + kiloWatts;
            user.setKiloWatts(newKiloWatts);
            userRepository.save(user);
            System.out.println("User updated: " + user);
        }, () -> {
            System.out.println("User not found with accountNumber: " + donatorsAccountNumber);
        });

        // Update donations Account
        donatorsAccount.setKiloWatts(donatorsAccount.getKiloWatts() - kiloWatts);
        tokenRepository.save(donatorsAccount);

        // Create the donation entity
        DonationsEntity newDonation = new DonationsEntity();
        newDonation.setDonationType(donationType);
        newDonation.setDonatorsAccountNumber(donatorsAccount);
        newDonation.setDonationAccountNumber(donationsAccountOptional.get());
        newDonation.setKiloWatts(kiloWatts);
        newDonation.setConvertedValue(convertedValue);
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
