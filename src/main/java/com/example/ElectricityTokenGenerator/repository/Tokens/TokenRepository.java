package com.example.ElectricityTokenGenerator.repository.Tokens;

import com.example.ElectricityTokenGenerator.entity.Tokens.TokenGenerator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TokenRepository extends JpaRepository<TokenGenerator, Long> {
    boolean existsByGeneratedTokenCode(String generatedTokenCode);
}