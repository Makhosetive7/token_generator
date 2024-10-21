package com.example.ElectricityTokenGenerator.services;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;

import java.util.List;
import java.util.Optional;

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
public TokensEntity createTokens(TokensEntity tokens) {
    return tokensRepository.save(tokens);
}


//retrieve all tokens
public List <TokensEntity> getAllTokens() {
    return tokensRepository.findAll();
}

//get tokens by id
public Optional<TokensEntity> getTokensById(Long id){
    return tokensRepository.findById(id);
}

//cancel  created  tokens

public void deleteTokens(Long id) {
    tokensRepository.deleteById(id);
}





























}
