package com.example.ElectricityTokenGenerator.repository;

import com.example.ElectricityTokenGenerator.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    boolean existsByAccountNumber(String accountNumber);

    Optional<UsersEntity> findByAccountNumber(String accountNumber);
}
