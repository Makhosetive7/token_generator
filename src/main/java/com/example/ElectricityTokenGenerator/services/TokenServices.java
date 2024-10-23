package com.example.ElectricityTokenGenerator.services;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.repository.TokensRepository;
import com.example.ElectricityTokenGenerator.repository.UserRepository;

@Service
public class TokenServices {

    private final TokensRepository tokensRepository;
    private final UserRepository userRepository;

    @Autowired
    public TokenServices(TokensRepository tokensRepository, UserRepository userRepository) {
        this.tokensRepository = tokensRepository;
        this.userRepository = userRepository;
    }

    // create tokens
    public TokensEntity createTokens(Long accountNumber, Double amountPaid, String serialNumber, LocalDateTime timeStamp) {
        TokensEntity tokens = new TokensEntity();
        tokens.setAccountNumber(accountNumber);
        tokens.setAmountPaid(amountPaid);
        tokens.setTokenGenerated(generateUniqueToken());
        tokens.setSerialNumber(generateUniqueSerialNumber());
        tokens.setCreatedAt(timeStamp); 
    
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

    // retrieve all tokens
    public List<TokensEntity> getAllTokens() {
        return tokensRepository.findAll();
    }

    // get tokens by id
    public Optional<TokensEntity> getTokensById(Long id) {
        return tokensRepository.findById(id);
    }

    // cancel created tokens

    public void deleteTokens(Long id) {
        tokensRepository.deleteById(id);
    }

}
