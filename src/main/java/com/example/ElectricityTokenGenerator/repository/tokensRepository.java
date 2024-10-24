package com.example.ElectricityTokenGenerator.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;

import java.time.LocalDateTime;


@Repository
public interface TokensRepository extends JpaRepository<TokensEntity, Long> {
    boolean existsByTokenGenerated(String tokenGenerated);
    boolean existsBySerialNumber(String serialNumber);
    void deleteByExpiredAt(LocalDateTime expiredAt);
}
