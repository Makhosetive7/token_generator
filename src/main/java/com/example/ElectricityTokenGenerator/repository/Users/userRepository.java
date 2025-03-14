package com.example.ElectricityTokenGenerator.repository.Users;


import com.example.ElectricityTokenGenerator.entity.Users.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User, Long> {
    boolean existsByAccountNumber(String accountNumber);

    Optional<User> findByAccountNumber(String accountNumber);
}
