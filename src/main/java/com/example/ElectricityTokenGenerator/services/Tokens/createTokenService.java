package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokenDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.Token;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.mappers.Tokens.TokensMapper;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;
import com.example.ElectricityTokenGenerator.services.calculations.ElectricityTokenConversion;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class createTokenService {

    private final TokenRepository tokenRepository;
    private final ElectricityTokenConversion electricityTokenConversion;
    private final userRepository userRepository;
    private final TokensMapper tokensMapper;// inject tokens mapper

    @Autowired
    public createTokenService(
            TokenRepository tokenRepository,
            ElectricityTokenConversion electricityTokenConversion,
            userRepository userRepository,
            TokensMapper tokensMapper) {
        this.tokenRepository = tokenRepository;
        this.electricityTokenConversion = electricityTokenConversion;
        this.userRepository = userRepository;
        this.tokensMapper = tokensMapper;
    }

    /**
     * Create a new token for the given account number and amount paid.
     * @param accountNumber The account number of the user.
     * @param amountPaid    The amount paid for the token.
     * @return The created token.
     * @throws RuntimeException If the user is not found.
     */

    public TokenDTO createTokens(String tokenBuyer, Double amount) {
        // Fetch user from database
        User user = userRepository.findByAccountNumber(tokenBuyer)
                .orElseThrow(() -> new RuntimeException("User not found with account number: " + tokenBuyer));

        // Create a new token entity
        Token token = new Token();
        token.setTokenBuyer(user);
        token.setTokenCode(generateUniqueToken());
        token.setAmount(amount);
        token.setKiloWatts(electricityTokenConversion.convertAmountPaidToKilowatts(amount));
        token.setPurchaseDate(LocalDateTime.now());
        token.setExpirationDate(LocalDateTime.now().plusDays(75));

        // Save the token to the database
        tokenRepository.save(token);

        // Update the user's kiloWatts
        double currentKiloWatts = user.getKiloWatts() != null ? user.getKiloWatts() : 0.0;
        double newKiloWatts = currentKiloWatts + electricityTokenConversion.convertAmountPaidToKilowatts(amount);
        user.setKiloWatts(newKiloWatts);
        userRepository.save(user);

        // Map the Token entity to TokenDTO
        return tokensMapper.toDto(token);
    }

    // Token Generation Logic (20-character alphanumeric token)
    private String generateUniqueToken() {
        String tokenCode;
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        do {
            StringBuilder token = new StringBuilder(20);
            for (int i = 0; i < 20; i++) {
                token.append(characters.charAt(random.nextInt(characters.length())));
            }
            tokenCode= token.toString();
        } while (tokenRepository.existsByTokenGenerated(tokenCode));

        return tokenCode;
    }
}
