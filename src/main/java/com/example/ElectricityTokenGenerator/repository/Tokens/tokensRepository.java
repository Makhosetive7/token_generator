package com.example.ElectricityTokenGenerator.repository.Tokens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElectricityTokenGenerator.entity.Tokens.TokensEntity;

import java.time.LocalDateTime;


@Repository
public interface tokensRepository extends JpaRepository<TokensEntity, Long> {
    boolean existsByTokenGenerated(String tokenGenerated);
    boolean existsBySerialNumber(String serialNumber);
    void deleteByExpiredAt(LocalDateTime expiredAt);
}