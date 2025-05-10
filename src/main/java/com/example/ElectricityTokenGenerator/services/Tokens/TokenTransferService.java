package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenTransferDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.Token;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenTransfer;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.exceptionHandling.TokensException.InsufficientBalanceException;
import com.example.ElectricityTokenGenerator.exceptionHandling.TokensException.InvalidTransferException;
import com.example.ElectricityTokenGenerator.mappers.Tokens.TokenTransferMapper;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenTransferRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;

import jakarta.transaction.InvalidTransactionException;

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

    // Validate kilowatts is not null and is positive
    if (kilowatts == null) {
        throw new IllegalArgumentException("Kilowatts value must not be null.");
    }
    if (kilowatts <= 0) {
        throw new IllegalArgumentException("Kilowatts must be a positive number.");
    }

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
        throw new InvalidTransferException("Sender and receiver accounts cannot be the same.");
    }

    // Validate sender balance
    if (sender.getKiloWatts() <= kilowatts) {
        throw new InsufficientBalanceException(
                "Insufficient balance. Available: " + sender.getKiloWatts() +
                        ", Attempted to transfer: " + kilowatts);
    }

    // Update sender account after token transfer
    sender.setKiloWatts(sender.getKiloWatts() - kilowatts);
    userRepository.save(sender);

    // Update receiver account after receiving token transfer
    receiver.setKiloWatts(receiver.getKiloWatts() + kilowatts);
    userRepository.save(receiver);

    // Record the transfer
    TokenTransfer tokenTransfer = TokenTransfer.builder()
            .sender(sender)
            .receiver(receiver)
            .kiloWatts(kilowatts)
            .createdAt(createdAt)
            .build();

    // Save the token transfer to the database
    TokenTransfer savedTransfer = tokenTransferRepository.save(tokenTransfer);

    // Map the TokenTransfer entity to TokenTransferDTO
    return tokenTransferMapper.toDto(savedTransfer);
}

}
