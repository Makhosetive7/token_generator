package com.example.ElectricityTokenGenerator.repository.Users;


import com.example.ElectricityTokenGenerator.entity.Users.UserEntities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<UserEntities, Long> {
    boolean existsByAccountNumber(String accountNumber);

    Optional<UserEntities> findByAccountNumber(String accountNumber);
}
