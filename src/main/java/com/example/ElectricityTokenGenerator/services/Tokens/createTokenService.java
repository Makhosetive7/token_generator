package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Random;
import org.springframework.stereotype.Service;
import com.example.ElectricityTokenGenerator.dto.Tokens.TokensGenerationDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenGeneratorEntity;
import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;
import com.example.ElectricityTokenGenerator.mappers.Tokens.TokenGenerationMapper;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;
import com.example.ElectricityTokenGenerator.services.calculations.ElectricityTokenConversion;

@Service
public class createTokenService {

    private final TokenRepository tokenRepository;
    private final ElectricityTokenConversion electricityTokenConversion;
    private final userRepository userRepository;
    private final TokenGenerationMapper tokenGenerationMapper;

    public createTokenService(
            TokenRepository tokensRepository,
            userRepository userRepository,
            ElectricityTokenConversion electricityTokenConversion,
            TokenGenerationMapper tokenGenerationMapper) {
        this.tokenRepository = tokensRepository;
        this.electricityTokenConversion = electricityTokenConversion;
        this.userRepository = userRepository;
        this.tokenGenerationMapper = tokenGenerationMapper;
    }

    public TokensGenerationDTO createTokens(
            String accountNumber,
            Double amountPaid,
            String serialNumber,
            LocalDateTime timeStamp) {

        UserEntities user = userRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("User not found with account number: " + accountNumber));

        TokenGeneratorEntity tokens = TokenGeneratorEntity.builder()
                .accountNumber(user)
                .amountPaid(amountPaid)
                .tokenGenerated(generateUniqueToken())
                .serialNumber(generateUniqueSerialNumber())
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusDays(75))
                .kiloWatts(electricityTokenConversion.convertAmountPaidToKilowatts(amountPaid))
                .build();

        tokenRepository.save(tokens);

        double currentKiloWatts = user.getKiloWatts() != null ? user.getKiloWatts() : 0.0;
        double newKiloWatts = currentKiloWatts + electricityTokenConversion.convertAmountPaidToKilowatts(amountPaid);
        user.setKiloWatts(newKiloWatts);
        userRepository.save(user);

        return tokenGenerationMapper.toDto(tokens);
    }

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

    private String generateUniqueSerialNumber() {
        String serialNumber;
        Random random = new Random();

        do {
            serialNumber = String.format("%010d", random.nextInt(1000000000));
        } while (tokenRepository.existsBySerialNumber(serialNumber));

        return serialNumber;
    }
}