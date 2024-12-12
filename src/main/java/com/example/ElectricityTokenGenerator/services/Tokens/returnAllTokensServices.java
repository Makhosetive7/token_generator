package com.example.ElectricityTokenGenerator.services.Tokens;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.repository.tokensRepository;

@Service
public class returnAllTokensServices {
    
    public final  tokensRepository tokensRepository;

    public returnAllTokensServices(tokensRepository tokensRepository) {
        this.tokensRepository = tokensRepository;
    }

    
    // retrieve all tokens
    public List<TokensEntity> getAllTokens() {
        return tokensRepository.findAll();
    }
    
}
