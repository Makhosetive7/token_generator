package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ElectricityTokenGenerator.entity.Tokens.TokenTransferEntity;
import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenTransferRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

@Service
public class TokenTransferService {

    private final TokenTransferRepository tokenTransferRepository;
    private final userRepository userRepository;

    // Constructor
    public TokenTransferService(TokenTransferRepository tokenTransferRepository, userRepository userRepository) {
        this.tokenTransferRepository = tokenTransferRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public TokenTransferEntity transferTokens(String senderAccountNumber, String receiverAccountNumber,
                                              Double kilowatts, Long transferTokenId, LocalDateTime createdAt) {

        // Validate sender account exists (find by account number)
        Optional<UserEntities> senderAccountOptional = userRepository.findByAccountNumber(senderAccountNumber);
        if (senderAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Sender account not found for account number: " + senderAccountNumber);
        }

        // Validate if receiver account exists (find by account number)
        Optional<UserEntities> receiverAccountOptional = userRepository.findByAccountNumber(receiverAccountNumber);
        if (receiverAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Receiver account not found for account number: " + receiverAccountNumber);
        }

        // Extract sender and receiver entities
        UserEntities senderAccount = senderAccountOptional.get();
        UserEntities receiverAccount = receiverAccountOptional.get();

        // Validate sender has enough kilowatts
        if (senderAccount.getKiloWatts() < kilowatts) {
            throw new IllegalArgumentException("Insufficient balance to transfer from account: " + senderAccountNumber);
        }

        // Deduct kilowatts from sender
        senderAccount.setKiloWatts(senderAccount.getKiloWatts() - kilowatts);
        userRepository.save(senderAccount);

        // Add kilowatts to receiver
        receiverAccount.setKiloWatts(receiverAccount.getKiloWatts() + kilowatts);
        userRepository.save(receiverAccount);

        // Record the transfer
        TokenTransferEntity tokenTransfer = new TokenTransferEntity();
        tokenTransfer.setSenderAccountNumber(senderAccount.getAccountNumber()); 
        tokenTransfer.setReceiverAccountNumber(receiverAccount.getAccountNumber()); 
        tokenTransfer.setTransferTokenId(transferTokenId);
        tokenTransfer.setKiloWatts(kilowatts);
        tokenTransfer.setCreatedAt(createdAt);

        return tokenTransferRepository.save(tokenTransfer); 
    }
}
