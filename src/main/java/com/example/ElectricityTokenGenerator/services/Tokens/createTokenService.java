package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.repository.UserRepository;
import com.example.ElectricityTokenGenerator.repository.tokensRepository;
import com.example.ElectricityTokenGenerator.services.calculations.ElectricityTokenConversion;

@Service
public class createTokenService {

    public final tokensRepository   tokensRepository;
    public final ElectricityTokenConversion electricityTokenConversion;
    public final UserRepository userRepository;

    public createTokenService(tokensRepository tokensRepository, UserRepository userRepository, ElectricityTokenConversion electricityTokenConversion) {
        this.tokensRepository = tokensRepository;
        this.electricityTokenConversion = electricityTokenConversion;
        this.userRepository = userRepository;
    }

        // create tokens
    public TokensEntity createTokens(Long accountNumber, Double amountPaid, String serialNumber, LocalDateTime timeStamp) {
        TokensEntity tokens = new TokensEntity();
        tokens.setAccountNumber(accountNumber);
        tokens.setAmountPaid(amountPaid);
        tokens.setTokenGenerated(generateUniqueToken());
        tokens.setSerialNumber(generateUniqueSerialNumber());
        tokens.setCreatedAt(LocalDateTime.now()); 
        tokens.setExpiredAt(LocalDateTime.now().plusDays(75));
       tokens.setKiloWatts(electricityTokenConversion.convertAmountPaidToKilowatts(amountPaid));
    
        return tokensRepository.save(tokens);
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
    } while (tokensRepository.existsByTokenGenerated(tokenGenerated));

    return tokenGenerated;
}

// Serial Number Generation Logic (10-digit numeric serial number)
private String generateUniqueSerialNumber() {
    String serialNumber;
    Random random = new Random();

    do {
        serialNumber = String.format("%010d", random.nextInt(1000000000));
    } while (tokensRepository.existsBySerialNumber(serialNumber));

    return serialNumber;
}


    
}
