package com.example.ElectricityTokenGenerator.repository;

import com.example.ElectricityTokenGenerator.entity.TokensEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TokensRepository extends JpaRepository<TokensEntity, Long> {
}
