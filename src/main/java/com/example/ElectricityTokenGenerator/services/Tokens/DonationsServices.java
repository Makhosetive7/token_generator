package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.dto.Tokens.DonationsDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.Donation;
import com.example.ElectricityTokenGenerator.entity.Tokens.Token;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.enums.Donations;
import com.example.ElectricityTokenGenerator.mappers.Tokens.DonationsMapper;
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
    private final DonationsMapper donationsMapper; // inject donations mapper

    @Autowired
    public DonationsServices(
            DonationsRepository donationsRepository,
            TokenRepository tokenRepository,
            userRepository userRepository,
            DonationsMapper donationsMapper) {
        this.donationsRepository = donationsRepository;
        this.tokenRepository = tokenRepository;
        this.userRepository = userRepository;
        this.donationsMapper = donationsMapper;

    }

/**
     * Converting Available user kiloWatts and donating them to the need in monetary value
     *
     * @param senderAccountNumber The account number of the sender.
     * @param receiverAccountNumber The account number of the receiver
     * @param kiloWatts    Amount of kilOWatts to be converted.
     * @param donationType The donation type.
     * @param createdAt Date when the donation was made.
     * @return The created token.
     * @throws RuntimeException If the user is not found.
     */

    @Transactional
    public DonationsDTO createDonation(Donations donationType, String senderAccountNumber,
            String receiverAccountNumber, Double kiloWatts, LocalDateTime createdAt) {

                //Fetch sender account number from database
                User sender = userRepository.findByAccountNumber(senderAccountNumber)
                        .orElseThrow(() -> new RuntimeException("User (sender) not found with account number " + senderAccountNumber));

                //Fetch receiver account number from database
                User receiver = userRepository.findByAccountNumber(receiverAccountNumber)
                        .orElseThrow(() -> new RuntimeException("User (receiver) not found with account number " + receiverAccountNumber));


        //  Check if the sender has enough kiloWatts to convert and create the donation
        if (senderAccountNumber.getKiloWatts() < MINIMUM_KILOWATTS_CONVERTED) {
            throw new IllegalArgumentException(
                    "Insufficient kilowatts to convert. User must have at least 250 kilowatts.");
        }

        Double convertedValue = kiloWatts * CONVERSION_RATE;

        if (convertedValue <= 0 || convertedValue < MINIMUM_DONATION_AMOUNT) {
            throw new IllegalArgumentException("Donation amount must be at least $10.0.");
        }

        // Update the Sender kiloWatts and save the updated user
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
        Donation newDonation = new Donation();
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
