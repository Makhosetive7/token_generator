package com.example.ElectricityTokenGenerator.repository.Tokens;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ElectricityTokenGenerator.entity.Tokens.DonationsEntity;

public interface DonationsRepository extends JpaRepository<DonationsEntity, Long> {
  
}