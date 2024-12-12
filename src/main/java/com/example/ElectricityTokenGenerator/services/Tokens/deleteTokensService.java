package com.example.ElectricityTokenGenerator.services.Tokens;

import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.repository.tokensRepository;


@Service
public class deleteTokensService {
    
    public final tokensRepository tokensRepository;

    public deleteTokensService(tokensRepository tokensRepository) {
        this.tokensRepository = tokensRepository;
    }
   // cancel created tokens
   public void deleteTokens(Long id) {
    tokensRepository.deleteById(id);
}

}
