package com.example.ElectricityTokenGenerator.repository.Tokens;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ElectricityTokenGenerator.entity.Tokens.TokenTransferEntity;
import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;

public interface TokenTransferRepository  extends JpaRepository<TokenTransferEntity, Long> {
    
    Optional<TokenTransferEntity> findByAccountNumber(UserEntities accountNumber);

}
