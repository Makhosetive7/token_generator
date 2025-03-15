package com.example.ElectricityTokenGenerator.repository.Tokens;


import com.example.ElectricityTokenGenerator.entity.Tokens.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

}