package com.example.ElectricityTokenGenerator.repository.Tokens;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ElectricityTokenGenerator.entity.Tokens.TokenTransferEntity;

public interface TokenTransferRepository  extends JpaRepository<TokenTransferEntity, Long> {

}
