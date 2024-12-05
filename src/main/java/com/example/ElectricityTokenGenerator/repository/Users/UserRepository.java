package com.example.ElectricityTokenGenerator.repository.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ElectricityTokenGenerator.entity.Users.UsersEntity;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity, Long> {
    boolean existsByAccountNumber(String accountNumber);

}