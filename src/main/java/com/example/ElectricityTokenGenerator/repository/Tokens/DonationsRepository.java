package com.example.ElectricityTokenGenerator.repository.Tokens;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ElectricityTokenGenerator.entity.Tokens.Donation;

public interface DonationsRepository extends JpaRepository<Donation, Long> {
  
}