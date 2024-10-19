package com.example.ElectricityTokenGenerator.services;

import com.example.ElectricityTokenGenerator.models.TokensModel;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ElectricityTokenGenerator.repository.TokensRepository;

@Service
public class TokenServices {
    
private final TokensRepository tokensRepository;

@Autowired
public TokenServices (TokensRepository tokensRepository) {
    this.tokensRepository = tokensRepository;
}

//create tokens 
public TokensModel createTokens(TokensModel tokens) {
    return tokensRepository.save(tokens);
}


//retrieve all tokens
public List <TokensModel> getAllTokens() {
    return tokensRepository.findAll();
}

//cancel  created  tokens

public void deleteTokens(Long id) {
    tokensRepository.deleteById(id);
}





























}
