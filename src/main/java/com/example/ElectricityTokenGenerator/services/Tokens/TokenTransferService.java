package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenTransferEntity;
import com.example.ElectricityTokenGenerator.repository.UserRepository;
import com.example.ElectricityTokenGenerator.repository.tokensRepository;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenTransferRepository;

@Service
public class TokenTransferService {

    private final tokensRepository tokensRepository;
    private final TokenTransferRepository tokenTransferRepository;
    private final UserRepository userRepository;

    public TokenTransferService(tokensRepository tokensRepository, TokenTransferRepository tokenTransferRepository, UserRepository userRepository) {
        this.tokensRepository = tokensRepository;
        this.tokenTransferRepository = tokenTransferRepository;
        this.userRepository = userRepository;

    }

    @Transactional
    public TokenTransferEntity transferTokens(Long senderAccountNumber, Long receiverAccountNumber, Double kilowatts, Long transferTokenId, LocalDateTime createdAt) {
        // Validate sender account exists
        Optional<TokensEntity> senderAccountOptional = tokensRepository.findByAccountNumber(senderAccountNumber);
        if (senderAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Sender account not found.");
        }
        
        // Validate receiver account exists
        Optional<TokensEntity> receiverAccountOptional = tokensRepository.findByAccountNumber(receiverAccountNumber);
        if (receiverAccountOptional.isEmpty()) {
            throw new IllegalArgumentException("Receiver account not found.");
        }
        
        // Extract sender and receiver
        TokensEntity senderAccount = senderAccountOptional.get();
        TokensEntity receiverAccount = receiverAccountOptional.get();

        // Validate sender has enough kilowatts
        if (senderAccount.getKiloWatts() < kilowatts) {
            throw new IllegalArgumentException("Insufficient balance to transfer.");
        }

        // Deduct kilowatts from sender
        senderAccount.setKiloWatts(senderAccount.getKiloWatts() - kilowatts);
        tokensRepository.save(senderAccount);

        // Add kilowatts to receiver
        receiverAccount.setKiloWatts(receiverAccount.getKiloWatts() + kilowatts);
        tokensRepository.save(receiverAccount);

        // Record the transfer
        TokenTransferEntity tokenTransfer = new TokenTransferEntity();
        tokenTransfer.setSenderAccountNumber(senderAccount);
        tokenTransfer.setReceiverAccountNumber(receiverAccount);
        tokenTransfer.setTransferTokenId(transferTokenId);
        tokenTransfer.setKiloWatts(kilowatts);
        tokenTransfer.setCreatedAt(createdAt);

        return tokenTransferRepository.save(tokenTransfer);
    }
}
