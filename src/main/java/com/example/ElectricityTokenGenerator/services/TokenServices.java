package com.example.ElectricityTokenGenerator.services;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenTransferDTO;
import com.example.ElectricityTokenGenerator.entity.TokensEntity;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.repository.TokensRepository;
import com.example.ElectricityTokenGenerator.repository.UserRepository;
import com.example.ElectricityTokenGenerator.services.calculations.ElectricityTokenConversion;

@Service
public class TokenServices {

    private final TokensRepository tokensRepository;
    private final UserRepository userRepository;
    private final ElectricityTokenConversion electricityTokenConversion;

    @Autowired
    public TokenServices(TokensRepository tokensRepository, UserRepository userRepository,
            ElectricityTokenConversion electricityTokenConversion) {
        this.tokensRepository = tokensRepository;
        this.userRepository = userRepository;
        this.electricityTokenConversion = electricityTokenConversion;
    }

    // create tokens
    public TokensEntity createTokens(Long accountNumber, Double amountPaid, String serialNumber,
            LocalDateTime timeStamp) {
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

    // Token Generation Logic
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

    //Transfer Tokens
   public void transferTokens(Long sendingAccountNumber, Long receivingAccountNumber, Long accountNumber, Long amountTransferred, Double kiloWatts) {
        // Validate the sender's account exists
        if (!userRepository.existsBySendingAccountNumber(sendingAccountNumber)) {
            throw new IllegalArgumentException("Sending account does not exist.");
        }

        // Validate the receiver's account exists
        if (!userRepository.existsByReceivingAccountNumber(receivingAccountNumber)) {
            throw new IllegalArgumentException("Receiving account does not exist.");
        }

        // Retrieve sender's tokens
        List<TokensEntity> senderTokens = tokensRepository.findByAccountNumber(sendingAccountNumber);

        // Calculate total available tokens for the sender
        double totalAvailableTokens = senderTokens.stream().mapToDouble(TokensEntity::getKiloWatts).sum();

        // Check if the sender has enough tokens
        if (totalAvailableTokens < amountTransferred) {
            throw new IllegalArgumentException("Insufficient tokens in the sending account.");
        }

        // Deduct tokens from the sender
        double remainingAmount = amountTransferred;
        for (TokensEntity token : senderTokens) {
            double tokenBalance = token.getKiloWatts();
            if (tokenBalance >= remainingAmount) {
                token.setKiloWatts(tokenBalance - remainingAmount);
                tokensRepository.save(token);
                break;
            } else {
                token.setKiloWatts(0.0);
                remainingAmount -= tokenBalance;
                tokensRepository.save(token);
            }
        }

        // Add tokens to the receiver's account
        TokensEntity receiverToken = new TokensEntity();
        receiverToken.setAccountNumber(accountNumber);
        receiverToken.setKiloWatts(kiloWatts);
        receiverToken.setCreatedAt(LocalDateTime.now());
        receiverToken.setExpiredAt(LocalDateTime.now().plusDays(75));
        receiverToken.setTokenGenerated(generateUniqueToken());
        receiverToken.setSerialNumber(generateUniqueSerialNumber());

        tokensRepository.save(receiverToken);
    }

}
