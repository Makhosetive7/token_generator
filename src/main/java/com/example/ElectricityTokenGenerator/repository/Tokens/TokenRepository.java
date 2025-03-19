package com.example.ElectricityTokenGenerator.repository.Tokens;


import com.example.ElectricityTokenGenerator.entity.Tokens.Token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {
    boolean existsByTokenCode(String tokenCode);
   
}