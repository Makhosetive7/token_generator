package com.example.ElectricityTokenGenerator.services.Tokens;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.repository.tokensRepository;

@Service
public class returnTokensByIdServices {

    public final tokensRepository tokensRepository;

    public returnTokensByIdServices(tokensRepository tokensRepository) {
        this.tokensRepository = tokensRepository;
    }

        // get tokens by id
    public Optional<TokensEntity> getTokensById(Long id) {
        return tokensRepository.findById(id);
    }

    
}
