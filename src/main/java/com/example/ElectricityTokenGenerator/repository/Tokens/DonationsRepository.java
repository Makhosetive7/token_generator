package com.example.ElectricityTokenGenerator.repository.Tokens;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import com.example.ElectricityTokenGenerator.entity.Tokens.DonationsEntity;

public interface DonationsRepository extends JpaRepository<DonationsEntity, Long> {
    List<DonationsEntity> findByAccountNumber(TokensEntity accountNumber);
}