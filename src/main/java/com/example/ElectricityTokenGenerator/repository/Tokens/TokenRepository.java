package com.example.ElectricityTokenGenerator.repository.Tokens;


import com.example.ElectricityTokenGenerator.entity.Tokens.TokenEntities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<TokenEntities, Long> {
    boolean existsByTokenGenerated(String tokenGenerated);
    boolean existsBySerialNumber(String serialNumber);
    void deleteByExpiredAt(LocalDateTime expiredAt);

    Optional<TokenEntities> findByAccountNumber(Long accountNumber);
}