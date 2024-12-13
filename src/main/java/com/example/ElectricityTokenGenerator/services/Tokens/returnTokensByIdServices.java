package com.example.ElectricityTokenGenerator.services.Tokens;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;

@Service
public class returnTokensByIdServices {

    public final TokenRepository tokenRepository;

    public returnTokensByIdServices(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

        // get tokens by id
    public Optional<TokenEntities> getTokensById(Long id) {
        return tokenRepository.findById(id);
    }

    
}
