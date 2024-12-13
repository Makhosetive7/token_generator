package com.example.ElectricityTokenGenerator.services.Tokens;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.repository.Tokens.TokenRepository;


@Service
public class deleteTokensService {
    
    public final TokenRepository tokenRepository;

    public deleteTokensService(TokenRepository tokensRepository) {
        this.tokenRepository = tokensRepository;
    }
   // cancel created tokens
   public void deleteTokens(Long id) {
    tokenRepository.deleteById(id);
}

}
