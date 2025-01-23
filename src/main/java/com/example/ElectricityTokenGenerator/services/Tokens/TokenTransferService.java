package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenTransferEntity;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenTransferRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

@Service
public class TokenTransferService {

    private final TokenRepository tokenRepository;
    private final TokenTransferRepository tokenTransferRepository;
    private final userRepository userRepository;

    public TokenTransferService(TokenRepository tokenRepository, TokenTransferRepository tokenTransferRepository, userRepository userRepository) {
        this.tokenRepository = tokenRepository;
        this.tokenTransferRepository = tokenTransferRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public TokenTransferEntity transferTokens(String senderAccountNumber, String receiverAccountNumber, Double kilowatts, Long transferTokenId, LocalDateTime createdAt) {
        // Validate sender account exists
        Optional<TokenEntities> senderAccountOptional = tokenRepository.findByAccountNumber(senderAccountNumber);
        if (senderAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Sender account not found.");
        }
        
        // Validate receiver account exists
        Optional<TokenEntities> receiverAccountOptional = tokenRepository.findByAccountNumber(receiverAccountNumber);
        if (receiverAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Receiver account not found.");
        }

        // Validate if sender and receiver accounts are not the same
        if (senderAccountNumber.equals(receiverAccountNumber)) {
            throw new IllegalArgumentException("Sender and receiver account cannot be the same.");
        }
        
        // Extract sender and receiver
        TokenEntities senderAccount = senderAccountOptional.get();
        TokenEntities receiverAccount = receiverAccountOptional.get();

        // Validate sender has enough kilowatts
        if (senderAccount.getKiloWatts() < kilowatts) {
            throw new IllegalArgumentException("Insufficient balance to transfer.");
        }

        // Deduct kilowatts from sender
        senderAccount.setKiloWatts(senderAccount.getKiloWatts() - kilowatts);
        tokenRepository.save(senderAccount);

        // Add kilowatts to receiver
        receiverAccount.setKiloWatts(receiverAccount.getKiloWatts() + kilowatts);
        tokenRepository.save(receiverAccount);

        // Record the transfer
        TokenTransferEntity tokenTransfer = new TokenTransferEntity();
        tokenTransfer.setSenderAccountNumber(senderAccount);
        tokenTransfer.setReceiverAccountNumber(receiverAccount);
        tokenTransfer.setTransferTokenId(transferTokenId);
        tokenTransfer.setKiloWatts(kilowatts);
        tokenTransfer.setCreatedAt(createdAt);
        tokenTransfer = tokenTransferRepository.save(tokenTransfer);

        // Update sender and receiver accounts in user table
        updateUserKiloWatts(senderAccountNumber, kilowatts, false);
        updateUserKiloWatts(receiverAccountNumber, kilowatts, true);

        return tokenTransfer;
    }

    //update user's table after token transfer
    private void updateUserKiloWatts(String accountNumber, Double kilowatts, boolean isReceiver) {
        userRepository.findByAccountNumber(accountNumber).ifPresentOrElse(user -> {
            double currentKiloWatts = user.getKiloWatts() != null ? user.getKiloWatts() : 0.0;
            double newKiloWatts = isReceiver ? currentKiloWatts + kilowatts : currentKiloWatts - kilowatts;
            user.setKiloWatts(newKiloWatts);
            userRepository.save(user);
            System.out.println("User updated: " + user);
        }, () -> {
            System.out.println("User not found with accountNumber: " + accountNumber);
        });
    }
}
