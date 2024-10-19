package com.example.ElectricityTokenGenerator.repository;

import com.example.ElectricityTokenGenerator.models.TokensModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TokensRepository extends JpaRepository<TokensModel, Long> {
}
