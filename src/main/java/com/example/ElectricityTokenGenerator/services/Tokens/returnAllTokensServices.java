package com.example.ElectricityTokenGenerator.services.Tokens;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.Tokens.Token;
import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;

@Service
public class returnAllTokensServices {
    
   public final TokenRepository tokenRepository;

    public returnAllTokensServices(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    
    // retrieve all tokens
    public List<Token> getAllTokens() {
        return tokenRepository.findAll();
    }
    
}
