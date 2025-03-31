package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.dto.Tokens.DonationsDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.Donation;
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
     * Converting Available user kiloWatts and donating them to the need in monetary
     * value
     *
     * @param senderAccountNumber   The account number of the sender.
     * @param receiverAccountNumber The account number of the receiver
     * @param kiloWatts             Amount of kilOWatts to be converted.
     * @param donationType          The donation type.
     * @param createdAt             Date when the donation was made.
     * @return The created token.
     * @throws RuntimeException If the user is not found.
     */

    @Transactional
    public Donation createDonation(Donations donationType, String senderAccountNumber,
            String receiverAccountNumber, Double kiloWatts, LocalDateTime createdAt) {

        // Fetch sender account number from database
        User sender = userRepository.findByAccountNumber(senderAccountNumber)
                .orElseThrow(() -> new RuntimeException(
                        "User (sender) not found with account number " + senderAccountNumber));

        // Fetch receiver account number from database
        User receiver = userRepository.findByAccountNumber(receiverAccountNumber)
                .orElseThrow(() -> new RuntimeException(
                        "User (receiver) not found with account number " + receiverAccountNumber));

        // Check if the sender has enough kiloWatts to convert and create the donation
        if (sender.getKiloWatts() < MINIMUM_KILOWATTS_CONVERTED) {
            throw new IllegalArgumentException(
                    "Insufficient kilowatts to convert. User must have at least 250 kilowatts.");
        }

        // Convert kiloWatts to monetary value and check if it is a valid donation
        Double convertedValue = kiloWatts * CONVERSION_RATE;

        if (convertedValue <= 0 || convertedValue < MINIMUM_DONATION_AMOUNT) {
            throw new IllegalArgumentException("Donation amount must be at least $10.0.");
        }

        // Update the receiver's account after donation
        double receiverKiloWatts = receiver.getKiloWatts() != null ? receiver.getKiloWatts() : 0.0;
        receiver.setKiloWatts(receiverKiloWatts + kiloWatts);
        userRepository.save(receiver);

        // Update the sender's account after donation
        sender.setKiloWatts(sender.getKiloWatts() - kiloWatts);
        userRepository.save(sender);

        // Create the donation entity
        Donation newDonation = new Donation();
        newDonation.setDonationType(donationType);
        newDonation.setSender(sender); 
        newDonation.setReceiver(receiver); 
        newDonation.setKiloWatts(kiloWatts);
        newDonation.setConvertedValue(convertedValue);
        newDonation.setCreatedAt(createdAt);


        return donationsRepository.save(newDonation);
    }
}
