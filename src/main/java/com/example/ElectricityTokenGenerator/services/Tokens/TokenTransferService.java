package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenTransferDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.Token;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenTransfer;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.mappers.Tokens.TokenTransferMapper;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenTransferRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

@Service
public class TokenTransferService {

    private final Double MINIMUM_KILOWATTS_TRANSFER = 10.00;

    private final TokenRepository tokenRepository;
    private final TokenTransferRepository tokenTransferRepository;
    private final userRepository userRepository;
    private final TokenTransferMapper tokenTransferMapper;

    public TokenTransferService(
            TokenRepository tokenRepository,
            TokenTransferRepository tokenTransferRepository,
            userRepository userRepository,
            TokenTransferMapper tokenTransferMapper) {
        this.tokenRepository = tokenRepository;
        this.tokenTransferRepository = tokenTransferRepository;
        this.userRepository = userRepository;
        this.tokenTransferMapper = tokenTransferMapper;
    }

    @Transactional
    public TokenTransferDTO transferTokens(
            String senderAccountNumber,
            String receiverAccountNumber,
            Double kilowatts,
            LocalDateTime createdAt) {
    
        // Fetch sender account number from database
        User sender = userRepository.findByAccountNumber(senderAccountNumber)
                .orElseThrow(() -> new RuntimeException(
                        "User (sender) not found with account number " + senderAccountNumber));
    
        // Fetch receiver account number from database
        User receiver = userRepository.findByAccountNumber(receiverAccountNumber)
                .orElseThrow(() -> new RuntimeException(
                        "User (receiver) not found with account number " + receiverAccountNumber));
    
        // Validate if sender and receiver accounts are not the same
        if (senderAccountNumber.equals(receiverAccountNumber)) {
            throw new IllegalArgumentException("Sender and receiver account cannot be the same.");
        }
    
        // Validate sender has enough kilowatts
        if (sender.getKiloWatts() < MINIMUM_KILOWATTS_TRANSFER) {
            throw new IllegalArgumentException(
                    "Insufficient balance to transfer. User kiloWatts must not be less than 10.00.");
        }
    
        // Update sender account after token transfer
        sender.setKiloWatts(sender.getKiloWatts() - kilowatts);
        userRepository.save(sender);
    
        // Update receiver account after receiving token transfer
        receiver.setKiloWatts(receiver.getKiloWatts() + kilowatts);
        userRepository.save(receiver);
    
        // Record the transfer
        TokenTransfer tokenTransfer = new TokenTransfer();
        tokenTransfer.setSender(sender); // Set the sender (User entity)
        tokenTransfer.setReceiver(receiver); // Set the receiver (User entity)
        tokenTransfer.setKiloWatts(kilowatts);
        tokenTransfer.setCreatedAt(createdAt);
    
        // Save the token transfer to the database
        TokenTransfer savedTransfer = tokenTransferRepository.save(tokenTransfer);
    
        // Map the TokenTransfer entity to TokenTransferDTO
        return tokenTransferMapper.toDto(savedTransfer);
    }

}
