package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;
import com.example.ElectricityTokenGenerator.services.calculations.ElectricityTokenConversion;

@Service
public class createTokenService {

    public final TokenRepository tokenRepository;
    public final ElectricityTokenConversion electricityTokenConversion;
    public final userRepository userRepository;

    public createTokenService(TokenRepository tokensRepository, userRepository userRepository, ElectricityTokenConversion electricityTokenConversion) {
        this.tokenRepository = tokensRepository;
        this.electricityTokenConversion = electricityTokenConversion;
        this.userRepository = userRepository;
    }

        // create tokens
        public TokenEntities createTokens(String accountNumber, Double amountPaid, String serialNumber, LocalDateTime timeStamp) {
            // Create a new token entity
            TokenEntities tokens = new TokenEntities();
            tokens.setAccountNumber(accountNumber);
            tokens.setAmountPaid(amountPaid);
            tokens.setTokenGenerated(generateUniqueToken());
            tokens.setSerialNumber(generateUniqueSerialNumber());
            tokens.setCreatedAt(LocalDateTime.now());
            tokens.setExpiredAt(LocalDateTime.now().plusDays(75));
            tokens.setKiloWatts(electricityTokenConversion.convertAmountPaidToKilowatts(amountPaid));
        
            // Save the token to the database
            tokenRepository.save(tokens);
        
            // Update the user's table
            userRepository.findByAccountNumber(accountNumber).ifPresentOrElse(user -> {
                double currentKiloWatts = user.getKiloWatts() != null ? user.getKiloWatts() : 0.0;
                double newKiloWatts = currentKiloWatts + electricityTokenConversion.convertAmountPaidToKilowatts(amountPaid);
                user.setKiloWatts(newKiloWatts);
                userRepository.save(user);
                System.out.println("User updated: " + user);
            }, () -> {
                System.out.println("User not found with accountNumber: " + accountNumber);
            });
            
            
            return tokens; // Return the saved token
        }
        
    
  // Token Generation Logic (20-character alphanumeric token)
  private String generateUniqueToken() {
    String tokenGenerated;
    Random random = new Random();
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    do {
        StringBuilder token = new StringBuilder(20);
        for (int i = 0; i < 20; i++) {
            token.append(characters.charAt(random.nextInt(characters.length())));
        }
        tokenGenerated = token.toString();
    } while (tokenRepository.existsByTokenGenerated(tokenGenerated));

    return tokenGenerated;
}

// Serial Number Generation Logic (10-digit numeric serial number)
private String generateUniqueSerialNumber() {
    String serialNumber;
    Random random = new Random();

    do {
        serialNumber = String.format("%010d", random.nextInt(1000000000));
    } while (tokenRepository.existsBySerialNumber(serialNumber));

    return serialNumber;
}


    
}
