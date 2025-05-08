package com.example.ElectricityTokenGenerator.services.Tokens;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.dto.Tokens.TokensGeneratorDTO;
import com.example.ElectricityTokenGenerator.entity.Tokens.TokenGenerator;
import com.example.ElectricityTokenGenerator.entity.Users.User;
import com.example.ElectricityTokenGenerator.enums.TokenStatus;
import com.example.ElectricityTokenGenerator.exceptionHandling.UsersException.UserNotFoundException;
import com.example.ElectricityTokenGenerator.mappers.Tokens.TokenGenerationMapper;
import com.example.ElectricityTokenGenerator.mappers.Tokens.TokensMapper;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;
import com.example.ElectricityTokenGenerator.repository.Users.userRepository;
import com.example.ElectricityTokenGenerator.services.calculations.ElectricityTokenConversion;

import jakarta.transaction.Transactional;

@Service
public class createTokenService {

    private final TokenRepository tokenRepository;
    private final ElectricityTokenConversion electricityTokenConversion;
    private final userRepository userRepository;
    private final TokenGenerationMapper tokenGenerationMapper;// inject tokens mapper

    @Autowired
    public createTokenService(
            TokenRepository tokenRepository,
            ElectricityTokenConversion electricityTokenConversion,
            userRepository userRepository,
            TokensMapper tokensMapper, TokenGenerationMapper tokenGenerationMapper) {
        this.tokenRepository = tokenRepository;
        this.electricityTokenConversion = electricityTokenConversion;
        this.userRepository = userRepository;
        this.tokenGenerationMapper = tokenGenerationMapper;
    }

    /**
     * Create a new token for the given account number and amount paid.
     * 
     * @param accountNumber The account number of the user.
     * @param amountPaid    The amount paid for the token.
     * @return The created token.
     * @throws RuntimeException If the user is not found.
     */
    @Transactional
    public TokensGeneratorDTO createTokens(String accountNumber, Double amount) {
        User user = userRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new UserNotFoundException(accountNumber + " not found"));

        TokenGenerator tokenGenerator = TokenGenerator.builder()
                .accountNumber(user)
                .generatedTokenCode(generateUniqueToken())
                .amount(amount)
                .kiloWatts(electricityTokenConversion.convertAmountPaidToKilowatts(amount))
                .generationDate(LocalDateTime.now())
                .status(TokenStatus.Active)
                .build();

        tokenRepository.save(tokenGenerator);

        double newKiloWatts = (user.getKiloWatts() != null ? user.getKiloWatts() : 0.0) +
                electricityTokenConversion.convertAmountPaidToKilowatts(amount);
        user.setKiloWatts(newKiloWatts);
        userRepository.save(user);

        return tokenGenerationMapper.toDto(tokenGenerator);
    }

    // Token Generation Logic (20-character alphanumeric token)
    private String generateUniqueToken() {
        String token;
        Random random = new Random();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        do {
            StringBuilder tokenBuilder = new StringBuilder(16); // Match entity length
            for (int i = 0; i < 16; i++) {
                tokenBuilder.append(characters.charAt(random.nextInt(characters.length())));
            }
            token = tokenBuilder.toString();
        } while (tokenRepository.existsByGeneratedTokenCode(token));

        return token;
    }
}
